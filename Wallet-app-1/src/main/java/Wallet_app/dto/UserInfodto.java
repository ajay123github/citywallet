package Wallet_app.dto;

import java.util.List;

public class UserInfodto {

	private String username;
	private String password;
	private String mail;
	private List<String> roles;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public UserInfodto(String username, String password, String mail, List<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.roles = roles;
	}
	public UserInfodto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
