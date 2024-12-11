package security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class security {

	@GetMapping("/message")
	public String Message() {
		return "Come to Move on";
	}
	
	@GetMapping("/message")
	public String Message3() {
		return "Come to Move ontone";
	}
}
