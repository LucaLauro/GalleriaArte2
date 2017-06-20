package it.uniroma3.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.spring.model.Admin;
import it.uniroma3.spring.model.Ruolo;
import it.uniroma3.spring.service.AdminService;
import it.uniroma3.spring.service.AutoreService;
import it.uniroma3.spring.service.RuoloService;

@Controller
public class AdminController {
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private AdminService adminService;
	@Autowired 
	private RuoloService ruoloService;
	
	
	
	 
	 @GetMapping("/formAdmin")
	    public String showFormAdmin(Admin admin) {
			return "formAdmin";
	}
	 
	 @PostMapping("/formAdmin")
	    public String checkAmministratoreInfo(@Valid @ModelAttribute Admin admin, 
	    									BindingResult bindingResult, Model model) {
	        if (bindingResult.hasErrors()) {
	            return "formAdmin";
	        }
	        else {
	        	
				admin.setEnabled(true);
	        	model.addAttribute(admin);
	            adminService.add(admin);
	            Ruolo ruolo = new Ruolo();
	            ruolo.setRuolo("ROLE_ADMIN");
	            ruolo.setUsername(admin.getUsername());
	            ruoloService.add(ruolo);
	            model.addAttribute("autori", autoreService.findAll());
	        return "index";
	     }
	 }
	 

}