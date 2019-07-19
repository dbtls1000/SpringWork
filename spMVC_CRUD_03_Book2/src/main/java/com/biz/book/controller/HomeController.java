package com.biz.book.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/*
	 * logger level
	 * ALL,TRACE,DEBUG,INFO,WARN,ERROR,FATAL,OFF
	 * 만약 log level을 warn설정하면 all trace debug info를 무시
	 * info로 설정하면 all trace debug를 무시
	 */
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// 콘솔을 통해서 어떤 메시지를 표시하거나 변수가 값을 확인하고자 할때
		// sysout을 사용하지 않고
		logger.debug("나의 메세지");
		logger.debug("log debug");
		logger.debug("log debug");
		logger.debug("",(30+40));
		logger.debug("표시할 값 {}",30+40);
		logger.debug("표시할 값 {} + {} ={}",30,40);
		logger.info("log info");
		logger.warn("log warn");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
