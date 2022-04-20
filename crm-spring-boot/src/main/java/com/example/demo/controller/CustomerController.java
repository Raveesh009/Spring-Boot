package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String getAllCustomers(Model model) {

        List<Customer> list = customerService.getAllCustomer();
        model.addAttribute("customers", list);
        return "customer-list";
    }

    @GetMapping("/showFormForAdd")
    public String addCustomer(Model model) {

        Customer theCustomer = new Customer();
        model.addAttribute("CUSTOMER", theCustomer);
        return "customer-form";
    }

    @GetMapping ("/showFormForUpdate")
    public String update(@RequestParam("id")int id, Model model) {

        Customer theCustomer = customerService.getCustomer(id);
        model.addAttribute("CUSTOMER", theCustomer);

        return "customer-form";
    }

    @GetMapping("/showFormForDetails")
    public String details(@RequestParam("id")int id, Model model) {

        Customer customer = customerService.getCustomer(id);
        model.addAttribute("CUSTOMER", customer);

        return "details-form";
    }

    @PostMapping("/back")
    public String back() {

        return "redirect:/customers/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("CUSTOMER")Customer customer) {

        Customer c = customerService.createCustomer(customer);
        System.out.println(c);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id")int id) {

        customerService.deleteCustomerById(id);
        return "redirect:/customers/list";
    }
}
