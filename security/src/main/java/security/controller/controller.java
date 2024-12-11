package security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/rest1/security")
public class controller {

	@GetMapping("/loginform")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("main.html");
		return mv;
	}
	
	@GetMapping("/loginform2")
	@PreAuthorize("hasRole('ADMIN')")
	public String Msg() {
		return "Message";
	}
	
	@GetMapping("/loginform3")
	@PreAuthorize("hasRole('ADMIN')")
	public String Msg1() {
		return "Message";
	}
	
}
