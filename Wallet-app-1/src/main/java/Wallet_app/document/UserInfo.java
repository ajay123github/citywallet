package Wallet_app.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="userinfo")
public class UserInfo {
	
	@Id
	private String id;
	private String username;
	private String password;
	private String mail;
	private List<String> roles;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public UserInfo(String id, String username, String password, String mail, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.roles = roles;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
