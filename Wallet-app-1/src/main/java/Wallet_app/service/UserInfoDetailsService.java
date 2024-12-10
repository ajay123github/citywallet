package Wallet_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import Wallet_app.document.UserInfo;
import Wallet_app.repository.UserInfoRepository;

@Component
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	UserInfoRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo= userrepo.findByUsername(username);
		
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("username not found"+username));
		
	}

}
