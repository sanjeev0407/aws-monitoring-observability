package com.example.observability;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class HealthControllerTest {
 @Autowired MockMvc mvc;
 @Test void healthReturnsUp() throws Exception {
  mvc.perform(get("/api/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
 }
 @Test void demoFailureCanBeTriggered() throws Exception {
  mvc.perform(get("/api/demo").param("fail","true")).andExpect(status().isInternalServerError());
 }
}
