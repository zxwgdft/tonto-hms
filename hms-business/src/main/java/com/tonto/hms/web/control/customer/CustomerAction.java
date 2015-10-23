package com.tonto.hms.web.control.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tonto.hms.dao.support.Page;
import com.tonto.hms.model.Customer;
import com.tonto.hms.service.customer.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerAction {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/view")
	public ModelAndView view()
	{
		return new ModelAndView("customer/customer");
	}
	
	@RequestMapping("/page/{pageNo}/{pageSize}")
	public ModelAndView getHousePage(@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize,@ModelAttribute("customer")Customer customer)
	{
		Page<Customer> page=customerService.searchCustomerByPage(pageNo,pageSize>0?pageSize:20,customer);
		
		ModelAndView model=new ModelAndView("customer/customerPage");
		model.addObject("pager", page);
		model.addObject("customers", page.getResult());		
		return model;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String toAdd()
	{
		return "customer/customerAdd";
	} 
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView toAdd(@PathVariable("id") Integer id)
	{
		Customer customer=customerService.get(id);
		return new ModelAndView("customer/customerAdd","customer",customer);
	} 
}
