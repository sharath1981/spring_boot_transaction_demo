package com.transaction.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.demo.domain.Customer;
import com.transaction.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BankService {

	private final CustomerRepository customerRepository;

	@Transactional
	public List<Customer> transfer(String from, String to, BigDecimal amount) {
		withdraw(from, amount);
		deposit(to, amount);
		return customerRepository.findAll();
	}

	public Customer withdraw(String from, BigDecimal amount) {
		final Customer fromCustomer = customerRepository.findByName(from);
		fromCustomer.debit(amount);
		return customerRepository.save(fromCustomer);
	}
	
	public Customer deposit(String to, BigDecimal amount) {
		final Customer toCustomer = customerRepository.findByName(to);
		toCustomer.credit(amount);
		return customerRepository.save(toCustomer);
	}
	
}
