package Wallet_app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import Wallet_app.document.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo, String>{

	//Optional<UserInfo> findByName(String username);

	Optional<UserInfo> findByUsername(String username);
	
	

}
