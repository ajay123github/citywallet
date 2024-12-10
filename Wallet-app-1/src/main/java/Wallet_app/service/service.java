package Wallet_app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.swing.text.Document;

//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVPrinter;
//import org.apache.commons.lang3.time.DateUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

//import com.opencsv.CSVParser;
//import com.opencsv.CSVReader;

import Wallet_app.document.Transactions;
import Wallet_app.document.UserInfo;
import Wallet_app.dto.Transactiondto;
import Wallet_app.dto.UserInfodto;
import Wallet_app.repository.TransactionRepository;
import Wallet_app.repository.UserInfoRepository;
import lombok.Synchronized;

@Service
public class service {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	UserInfoRepository userrepo;
	
	@Autowired
	PasswordEncoder encoder;
//
//
//	public  String writeCSVFile(String foldername,String filename) {
//		
////		if(!filename.endsWith(".csv")) {
////			filename=filename+".csv";
////		}
//		
//		String filepath=foldername+"/"+filename;
//		
//		// TODO Auto-generated method stub
//		String[] headers= {"userId","type","amountspend","category","date","time"};
//		
//		List<List<String>> data=Arrays.asList(
//				
//				Arrays.asList("bob1", "debit", "1000", "mobile recharged", "03-10-2024", "9:47:00"),
//				Arrays.asList("bob1", "debit", "5000", "groceries", "02-10-2024", "11:15:00"),
//				Arrays.asList("bob1", "debit", "900", "mobile recharged", "01-10-2024", "6:23:00"),
//				Arrays.asList("bob1", "debit", "125", "medicines", "01-10-2024", "7:23:00"),
//				Arrays.asList("bob1", "debit", "1000", "buspass", "01-10-2024", "10:00:00"),
//				Arrays.asList("bob1", "debit", "400", "buspass", "01-10-2024", "10:01:00"),
//				Arrays.asList("bob1", "debit", "1200", "power bill", "30-09-2024", "11:00:00"),
//				Arrays.asList("bob1", "debit", "3000", "shopping", "29-09-2024", "12:30:00"),
//				Arrays.asList("bob1", "debit", "4000", "shopping", "28-09-2024", "2:30:00"),
//				Arrays.asList("bob1", "debit", "1600", "food", "25-09-2024", "3:00:00"),
//				Arrays.asList("bob1", "debit", "1600", "food", "25-09-2024", "7:00:00"),
//				Arrays.asList("bob1", "debit", "1600", "food", "24-09-2024", "3:00:00")
//			
//				);
//		
//		
//		
////		File folder=new File(filepath);
////		if(!folder.exists()) {
////			folder.mkdirs();
////		}
//		
//		try(FileWriter fileWriter=new FileWriter(filepath);
//				CSVPrinter csvPrinter=new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(headers))){
//			for(List<String> record:data) {
//				csvPrinter.printRecord(record);
//			}
//			return "Records recorded successfully";
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Error while writing csv"+e.getMessage());
//		}
//	}
//
//	public synchronized String saveCSVFile() {
//		
//		// TODO Auto-generated method stub
//		
//		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ssa");
//		try(BufferedReader bufferedReader=new BufferedReader(new FileReader("D:/transaction/transactiondetails"));
//				CSVParser csvParser=new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
//						.withIgnoreHeaderCase().withTrim())){
//			
//			csvParser.stream().map(csvRecords->{
//				Transactions transactions=new Transactions();
//				transactions.setUserId(csvRecords.get("userId"));
//				transactions.setType(csvRecords.get("type"));
//				try {
//				transactions.setAmountspend(Double.parseDouble(csvRecords.get("amountspend")));
//				}catch(NumberFormatException e) {
//					transactions.setAmountspend(0.0);
//				}
//				
//				transactions.setCategory(csvRecords.get("category"));
//				
//				LocalDate date= LocalDate.parse(csvRecords.get("date"), dateTimeFormatter);
//				transactions.setDate(date);
//				
//				String timeString=csvRecords.get("time");
////				if(timeString.matches("\\d:\\d{2}:\\d{2}")) {
////					timeString="0"+timeString;
////				}
////				LocalTime time=LocalTime.parse(timeString, timeFormatter);
//				transactions.setTime(timeString);
//				
//				return transactions;
//			}).forEach(transactionRepository::save);
//			
//			return "Data Inserted to mongodb Successfully";
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Error while inserting data into mongodb:"+e.getMessage());
//		}
//	}

	public List<Transactiondto> getTransactionsDetails() {
		// TODO Auto-generated method stub
		List<Transactions> transactiondata= transactionRepository.findAll();
		
		List<Transactiondto> dtos=transactiondata.stream().map(transactions->{
			
			Transactiondto dto=new Transactiondto();
			dto.setType(transactions.getType());
			dto.setTime(transactions.getTime());
			dto.setAmountspend(transactions.getAmountspend());
			dto.setCategory(transactions.getCategory());
			dto.setDate(transactions.getDate());
			
			return dto;
			
		}).collect(Collectors.toList());
	
		return dtos;
	}

	public List<Transactiondto> getTransactionsDetailsByCategory(String category) {
		// TODO Auto-generated method stub
		List<Transactions> transactions = transactionRepository.findByCategory(category);
		
		List<Transactiondto> dtos = transactions.stream().map(transaction->{
			Transactiondto dto=new Transactiondto();
			dto.setType(transaction.getType());
			dto.setTime(transaction.getTime());
			dto.setAmountspend(transaction.getAmountspend());
			dto.setCategory(transaction.getCategory());
			dto.setDate(transaction.getDate());
			return dto;
		}).collect(Collectors.toList());
		
		return dtos;
	}

	public List<Transactions> getUserId(String userid) {
		// TODO Auto-generated method stub
		List<Transactions> userids=transactionRepository.findByUserId(userid);
		return userids;
	}
	
	public List<Transactions> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public List<Transactions> getByUserId(String userid) {
		// TODO Auto-generated method stub
		List<Transactions> entity=transactionRepository.findByUserId(userid);
			return entity;
	}

	public List<Transactiondto> findByTime(String time) {
		// TODO Auto-generated method stub
		List<Transactions> times=transactionRepository.findByTimeOrderByCategoryAsc(time);
		
		return times.stream().map(timedata->{
			Transactiondto dto=new Transactiondto();
			dto.setType(timedata.getType());
			dto.setTime(timedata.getTime());
			dto.setAmountspend(timedata.getAmountspend());
			dto.setCategory(timedata.getCategory());
			dto.setDate(timedata.getDate());
			return dto;
		}).collect(Collectors.toList());
	}

	

	public List<Transactiondto> findByDate(LocalDate date) {
		// TODO Auto-generated method stub
		LocalDateTime startofdate = date.atStartOfDay();
		LocalDateTime endofdate=date.atTime(23, 59, 59).withNano(999999999);
		
		//ZoneId zoneId = ZoneId.systemDefault();
		
		// Converting LocalDateTime to ZonedDateTime
		
		ZoneId zoneId = ZoneOffset.UTC;
		
		ZonedDateTime startzonedate= startofdate.atZone(zoneId);
		ZonedDateTime endzonedate = endofdate.atZone(zoneId);
		
		// Converting the zonedDateTime to Instant, and then to Date
		
		Date startDate = Date.from(startzonedate.toInstant());
		Date endDate = Date.from(endzonedate.toInstant());
		

		
		List<Transactions> dates=transactionRepository.findByDateBetweenOrderByCategoryAsc(startDate,endDate);
		
			return dates.stream().map(datedata->{
			Transactiondto dto=new Transactiondto();
			dto.setType(datedata.getType());
			dto.setTime(datedata.getTime());
			dto.setAmountspend(datedata.getAmountspend());
			dto.setCategory(datedata.getCategory());
			dto.setDate(date);
			return dto;
	}).collect(Collectors.toList());
			
	}

	@Cacheable(value="customerCache",key="#type")
	public List<Transactiondto> findByType(String type) {
		// TODO Auto-generated method stub
		List<Transactions> typestransaction = transactionRepository.findByType(type);
		
	return	typestransaction.stream().map(types->{
			Transactiondto dto=new Transactiondto();
			dto.setType(types.getType());
			dto.setCategory(types.getCategory());
			dto.setDate(types.getDate());
			dto.setAmountspend(types.getAmountspend());
			dto.setTime(types.getTime());
			return dto;
		}).collect(Collectors.toList());
	}

	@Cacheable(value="customerCache", key="#category")
	public List<Transactiondto> findByCategory(String category) {
		// TODO Auto-generated method stub
	List<Transactions>	transactions= transactionRepository.findByCategory(category);
		
	List<Transactiondto> dtos = transactions.stream().map(transaction->{
		Transactiondto dto=new Transactiondto();
		dto.setType(transaction.getType());
		dto.setTime(transaction.getTime());
		dto.setAmountspend(transaction.getAmountspend());
		dto.setCategory(transaction.getCategory());
		dto.setDate(transaction.getDate());
		return dto;
	}).collect(Collectors.toList());
	
	return dtos;
	}

	public List<Transactiondto> getAmounts(double amount) {
		// TODO Auto-generated method stub
		List<Transactions>	transactions= transactionRepository.findByAmountspend(amount);
		
		List<Transactiondto> dtos = transactions.stream().map(transaction->{
			Transactiondto dto=new Transactiondto();
			dto.setType(transaction.getType());
			dto.setTime(transaction.getTime());
			dto.setAmountspend(transaction.getAmountspend());
			dto.setCategory(transaction.getCategory());
			dto.setDate(transaction.getDate());
			return dto;
		}).collect(Collectors.toList());
		
		return dtos;
	}

	public String setInfo(UserInfodto dto) {
		// TODO Auto-generated method stub
		UserInfo user=new UserInfo();
		user.setUsername(dto.getUsername());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setMail(dto.getMail());
		user.setRoles(dto.getRoles());
		userrepo.save(user);
		
		return "User Added Successfully";
	}

	
}
