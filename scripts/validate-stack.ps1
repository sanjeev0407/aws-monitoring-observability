$checks=@(
 @{Name="Application";Url="http://localhost:8080/actuator/health"},
 @{Name="Prometheus";Url="http://localhost:9090/-/healthy"},
 @{Name="Grafana";Url="http://localhost:3000/api/health"}
)
foreach($c in $checks){
 try{$r=Invoke-WebRequest -Uri $c.Url -UseBasicParsing -TimeoutSec 5; Write-Host "$($c.Name): HTTP $([int]$r.StatusCode)" -ForegroundColor Green}
 catch{Write-Host "$($c.Name): FAILED - $($_.Exception.Message)" -ForegroundColor Red}
}
