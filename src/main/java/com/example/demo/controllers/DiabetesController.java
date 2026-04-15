package com.example.demo.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows the frontend to connect
public class DiabetesController {

    @PostMapping("/predict-diabetes")
    public ResponseEntity<Map<String, Object>> predict(@RequestBody Map<String, Object> input) {
        Map<String, Object> responseMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://localhost:5000/predict";

        try {
            // 1. Basic Validation
            if (input.get("features") == null) {
                responseMap.put("error", "Features list is missing");
                return ResponseEntity.badRequest().body(responseMap);
            }

            // 2. Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(input, headers);

            // 3. Call Python/Flask AI Service
            ResponseEntity<Map> flaskResponse = restTemplate.postForEntity(flaskUrl, entity, Map.class);

            if (flaskResponse.getBody() != null && flaskResponse.getBody().containsKey("prediction")) {
                Number pred = (Number) flaskResponse.getBody().get("prediction");
                int val = pred.intValue();
                
                responseMap.put("prediction", val);
                responseMap.put("message", val == 1 ? "⚠️ High Risk of Diabetes" : "✅ Low Risk of Diabetes");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", "AI Model returned an empty response");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
            }

        } catch (ResourceAccessException e) {
            responseMap.put("error", "Connection Failed: Is the Flask server running on port 5000?");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseMap);
        } catch (Exception e) {
            responseMap.put("error", "Internal Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
        }
    }
}