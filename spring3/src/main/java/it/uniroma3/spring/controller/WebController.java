package it.uniroma3.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.spring.model.Quadro;
import it.uniroma3.spring.service.QuadroService;
 
@Controller
public class WebController {
	@Autowired
   QuadroService quadroservice;
    @RequestMapping(value={"/","index"})
        public String home(Model model){
    	
    	Iterable<Quadro> source = this.quadroservice.findAll();
		List<Quadro> quadri = new ArrayList<>();
		source.forEach(quadri::add);
		model.addAttribute("quadri",quadri);
            return "index";
        }
    
    
  
}
