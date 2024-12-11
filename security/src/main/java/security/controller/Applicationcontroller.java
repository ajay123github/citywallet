package security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/rest/security")
public class Applicationcontroller {

	@GetMapping("/msg")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public String getMsg() {
		return "Spring Boot Security";
	}
	
	@GetMapping("/loginform")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("login.html");
		return mv;
	}
}
