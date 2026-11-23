param([int]$Requests=50,[int]$DelayMs=200,[string]$BaseUrl="http://localhost:8080")
$ErrorActionPreference="Stop"
for($i=1;$i -le $Requests;$i++){
 try {
  if(($i % 10) -eq 0){ Invoke-RestMethod "$BaseUrl/api/demo?fail=true" | Out-Null }
  else { Invoke-RestMethod "$BaseUrl/api/demo" | Out-Null }
  Write-Host "[$i/$Requests] request completed"
 } catch { Write-Host "[$i/$Requests] expected demo error/request failure" }
 Start-Sleep -Milliseconds $DelayMs
}
Write-Host "Done. Wait 1-2 minutes for dashboard rates and alert evaluation."
