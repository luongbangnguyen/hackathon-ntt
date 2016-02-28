package org.spring.mongdb.service;

import org.apache.commons.lang3.StringUtils;
import org.spring.mongdb.entity.Customer;
import org.spring.mongdb.entity.QCustomer;
import org.spring.mongdb.exeption.BusinessException;
import org.spring.mongdb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

@Service
@Transactional
public class CustomerServiceImpl extends ModelServiceImpl<Customer> implements CustomerService{

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository repository) {
		super(repository);
		this.customerRepository = repository;
	}


	@Override
	public Page<Customer> getCustomers(String keyword, Pageable pageable) {
		return customerRepository.findByFirstNameContainsOrLastNameContains(keyword, keyword, pageable);
	}


	@Override
	public Page<Customer> getCustomersMatchFistNameAndLastName(String fistname,
			String lastname, Pageable pageable) {
		if (StringUtils.isBlank(fistname) && StringUtils.isBlank(lastname)) {
			return customerRepository.findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		if (StringUtils.isNotBlank(fistname)) {
			builder.and(QCustomer.customer.firstName.eq(fistname));
		}

		if (StringUtils.isNoneBlank(lastname)) {
			builder.and(QCustomer.customer.lastName.eq(lastname));
		}
		return customerRepository.findAll(builder, pageable);
	}
	
}
