# Project 4 — Monitoring and Observability

A runnable demo using **Spring Boot + Prometheus + Grafana**, with an optional **Amazon CloudWatch Agent** configuration. Designed for Windows/PowerShell and Docker Desktop.

## Architecture
```text
Spring Boot :8080 -- /actuator/prometheus --> Prometheus :9090 --> Grafana :3000
      |
      +-- /actuator/health

Optional AWS EC2 host --> CloudWatch Agent --> CloudWatch Metrics + Logs + Alarms
```

## Contents
- Java 17 Spring Boot API with Actuator and Micrometer Prometheus metrics
- Dockerfile and Docker Compose stack
- Prometheus scrape configuration and alert rules
- Provisioned Grafana Prometheus datasource and dashboard
- CloudWatch Agent starter JSON for EC2 Linux
- PowerShell traffic generator and validation script
- Runbooks, troubleshooting and production checklist

## Prerequisites
Windows 10/11, PowerShell, Docker Desktop with Compose, Git. AWS CLI/account are optional for the CloudWatch exercise.

## Start the stack
From this folder:
```powershell
docker compose up --build -d
docker compose ps
```
Open:
- App health: http://localhost:8080/actuator/health
- App API: http://localhost:8080/api/health
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000

Local Grafana demo login: `admin` / `ChangeMe-LocalOnly!`. Change it before any shared or exposed use. All ports bind to localhost by default.

## Generate traffic
```powershell
.\scripts\generate-traffic.ps1 -Requests 100 -DelayMs 150
```
Prometheus queries to try:
```promql
up
sum(rate(http_server_requests_seconds_count[5m]))
sum(rate(http_server_requests_seconds_count{status=~"5.."}[5m]))
jvm_memory_used_bytes
```
In Grafana open **Dashboards → Platform Observability → Demo Application Observability**.

## Test an outage
```powershell
docker compose stop app
```
After a minute, inspect http://localhost:9090/alerts and Grafana. Restore:
```powershell
docker compose start app
```
Alert rules evaluate in Prometheus. This bundle does not configure external notification delivery; add Alertmanager or an approved notification integration before production.

## Validate and troubleshoot
```powershell
.\scriptsalidate-stack.ps1
docker compose logs --tail 100 app
docker compose logs --tail 100 prometheus
docker compose logs --tail 100 grafana
```
Stop containers: `docker compose down`. Remove local metric/dashboard history too: `docker compose down -v`.

## Optional AWS CloudWatch
See `cloudwatch/README.md`. The starter agent config collects CPU, memory, disk and an example application log from an EC2 Linux host. Attach an approved IAM instance profile, install the agent, change the example log path, then start it using the commands in that guide. No AWS resources are created by this project.

## Production hardening
- Keep Grafana/Prometheus on private networks; use TLS, SSO and RBAC.
- Change demo credentials; use a secrets manager for real secrets.
- Apply least-privilege IAM and encrypt CloudWatch log groups where required.
- Set log and metric retention deliberately; monitor storage and cost.
- Avoid high-cardinality labels (user IDs, request IDs, arbitrary URLs).
- Give every alert an owner, severity, customer impact and runbook.
- Test notification delivery and monitor the monitoring stack itself.
- Never collect credentials, tokens or sensitive customer data.

## Interview topics
Metrics vs logs vs traces; Prometheus pull-based scraping; CloudWatch vs Prometheus; actionable alert design; SLI/SLO basics; incident acknowledgement, mitigation, communication, recovery validation and RCA.
