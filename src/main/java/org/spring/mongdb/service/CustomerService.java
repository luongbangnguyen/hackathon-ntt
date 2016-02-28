package org.spring.mongdb.service;

import org.spring.mongdb.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends ModelService<Customer>{

	Page<Customer> getCustomers(String keyword, Pageable pageable);
	Page<Customer> getCustomersMatchFistNameAndLastName(String fistname,
			String lastname, Pageable pageable);
}
