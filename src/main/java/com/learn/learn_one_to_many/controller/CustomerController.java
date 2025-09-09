package com.learn.learn_one_to_many.controller;

import com.learn.learn_one_to_many.entity.Customer;
import com.learn.learn_one_to_many.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/customers")
public class CustomerController {

    final private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "index";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute Customer customer) {
        System.out.println("addCustomer: " + customer);
        customer.getAddresses().forEach(add -> add.setCustomer(customer));
        customerService.addCustomer(customer);
        return "redirect:/api/customers";
    }

}
