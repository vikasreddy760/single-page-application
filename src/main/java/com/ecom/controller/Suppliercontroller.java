package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.dao.SupplierDAO;
import com.ecom.model.Supplier;



	@Controller
	public class Suppliercontroller {
		
		@Autowired
		private SupplierDAO supplierDAO;
		
		@RequestMapping(value="/",method = RequestMethod.GET)
		public String landPage(@ModelAttribute("supplier")Supplier supplier,BindingResult result,Model model)
		{
			model.addAttribute("supplierList",supplierDAO.list());
			return "Supplier";
			
		}
		 
		@RequestMapping(value="/Supplier")
		public String getSupplierDetails(@ModelAttribute("supplier")Supplier supplier,BindingResult result,Model model)
		{
		
			model.addAttribute("supplierList",supplierDAO.list());
			return "Supplier";
		}
		
		@RequestMapping(value="/addItem",method = RequestMethod.POST)
	public String addSupplierDetails(@ModelAttribute("supplier") Supplier p){
			
			this.supplierDAO.saveOrUpdate(p);
			return "redirect:/Supplier";	
		}
		
		@RequestMapping(value="/ItemById/{id}",method = RequestMethod.GET)
		public String editProductDetails(@PathVariable("id") int id,RedirectAttributes redirectAttributes)
		{
			redirectAttributes.addFlashAttribute("supplier", supplierDAO.get(id));
			return "redirect:/Supplier";
			
	}
		@RequestMapping(value="/deleteSupplierById/{id}",method = RequestMethod.GET)
		public String deleteItem(@PathVariable("id") int id,Supplier supplier)
		{
			supplierDAO.delete(supplier);
			return "redirect:/Supplier";
			
	}
	}