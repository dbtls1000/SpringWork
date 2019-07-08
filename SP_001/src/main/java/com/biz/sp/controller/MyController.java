package com.biz.sp.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sp.service.HelloService;

/*
 * 스프링에게 지금부터 MyController를 관리해달라고 요청
 */
@Controller
public class MyController {
	
	/*
	 * bean으로 설정된 클래스를 사용하도록 연결해달라
	 */
	@Autowired
	HelloService helloService;
	
	@RequestMapping(value="aa",method=RequestMethod.GET)
	public String myHome(Model model) {
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();
		
		model.addAttribute("date",ld.toString());
		model.addAttribute("time",lt.toString());
		String helloMsg = helloService.getHello();
		model.addAttribute("hello",helloMsg);
		
		return "aahome";
	}
	/*
	 * /sp/stform으로 요청을하면
	 * /views/stform.jsp를 보여주고
	 */
	@RequestMapping(value="stform",method=RequestMethod.GET)
	public String st(Model model) {
		
		return "stform";
	}
	/*
	 * stfrom.jsp의 input tag에 값을 입력한 후 전송을 누르면
	 *  /sp.st요청에 값을 실어서 서버로 전송되며
	 * 각각의 값은 st_name, st_tel 매개변수에서 받는다
	 */
	@RequestMapping(value="st",method=RequestMethod.GET)
	public String st(String st_name,String st_tel,Model model) {
		System.out.println("여기는 GET수신하는 곳");
		model.addAttribute("name",st_name);
		model.addAttribute("tel",st_tel);
		return "sthome";
	}
	@RequestMapping(value="st",method=RequestMethod.POST)
	public String st_post(String st_name,String st_tel,Model model) {
		
		System.out.println("여기는 POST수신하는 곳");
		model.addAttribute("name",st_name);
		model.addAttribute("tel",st_tel);
		return "sthome";
	}
}
