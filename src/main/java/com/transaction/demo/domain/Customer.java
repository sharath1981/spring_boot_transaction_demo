package com.transaction.demo.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long customerNumber;
	private String name;
	private String city;
	private int age;
	private BigDecimal balance;
	public void debit(BigDecimal amount) {
		if(balance.compareTo(amount)<0) {
			throw new IllegalStateException("Insufficient  Balance...");
		}
		balance=balance.subtract(amount);
	}
	public void credit(BigDecimal amount) {
		balance=balance.add(amount);
	}
}

