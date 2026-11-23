# Architecture notes

**Metrics** are numeric time series for trends and alerting. **Logs** are timestamped event records for investigation. **Traces** show a request journey across services and help locate latency.

Spring Boot Actuator/Micrometer exposes `/actuator/prometheus`. Prometheus scrapes that endpoint every 15 seconds and evaluates alert rules. Grafana queries Prometheus and renders dashboards. In AWS, CloudWatch Agent can publish host metrics and application logs to CloudWatch.

Use bounded labels such as service, environment, region and team. Never use user IDs, session IDs, request IDs or arbitrary URLs as metric labels; high cardinality increases resource and cost.
