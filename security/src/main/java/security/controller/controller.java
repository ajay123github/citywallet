package security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/rest1/security")
public class controller {

	@GetMapping("/loginform")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("main.html");
		return mv;
	}
	
}
