package com.demo.ping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import org.json.JSONException;
import java.time.Instant;

@SpringBootApplication
@RestController
public class PingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingApplication.class, args);
	}

	@CrossOrigin
	@GetMapping("/ping")
	public String ping(@RequestParam(defaultValue = "To ping, or not to ping; that is the question.") String ping) throws JSONException {
		JSONObject pong = new JSONObject();

		pong.put("ping", ping);
		pong.put("received_at", Instant.now());

		return pong.toString();
	}

}
