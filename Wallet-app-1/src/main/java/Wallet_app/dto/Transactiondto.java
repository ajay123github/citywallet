package Wallet_app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transactiondto {

	private String type;
	private double amountspend;
	private String category;
	private LocalDate date;
	private String time;
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
	public Transactiondto(String type, double amountspend, String category, LocalDate date, String time) {
		super();
		this.type = type;
		this.amountspend = amountspend;
		this.category = category;
		this.date = date;
		this.time = time;
	}
	public Transactiondto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
