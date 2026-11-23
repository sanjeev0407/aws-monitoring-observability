package com.example.observability;
import java.time.Instant;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@RestController
public class HealthController {
 @GetMapping("/api/health") public Map<String,Object> health() {
  return Map.of("status","UP","service","observability-demo","timestamp",Instant.now().toString());
 }
 @GetMapping("/api/info") public Map<String,Object> info() {
  return Map.of("application","Observability Demo API","version","1.0.0","environment","demo");
 }
 @GetMapping("/api/demo") public Map<String,Object> demo(@RequestParam(defaultValue="false") boolean fail) {
  if (fail) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Intentional demo failure");
  return Map.of("message","Request completed");
 }
}
