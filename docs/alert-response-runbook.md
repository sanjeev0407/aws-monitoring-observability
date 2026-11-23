# Alert response runbook

## ApplicationDown — Critical
1. Acknowledge and identify service/environment/customer impact.
2. Check Prometheus Status → Targets and `docker compose ps`.
3. Review `docker compose logs --tail 200 app`.
4. Check recent deployment/configuration changes and dependencies.
5. Mitigate or roll back through the approved change process.
6. Verify `/actuator/health`, Prometheus `up`, and a successful synthetic request.
7. Communicate recovery and record timeline, evidence, root cause and prevention.

## HighRequestErrorRate — Warning
Check traffic volume, 5xx trend, application logs, recent deployments, downstream APIs/database, connection pools and resource saturation. Mitigate, validate and record representative errors without secrets.

## HighJvmMemoryUsage — Warning
Confirm sustained growth; compare heap used/max, GC pauses, thread count and traffic. Investigate leaks, cache growth or large payloads. Restart only as a controlled mitigation and preserve evidence.

## Incident update template
Severity | Start time | Customer impact | Current status | Actions taken | Next update | Owner | Recovery validation
