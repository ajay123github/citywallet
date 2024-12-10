package Wallet_app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import Wallet_app.service.UserInfoDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filter extends OncePerRequestFilter{

	@Autowired
	jwttoken jwttoken;
	
	@Autowired
	UserInfoDetailsService userservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = null;
		String username = null;
		String header = request.getHeader("Authorization");
		if(null != header && header.startsWith("Bearer ")) {
			token = header.substring(7);
			username = jwttoken.extractUsername(token);
		}
		if(null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userservice.loadUserByUsername(username);
			if(jwttoken.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
