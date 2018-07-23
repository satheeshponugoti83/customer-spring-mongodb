package com.dev.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dev.model.Customer;
import com.dev.repository.CustomerRepository;

@Component
public class ScheduleTasks {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	CustomerRepository customerRepository;
	
	//The task will every day at 6 am in the morning
	@Scheduled(cron = "0 0 6,* * * *")
	public void scheduleTaskwithCorrectRecords() {
		
		logger.info("Execute Task 6 am Everyday ::{}"+dateTimeFormatter.format(LocalDateTime.now()));
		
		List<Customer> customerDetails = new ArrayList<Customer>();
		
		Customer cust1 = new Customer();
		cust1.setName("Rob");
		cust1.setAge(30);
		cust1.isActive();
		customerDetails.add(cust1);
		
		Customer cust2 = new Customer();
		cust2.setName("john");
		cust2.setAge(26);
		cust2.isActive();
		customerDetails.add(cust2);
		
		Customer cust3 = new Customer();
		cust3.setName("Michal");
		cust3.setAge(28);
		cust3.isActive();
		customerDetails.add(cust3);
		
		customerRepository.saveAll(customerDetails);
		
	}
	
	/***
	 * Save only the valid records. And skip the invalid records.
	 * The task will every day at 6 am in the morning.
	 */
		@Scheduled(cron = "0 0 6,* * * *")
		public void scheduleTaskwithErrorRecords() {
			
			logger.info("Execute Task 6 am Everyday ::{}"+dateTimeFormatter.format(LocalDateTime.now()));
			
			List<Customer> customerDetails = new ArrayList<Customer>();
			
			Customer cust1 = new Customer();
			cust1.setName("khol");
			cust1.setAge(30);
			cust1.isActive();
			customerDetails.add(cust1);
			
			Customer cust2 = new Customer();
			cust2.setName("Inna");
			cust2.setAge(20);
			cust2.isActive();
			customerDetails.add(cust2);
			
			Customer cust3 = new Customer();
			cust3.setName("Richel");
			cust3.setAge(23);
			cust3.isActive();
			customerDetails.add(cust3);
		for (Customer customer : customerDetails) {
			try {
				customerRepository.save(customer);
			} catch (Exception E) {
				logger.info("The customner record contain malformed data"+customer.getName(),E.getMessage());;
               continue;
			}
		}
			
			
		}
		
		/***
		 * Save only the valid records. And skip the invalid records.
		 * The task will every day at 6 am in the morning.
		 * @throws Exception 
		 */
			@Scheduled(cron = "0 0 6,* * * *")
			public void scheduleTaskwithErrorRecordsStopSave() throws Exception {
				
				logger.info("Execute Task 6 am Everyday ::{}"+dateTimeFormatter.format(LocalDateTime.now()));
				
				List<Customer> customerDetails = new ArrayList<Customer>();
				
				Customer cust1 = new Customer();
				cust1.setName("khol");
				cust1.setAge(30);
				cust1.isActive();
				customerDetails.add(cust1);
				
				Customer cust2 = new Customer();
				cust2.setName("Inna");
				cust2.setAge(20);
				cust2.isActive();
				customerDetails.add(cust2);
				
				Customer cust3 = new Customer();
				cust3.setName("Richel");
				cust3.setAge(23);
				cust3.isActive();
				customerDetails.add(cust3);
			for (Customer customer : customerDetails) {
				try {
					customerRepository.save(customer);
				} catch (Exception ex) {
					logger.info("The customner record contain malformed data"+customer.getName(),ex.getMessage());;
	               throw new Exception("The customer list have bad data"+ex.getMessage());
				}
			}
				
				
			}


}
