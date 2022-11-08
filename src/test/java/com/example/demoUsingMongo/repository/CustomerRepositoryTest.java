package com.example.demoUsingMongo.repository;
import com.example.demoUsingMongo.domain.Address;
import com.example.demoUsingMongo.domain.Customer;
import com.sun.jdi.PrimitiveValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {
    @Autowired
private CustomerRepository customerRepository;
private Address address;
private Customer customer;

@BeforeEach
public void setUp(){
    address = new Address("delhi","New delhi","India","1234");
    customer = new Customer(12,"Nikita","nik@123",address);
}

@AfterEach
public void tearDown(){
    address = null;
    customer = null;
    customerRepository.deleteAll();
}
@Test
@DisplayName("Test case for saving customer object")
void givenCustomerToSaveShouldReturnSavedCustomer(){
    customerRepository.save(customer);
    Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
    assertNotNull(customer1);
    assertEquals(customer.getCustomerId(),customer1.getCustomerId());
}
}
