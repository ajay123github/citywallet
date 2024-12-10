package Wallet_app.document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.internal.connection.Time;

@Document(value="transactions")
public class Transactions {

	@Id
	private String id;
	private String userId;
	private String type;
	private double amountspend;
	private String category;
	private LocalDate date;
	private String time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmountspend() {
		return amountspend;
	}
	public void setAmountspend(double amountspend) {
		this.amountspend = amountspend;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Transactions(String id, String userId, String type, double amountspend, String category, LocalDate date,
			String time) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.amountspend = amountspend;
		this.category = category;
		this.date = date;
		this.time = time;
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
