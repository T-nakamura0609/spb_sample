package myexample.jerseyspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("APITransport")
public class ApiServiceController {

	@RequestMapping(value = "APIAccessParametors", produces = "application/json;charset=UTF-8")
	public String APITAccessParametors() {
		String APIResponcesJson =
				"{\"user\": \"AtsuAtsuUdon\","
			            + " \"tool\": \"アツアツうどんですよろしく！\"}";

		return APIResponcesJson;
	}
}
