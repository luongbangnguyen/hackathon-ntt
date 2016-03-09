package org.spring.mongodb.config;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.vn.ntt.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringbootMongodbApplicationTests {

	@Autowired
	private CustomerRepository cusRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveCustomer(){
		Customer cus = cusRepository.save(new Customer());
		assertNotNull(cus);
	}
	

}