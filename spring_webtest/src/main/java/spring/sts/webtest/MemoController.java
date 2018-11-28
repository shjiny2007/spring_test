package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.utility.webtest.Utility;

@Controller
public class MemoController {
	
	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoService ser;
	
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.GET)
	public String delete(int memono, Model model) {	
		boolean flag=dao.refnumCheck(memono);
		model.addAttribute("flag", flag);
		
		return "/memo/delete";
	}
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.POST)
	public String delete(int memono, HttpServletRequest request) {
		
		boolean flag=dao.delete(memono);	
		if(flag) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}

	
	@RequestMapping(value="memo/reply", method=RequestMethod.GET)
	public String reply(int memono, Model model) {		
		MemoDTO dto = dao.replyRead(memono);		
		model.addAttribute("dto", dto);
		
		return "/memo/reply";
	}
	
	@RequestMapping(value="memo/reply", method=RequestMethod.POST)
	public String reply(MemoDTO dto, HttpServletRequest request) {		

		dto.setMemono(Integer.parseInt(request.getParameter("memono")));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		dto.setGrpno(Integer.parseInt(request.getParameter("grpno")));
		dto.setIndent(Integer.parseInt(request.getParameter("indent")));
		dto.setAnsnum(Integer.parseInt(request.getParameter("ansnum")));
		
		boolean flag=ser.reply(dto);
		if(flag) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="memo/update", method=RequestMethod.GET)
	public String update(int memono, Model model) {
		MemoDTO dto = dao.read(memono);
		model.addAttribute("dto", dto);	
		return "/memo/update";
	}
	
	@RequestMapping(value="memo/update", method=RequestMethod.POST)
	public String update(MemoDTO dto, HttpServletRequest request) {	
		
		dto.setMemono(Integer.parseInt(request.getParameter("memono")));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		boolean flag=dao.update(dto);
		if(flag) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/memo/read")
	public String read(int memono, Model model) {
		
		dao.upViewcnt(memono);				
		MemoDTO dto = dao.read(memono);
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");//not null이기 때문에 if없이 써도 가능

		dto.setContent(content);
		model.addAttribute("dto", dto);
		
		return "/memo/read";
	}
	
	
	
	
	
	
	@RequestMapping(value="/memo/create", method=RequestMethod.GET)
	public String create() {
		
		return "/memo/create";
	}
	
	
	@RequestMapping(value="/memo/create", method=RequestMethod.POST)
	public String create(MemoDTO dto) {
		if(dao.create(dto)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}

	}
	
	
	
	
	
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request) {
		
		//검색관련
		String col=Utility.checkNull(request.getParameter("col")); 
		String word=Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total")) word="";		
				
		//paging 관련
		int nowPage=1; //현재 보여질 페이지 정보 
		int recordPerPage=5; 
		
		if(request.getParameter("nowPage")!=null){ //nowpage가 null이 아니면 할당을 해라.
			nowPage=Integer.parseInt(request.getParameter("nowPage"));}
		//Integer.parseInt : 넘버포멧입셋션이 걸린다.
		
		//DB에서 가져올 레코드 순번
		int sno=((nowPage-1)*recordPerPage)+1; //공식
		int eno=nowPage *recordPerPage;
		
		Map map=new HashMap();
		map.put("col",col);
		map.put("word",word);
		map.put("sno", sno);
		map.put("eno",eno);
	
		
		List<MemoDTO> list=dao.list(map);		
	
		//전체 레코드 갯수(col, word) :  col,word를 가져가서 똑같이 비교해야 한다.
		int totalRecord=dao.total(map);
		String paging =Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		//결과를 request.로 저장해서 view페이지로 이동
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		
		return "/memo/list";
	}

}
