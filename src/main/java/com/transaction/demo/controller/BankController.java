package com.transaction.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.demo.domain.Customer;
import com.transaction.demo.repository.CustomerRepository;
import com.transaction.demo.service.BankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BankController {

	private final CustomerRepository customerRepository;
	private final BankService bankService;
	
	@GetMapping("customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/transfer/{from}/{to}/{amount}")
	public List<Customer> transfer(@PathVariable String from,
						   @PathVariable String to,   
						   @PathVariable BigDecimal amount) {
		
		return bankService.transfer(from, to, amount);
	}
	
	@GetMapping("withdraw/{from}/{amount}")
	public Customer withdraw(@PathVariable String from, @PathVariable BigDecimal amount) {
		return bankService.withdraw(from, amount);
	}
	
	@GetMapping("deposit/{to}/{amount}")
	public Customer deposit(@PathVariable String to, @PathVariable BigDecimal amount) {
		return bankService.deposit(to, amount);
	}

}
