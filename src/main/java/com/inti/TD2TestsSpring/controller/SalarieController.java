package com.inti.TD2TestsSpring.controller;

import com.inti.TD2TestsSpring.repository.SalarieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalarieController
{
	@Autowired
	SalarieRepository salarieRepository;
	
	@GetMapping("/salaries")
	public String getAllSalaries(Model m)
	{
		m.addAttribute("listeSalarie", salarieRepository.findAll());
		return "salaries";
	}
}
