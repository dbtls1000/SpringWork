package com.biz.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.book.model.BookVO;
import com.biz.book.model.MemberVO;
import com.biz.book.service.BookService;
@Controller
public class BookController {
	
	@Autowired
	BookService bService;
	
	@RequestMapping(value="book-view",method=RequestMethod.GET)
	public String book_view(Model model) {
		
		List<BookVO> books= bService.selectAll();
		
		model.addAttribute("BOOKS",books);
		model.addAttribute("BODY","BOOK-LIST");
		return "home";
				
	}
	@RequestMapping(value="book-input",method=RequestMethod.GET)
	public String book_input(Model model,HttpSession httpSession) {
		MemberVO memberVO = (MemberVO) httpSession.getAttribute("USER");
		if(memberVO == null) {
			return "redirect:login";
		} else {
			model.addAttribute("BODY","BOOK-INPUT");
			return "home";
		}
		
		
	}
	@RequestMapping(value="book-input",method=RequestMethod.POST)
	public String book_input(@ModelAttribute BookVO bookVO, Model model) {
		
		int ret = bService.insert(bookVO);
		
		/* 데이터를 추가한 후 추가된 새로운 리스트를 보고싶어서
		 * 전체 데이터를 selectAll한 후
		 * 그 데이터를 BOOKS에 담고
		 * BODY에는 "BOOK-LIST"를 담아서
		 * "home"으로 return을 수행한다.
		 */
//		List<BookVO> bList = bService.selectAll();
//		model.addAttribute("BOOK",bList);
//		model.addAttribute("BODY","BOOK-VIEW");
		/*
		 * 그런데 코드를 작성하고 보니 이미 리스트를 보는 method가 존재한다
		 * 그래서 그 method를 호출하여 같은 일을 수행하려고 했더니
		 * 문제가 발생한다.
		 * 
		 * spring환경에서는 그럴 경우 response를 통해 웹브라우저에게 해당 url을
		 * 다시 request하도록 요청할 수 있다
		 * 그 키워드가 redirect:...이다
		 */
		if(ret > 0) {
			//.../book-view라고 입력하고 enter를 눌러라
			return "redirect:book-view";
		} else {
			return "redirect:book-input";
		}
		// return "home";
		
	}
	/*
	 * request의 변수를 수신하는 방법
	 * url?변수=값 형태로 전달된 request는 변수이름과 같은 형태의
	 * 매개변수를 지정하여 받는다 이때 변수는거의 대부분 String
	 * String 변수 형식으로 매개변수를 지정한다.
	 * 단, 실제 사용할 변수의 형이 문자열이 아닌경우
	 * 
	 * 문자열 -> 사용할 type으로 변환이 가능하면
	 * 사용할 type으로 변수의 형을 지정할 수 있다.
	 * 
	 * 이때는 가급적 @RequestParam을 선언하여 준다
	 */
	@RequestMapping(value="book-detail",method=RequestMethod.GET)
	public String book_detail(@RequestParam long b_seq, Model model) {
		BookVO bookVO = bService.findBySeq(b_seq);
		model.addAttribute("BOOK",bookVO);
		model.addAttribute("BODY","BOOK-VIEW");
		return "home";
	}
	@RequestMapping(value="book-delete",method=RequestMethod.GET)
	public String book_delete(@RequestParam long b_seq, Model model) {
		int ret = bService.delete(b_seq);
		return "redirect:book-view";
	}
	/*
	 * update 수행 1
	 * 1.전달된 b_seq값으로 데이터를 추출하고,
	 * 2. 그 데이터를 입력 form에 전달하고
	 * 3. 입력폼에서 데이터를 보면서 편집할 수 있도록 한다.
	 */
	@RequestMapping(value="book-update",method=RequestMethod.GET)
	public String book_update(@RequestParam long b_seq, Model model) {
		BookVO bookVO = bService.findBySeq(b_seq);
		model.addAttribute("BOOK",bookVO);
		model.addAttribute("BODY","BOOK-INPUT");
		return "home";
	}
	@RequestMapping(value="book-update",method=RequestMethod.POST)
	public String book_update(@ModelAttribute BookVO bookVO, Model model) {
		
		int ret = bService.update(bookVO);
		return "redirect:book-view";
	}
}
