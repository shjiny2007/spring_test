package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;
import spring.model.team.TeamService;
import spring.utility.webtest.Utility;

@Controller
public class TeamController {
	
	@Autowired
	private TeamDAO dao;
	
	@Autowired
	private TeamService mgr;
	
	@RequestMapping("/team/delete")
	public String delete(int no, Model model) {
		
		boolean dflag = dao.checkRefnum(no);

		boolean flag = false;
		if(dflag==false){
			flag = dao.delete(no);
			
		model.addAttribute("flag", flag);
		model.addAttribute("dflag", dflag);
		}
		return "/team/delete";
	}
	
	@RequestMapping(value="/team/reply", method=RequestMethod.POST)
	public String reply(TeamDTO dto, Model model, HttpServletRequest request) {
		
		boolean flag = mgr.reply(dto);
		
		if(flag) {
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "redirect:/team/list";
			
		}else {
			return "/error/error";
			
		}
		
	}
	
	@RequestMapping(value="/team/reply", method=RequestMethod.GET)
	public String reply(int no, Model model) {
		
		TeamDTO dto = dao.replyRead(no);
		
		model.addAttribute("dto", dto);
		
		return "/team/reply";
	}
	
	@RequestMapping(value="/team/update", method=RequestMethod.POST)
	public String update(TeamDTO dto, Model model, HttpServletRequest request) {
		
		boolean flag = dao.update(dto);
		if(flag) {
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "redirect:/team/list";
			
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/team/update", method=RequestMethod.GET)
	public String update(int no, Model model) {
		
		TeamDTO dto = dao.read(no);
		
		model.addAttribute("dto", dto);
		
		return "/team/update";
	}
	
	@RequestMapping("/team/read")
	public String read(int no, Model model) {
		
		TeamDTO dto = dao.read(no);
		
		model.addAttribute("dto", dto);
		
		return "/team/read";
	}
	
	@RequestMapping(value="/team/create", method=RequestMethod.POST)
	public String create(TeamDTO dto, Model model) {
		
		boolean flag = dao.create(dto);
		
		if(flag) {
			return "redirect:/team/list";
		
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/team/create", method=RequestMethod.GET)
	public String create() {
		
		return "/team/create";
	}
	
	@RequestMapping("/team/list")
	public String list(HttpServletRequest request) {
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total")) word = "";
		
		//페이징관련 처리
		int nowPage = 1;
		int recordPerPage = 5;
		
		if(request.getParameter("nowPage")!=null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		
		//db가져올 레코드 시작번호, 끝번호
		int sno = (nowPage-1) * recordPerPage +1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		List<TeamDTO> list = dao.list(map);
		
		int totalRecord = dao.total(map);
		
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		return "/team/list";
	}
}
