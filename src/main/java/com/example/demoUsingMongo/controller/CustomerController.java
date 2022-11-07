package com.example.demoUsingMongo.controller;

import com.example.demoUsingMongo.domain.Customer;
import com.example.demoUsingMongo.exception.CustomerNotFoundException;
import com.example.demoUsingMongo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerdata/v1")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/postdata")
    public ResponseEntity<?> saveCustomerData(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);

    }

    @GetMapping("/getdata")
    public ResponseEntity<?> fetchAllCustomer() throws Exception {
        return new ResponseEntity<>(customerService.getAllCustomerData(), HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteData/{customerId}")
    public ResponseEntity<?> deleteData(@PathVariable int customerId) throws CustomerNotFoundException, Exception {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("/getDataByCity/{city}")
    public ResponseEntity<?> getDataByCity(@PathVariable String city) throws ClassNotFoundException,Exception{
        return new ResponseEntity<>(customerService.getAllCustomerByCity(city),HttpStatus.FOUND);
    }
}