package Wallet_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WalletApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(WalletApp1Application.class, args);
	}

}
