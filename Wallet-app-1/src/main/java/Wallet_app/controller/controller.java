package Wallet_app.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Wallet_app.document.Transactions;
import Wallet_app.document.authdto;
import Wallet_app.dto.Transactiondto;
import Wallet_app.dto.UserInfodto;
import Wallet_app.repository.TransactionRepository;
import Wallet_app.security.jwttoken;
import Wallet_app.service.service;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wallet")
public class controller {
	
	@Autowired
	service transactionservice;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	jwttoken jwttoken;
	
	@Autowired
	AuthenticationManager authenticationManager;

//	@GetMapping("/create-csvfile")
//	public String createFileInFolder(@RequestParam String foldername,@RequestParam String filename) {
//		return transactionservice.writeCSVFile(foldername,filename);
//	}
//	
//	@PostMapping("/transactions")
//	public String uploadCSVFile() {
//		return transactionservice.saveCSVFile();
//	}
	
	
@GetMapping("/login")
	
	public ModelAndView getLogin() {
		ModelAndView mv=new ModelAndView("main.html");
		return mv;
	}
	
	
	@GetMapping("/gettransactions")
	@PreAuthorize("hasAuthority('MANAGER')")
	public List<Transactiondto> getTransactions() {
		return transactionservice.getTransactionsDetails();
	}
	
	@PostMapping("/menu")
	public ModelAndView getAll(@RequestParam("userid") String userid) {
		List<Transactions> id= transactionservice.getByUserId(userid);
		ModelAndView mv=null;
		
	if(!(id.isEmpty())) {
		mv=new ModelAndView("menu.html");
		mv.addObject("id", id);
		
	}else {
		mv=new ModelAndView("result.html");
		mv.addObject("message", "UserNanme is not Found");
	}
	return mv;
	}
	
	@GetMapping("/transactions/analyse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Transactiondto> getTransaction(@RequestParam String category) {
		if(category.equals("all")) {
			return transactionservice.getTransactionsDetails();
		}else {
			return transactionservice.getTransactionsDetailsByCategory(category);
		}
	}
	
	
	@GetMapping("/transaction/time")
	public List<Transactiondto> getTime(@RequestParam String time){
		return transactionservice.findByTime(time);
	}
	

	@GetMapping("/transaction/date")
	public List<Transactiondto> getDate(@RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){
		return transactionservice.findByDate(date);
	}
	
	
	@GetMapping("/transaction/{type}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Transactiondto> getType(@PathVariable("type") String type){
		return transactionservice.findByType(type);
	}
	
	
	@GetMapping("/transactions/{category}")
	@PreAuthorize("hasAuthority('USER')")
	public List<Transactiondto> getCategory(@PathVariable("category") String category) {
		return transactionservice.findByCategory(category);
	}
	
	
	@GetMapping("/transaction/amount/{amount}")
	public ResponseEntity<List<Transactiondto>> getAmount(@PathVariable("amount") double amount){
		List<Transactiondto> amounts= transactionservice.getAmounts(amount);
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES).cachePublic())
				.body(amounts);
	}
	
	@PostMapping("/set/userinfo")
	public String setUserInfo(@RequestBody UserInfodto dto) {
		return transactionservice.setInfo(dto);
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody authdto dto) {
		Authentication authenticateManager= 
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		if(authenticateManager.isAuthenticated()) {
			return jwttoken.createToken(dto.getUsername());
		}else {
			throw new UsernameNotFoundException("invalid User request!");
		}
			
	}
}
