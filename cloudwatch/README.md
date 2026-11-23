# Optional CloudWatch Agent exercise

This is a starter config for an EC2 Linux host; it does not install or deploy anything.

1. Attach an approved EC2 instance profile with CloudWatch Agent permissions (use least privilege).
2. Install the agent using current AWS documentation for your Linux distribution.
3. Change `/var/log/myapp/application.log` to an existing application log path.
4. Ensure outbound connectivity to CloudWatch endpoints or required VPC endpoints.

Place the JSON at `/opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.json`, then run:
```bash
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m ec2 -c file:/opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.json -s
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a status
sudo tail -n 100 /opt/aws/amazon-cloudwatch-agent/logs/amazon-cloudwatch-agent.log
```
Check CloudWatch Metrics namespace `Demo/Host` and log group `/demo/application`. Set retention, encryption and alarms deliberately. Do not log secrets or personal data.
