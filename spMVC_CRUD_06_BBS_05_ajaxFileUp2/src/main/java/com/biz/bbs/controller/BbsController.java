package com.biz.bbs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.bbs.model.BbsDto;
import com.biz.bbs.model.BbsReqDto;
import com.biz.bbs.model.MemberVO;
import com.biz.bbs.service.AjaxFileService;
import com.biz.bbs.service.BbsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("bbsReqDto")
@RequestMapping(value="/bbs")
public class BbsController {
	@Autowired
	BbsService bbsService;
	@Autowired	
	AjaxFileService afService;
	/*
	 * 현재의 controller내의 어떤 메서드에서 BbsVO를 객체로 취급하여
	 * 값을 setter,getter하려고 시도할때 만약 객체가 초기화 되지 않아 nullpointerException이
	 * 발생하려고하면 이 메서드가 자동으로 호출되어 bbsVO객체를 생성 및 초기화 한다.
	 */
	@ModelAttribute("bbsReqDto")
	public BbsReqDto newBbsVO() {
		return new BbsReqDto();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BbsDto> bbsList = bbsService.bbsList();
		
		model.addAttribute("LIST",bbsList);
		model.addAttribute("BODY","BBS_LIST");
		return "home";
	}
	@RequestMapping(value="/album",method=RequestMethod.GET)
	public String album(Model model) {
		
		List<BbsDto> bbsList = bbsService.bbsListForFile();
		
		model.addAttribute("ALBUM",bbsList);
		model.addAttribute("BODY","BBS_ALBUM");
		return "home";
	}
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write(@ModelAttribute("bbsReqDto") BbsReqDto bbsReqDto,
						Model model,
						HttpSession httpSession) {
//		LocalDate ld = LocalDate.now();
//		LocalTime lt = LocalTime.now();
		MemberVO memberVO = (MemberVO) httpSession.getAttribute("LOGIN");
		if(memberVO != null)
		bbsReqDto.setBbs_auth(memberVO.getM_userid());
		
		LocalDateTime ldt = LocalDateTime.now();
		String curDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String curTime = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
		
		bbsReqDto.setBbs_date(curDate);
		bbsReqDto.setBbs_time(curTime);
		
		model.addAttribute("bbsReqDto",bbsReqDto);
		model.addAttribute("BODY","BBS_WRITE");
		return "home";
	}
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write1(@ModelAttribute("bbsReqDto") @Valid BbsReqDto bbsReqDto,
							BindingResult result,
							SessionStatus sStatus,
//							@RequestParam("bbs_file") List<MultipartFile> files
							Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("BODY","BBS_WRITE");
			return "home";
		}
		
		int ret = bbsService.insert(bbsReqDto);
		/*
		 * SessionAttribute를 사용할때
		 * dto에 한번 담긴 값은 웹브라우저가 열려있는동안 항상 유지되는 성질이 있다.
		 * 그래서,write update를 수행한 후에는 SessionStatus의 setComplete() method를 호출해서
		 * dto에 담긴 값을 비워 주어야 한다.
		 */
		sStatus.setComplete();
		return "redirect:/bbs/list";
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam long bbs_seq, Model model) {
		BbsDto bbsDto = bbsService.getContent(bbs_seq);
		
		model.addAttribute("BBS",bbsDto);
		model.addAttribute("BODY","BBS_VIEW");
		return "home";
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(@RequestParam long bbs_seq, Model model,HttpSession httpSession) {
		MemberVO memberVO = (MemberVO) httpSession.getAttribute("LOGIN");
		if(memberVO == null) {
			model.addAttribute("LOGIN_MSG","LOGIN");
			return "redirect:/member/login";
		} 
		
		BbsDto bbsDto = bbsService.getContent(bbs_seq);
		if(!memberVO.getM_userid().equalsIgnoreCase(bbsDto.getBbs_auth())) {
			model.addAttribute("LOGIN_MSG","AUTH");
			return "redirect:/member/login";
		}
		model.addAttribute("bbsVO",bbsDto);
		model.addAttribute("BODY","BBS_WRITE");
		return "home";
	}
//	@RequestMapping(value="/update",method=RequestMethod.GET)
//	public String update(@RequestParam long bbs_seq, Model model) {
//		BbsVO bbsVO = bbsService.findBySeq(bbs_seq);
//		model.addAttribute("BBS",bbsVO);
//		model.addAttribute("BODY","BBS_WRITE");
//		return "home";
//	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("bbsReqDto") BbsReqDto bbsReqDto,
						SessionStatus sStatus,
						Model model) {
		
		
		int ret = bbsService.update(bbsReqDto);
		sStatus.setComplete();
		return "redirect:/bbs/list";
	}
	
	/*
	 * PathVariable
	 * Get 방식으로 서버에 데이터를 보낼때
	 * ?변수=값 형식으로 많이 사용을 한다 하지만 이 방식은 보안에 취약점이 있다
	 * 
	 * path/값 형식의 주소처럼 서버로 데이터를 보내고
	 * 컨트롤러에서는 주소의 일부를 값으로 인식하여 변수에 할당하는 방식이다
	 * 
	 */
	@RequestMapping(value="/reply/{bbs_seq}",method=RequestMethod.GET)
	public String reply(@PathVariable long bbs_seq,
						@ModelAttribute("bbsReqDto") BbsReqDto bbsReqDto,
						Model model) {
		BbsDto bbsDto = bbsService.getContent(bbs_seq);
		
		LocalDateTime ldt = LocalDateTime.now();
		String curDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String curTime = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
		
		bbsReqDto.setBbs_date(curDate);
		bbsReqDto.setBbs_time(curTime);
		
		// 답글달기에서는 bbs_main_seq에 원글의 bbs_seq값을 포함해야한다.
		bbsReqDto.setBbs_main_seq(bbs_seq);
		bbsReqDto.setBbs_title("re : " + bbsDto.getBbs_title());
		
		model.addAttribute("bbsReqDto",bbsReqDto);
		model.addAttribute("BODY","BBS_WRITE");
		return "home";
	}
	
	@RequestMapping(value="/reply/{bbs_seq}",method=RequestMethod.POST)
	public String reply_write(@PathVariable long bbs_seq,
								@ModelAttribute("bbsReqDto") BbsReqDto bbsReqDto,
								SessionStatus sStatus,
								Model model) {
		//bbsVO.setBbs_main_seq(bbs_seq);
		log.debug("main_seq : " + bbsReqDto.getBbs_main_seq());
		bbsService.insert(bbsReqDto);
		sStatus.setComplete();
		return "redirect:/bbs/list";
	}
	
//	@RequestMapping(value="/delete",method=RequestMethod.GET)
//	public String delete(@RequestParam long bbs_seq,Model model) {
//		
//		bbsService.delete(bbs_seq);
//		return "redirect:/bbs/list";
//	}
	
	/*
	 * 게시글을 삭제하면
	 * 1.첨부파일확인 > 첨부파일삭제
	 * 2.첨부파일 table 정보삭제
	 * 3.게시글 삭제
	 */
	@RequestMapping(value="/delete/{bbs_seq}",method=RequestMethod.GET)
	public String delete(@PathVariable long bbs_seq) {
		int ret = bbsService.delete(bbs_seq);
		return "redirect:/bbs/list";
	}
	
}
