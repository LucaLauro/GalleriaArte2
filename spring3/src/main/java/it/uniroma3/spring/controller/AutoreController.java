package it.uniroma3.spring.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.service.AutoreService;


@Controller
public class AutoreController  {
	
    @Autowired
    private AutoreService autoreservice; 

    @GetMapping("/autore")
    public String showForm(Autore autore) {
        return "formAutore";
    }

    @PostMapping("/autore")
    public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
    									BindingResult bindingResult, Model model)  {
    	
        if (bindingResult.hasErrors()) {
            return "formAutore";
           
        }
        else {
        	SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        	dateFormat.setLenient(true);
        	
        	try {
        		Date nascita=dateFormat.parse(autore.getDataDiNascita());
        		String dataDiNascita=dateFormat.format(nascita);
        	
        	
        	model.addAttribute("dataDiNascita", dataDiNascita);}
        	catch(ParseException e){
        		bindingResult.rejectValue("dataDiNascita", "error.autore", "Inserisci una data");
        		return "formAutore";
        	}
        	try{
        		Date morte=dateFormat.parse(autore.getDataDiMorte());
        		String dataDiMorte=dateFormat.format(morte);
        		model.addAttribute("dataDiMorte", dataDiMorte);
        		model.addAttribute(autore);
        	}catch(Exception e){
        		model.addAttribute(autore);
        	}
        	
            autoreservice.add(autore); 
        }
        return "mostraAutore";
    }
    @RequestMapping("/autori")
    public String listaAutore(Model model){
    	Iterable<Autore> source = this.autoreservice.findAll();
    	List<Autore> autori = new ArrayList<>();
    	source.forEach(autori::add);
    	model.addAttribute("autori",autori);
    	return"autori";
    }
    @RequestMapping("/autoriAdmin")
    public String listaAutore2(Model model){
    	Iterable<Autore> source = this.autoreservice.findAll();
    	List<Autore> autori = new ArrayList<>();
    	source.forEach(autori::add);
    	model.addAttribute("autori",autori);
    	return"autori";
    }
    @GetMapping("/autore/elimina")
	public ModelAndView deleteOpera(@RequestParam("id")long id, Model model){
		autoreservice.delete(id);
		return new ModelAndView("autori");
	}
}
