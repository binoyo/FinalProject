package com.lambton.project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambton.project.entity.Customer;
import com.lambton.project.services.CustomerService;
import com.lambton.project.validator.UserValidator;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Customer());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Customer userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	logger.warn("Binding failed: " + bindingResult);
        	
            return "registration";
        }

        logger.info("Saving customer: " + userForm);
        customerService.save(userForm);

        model.addAttribute("sucess", "The user " + userForm.getUsername() + " sucessfully registered.");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("userLogin") Customer userLogin, BindingResult bindingResult, Model model) {
        Customer c = customerService.findByUsername(userLogin.getUsername());
        if (c != null && c.getPassword().matches(userLogin.getPassword())) {
            return "redirect:/item/list";
        } else {
        	model.addAttribute("error", "Your username or password is invalid.");
        	return "login";
        }

    }
    
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "item/list";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (Model model) {
    	model.addAttribute("message", "You have been logged out successfully.");
        return "redirect:/login?logout";
    }
}
