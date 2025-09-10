package com.learn.learn_one_to_many.controller;

import com.learn.learn_one_to_many.entity.Customer;
import com.learn.learn_one_to_many.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}/edit")
    public String editCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        System.out.println("editCustomer: id -> " + id);
        Customer customer = customerService.getCustomerById(id);
        System.out.println("editCustomer: customer -> " + customer);
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/api/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id) {
        System.out.println("editCustomer: id -> " + id);
        customerService.deleteCustomerById(id);
        return "redirect:/api/customers";
    }

}
