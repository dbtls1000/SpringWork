package com.biz.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.bbs.model.MenuDto;
import com.biz.bbs.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	MenuService menuService;
	/*
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession httpSession, Model model) {
		log.debug("여기는 홈 메서드 입니다");
		List<MenuDto> menuList = menuService.getDBMenu();
		httpSession.setAttribute("MENUS",menuList);
		
//		model.addAttribute("MENUS",menuService.makeMenu());
		return "home";
	}
	
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	public String menu(Model model) {
		List<MenuDto> menuList = menuService.getDBMenu();
		
		model.addAttribute("MENUS",menuList);
		return "include/include-header";
	}
}
