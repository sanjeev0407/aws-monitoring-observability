# Troubleshooting

| Symptom | Check | Action |
|---|---|---|
| App exits | `docker compose logs app` | Inspect Java/build error and port 8080 |
| Prometheus target DOWN | Status → Targets; Prometheus logs | Verify app is running, target `app:8080`, path `/actuator/prometheus` |
| Grafana No data | Explore → Prometheus; query `up` | Generate traffic; check datasource and time range |
| Grafana datasource fails | datasource URL | In Docker use `http://prometheus:9090`, not localhost |
| CloudWatch metrics absent | Agent status/log, IAM, network | Fix role, config, file path and connectivity |

Useful PromQL:
```promql
up
sum(rate(http_server_requests_seconds_count[5m]))
sum(rate(http_server_requests_seconds_count{status=~"5.."}[5m]))
histogram_quantile(0.95,sum by (le)(rate(http_server_requests_seconds_bucket[5m])))
sum(jvm_memory_used_bytes{area="heap"})
```
