package Wallet_app.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import Wallet_app.document.Transactions;

public interface TransactionRepository extends MongoRepository<Transactions, String>{

	List<Transactions> findByCategory(String category);

	List<Transactions> findByUserId(String userid);

	List<Transactions> findByTimeOrderByCategoryAsc(String time);

	List<Transactions> findByDateBetweenOrderByCategoryAsc(Date startDate, Date endDate);

	List<Transactions> findByType(String type);

	List<Transactions> findByAmountspend(double amount);

	


}
