package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.iolist.model.CompanyVO;
import com.biz.iolist.service.CompanyService;

@Controller
@RequestMapping(value="/company")
public class CompanyController {
	@Autowired
	CompanyService cService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam String c_name, Model model) {
		List<CompanyVO> compList = cService.findByCName(c_name);
		model.addAttribute("COMP_LIST",compList);
		return "ajax_body/company/list";
	}
	
	
	
	
	
}
