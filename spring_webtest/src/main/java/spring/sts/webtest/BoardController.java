package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.board.BReplyDAO;
import spring.model.board.BReplyDTO;
import spring.model.board.BoardDAO;
import spring.model.board.BoardDTO;
import spring.model.board.BoardMgr;
import spring.utility.webtest.Utility;


@Controller
public class BoardController {
	
	@Autowired
	private BReplyDAO rdao;
	
	@Autowired
	private BoardDAO dao;

	@Autowired
	private BoardMgr mgr;
	
	@RequestMapping("/board/rdelete")
	public String rdelete(int rnum,int num, Model model, String nowPage,String col,
			String word, int nPage) {
		
		int total = rdao.total(num);
		int totalPage = (int) Math.ceil((double)total/3);
		
		
		if(rdao.delete(rnum)) {
			if(nPage!=1 && nPage==totalPage && total%3==1) {
				nPage =nPage-1;
			}
			model.addAttribute("num",num);
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			model.addAttribute("nPage",nPage);
			return "redirect:/board/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/board/rupdate")
	public String rupdate(BReplyDTO dto, Model model, String nowPage,String col,
			String word, String nPage) {
		
		if(rdao.update(dto)) {
			model.addAttribute("num",dto.getNum());
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			model.addAttribute("nPage",nPage);
			return "redirect:/board/read";	
		}else {
			return "/error/error";
		}
	}
	
	
	@RequestMapping("/board/rcreate")
	public String rcreate(BReplyDTO dto,Model model,
			String nowPage, String col, String word) {
		
		if(rdao.create(dto)) {
			model.addAttribute("num",dto.getNum());
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			return "redirect:/board/read";	
		}else {
			return "/error/error";
		}
	}
	
	
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(int num, String passwd, String oldfile, 
			HttpServletRequest request, Model model){
		
		String basePath = request.getRealPath("/storage");
		
		Map map = new HashMap();
		map.put("num", num);
		map.put("passwd", passwd);
		
		boolean pflag = dao.passwdCheck(map);
		if(pflag) {
			dao.delete(num);
			Utility.deleteFile(basePath, oldfile);
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "redirect:/board/list";
			
		}else {
			return "/error/passwdError";
		}

	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(int num, Model model) {

		boolean flag = dao.checkRefnum(num);

		model.addAttribute("flag", flag);

		return "/board/delete";
	}

	@RequestMapping(value = "/board/reply", method = RequestMethod.POST)
	// 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
	public String reply(BoardDTO dto, Model model, HttpServletRequest request) {

		String basePath = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));

		dto.setFilesize((int) dto.getFilenameMF().getSize());

		dto.setIp(request.getRemoteAddr());

		boolean flag = mgr.reply(dto);

		if (flag) {
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "redirect:/board/list";
		} else {
			return "/error/error";

		}
	}

	@RequestMapping(value = "/board/reply", method = RequestMethod.GET)
	public String reply(int num, Model model) {

		BoardDTO dto = dao.readReply(num);

		model.addAttribute("dto", dto);

		return "/board/reply";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(BoardDTO dto, HttpServletRequest request, String oldfile, Model model) {
		String basePath = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
		dto.setFilesize((int) dto.getFilenameMF().getSize());

		Map map = new HashMap();
		map.put("num", dto.getNum());
		map.put("passwd", dto.getPasswd());

		boolean pflag = dao.passwdCheck(map);
		boolean flag = false;

		if (pflag) {
			flag = dao.update(dto);
		}

		String str = null;
		if (pflag) {
			if (flag) {
				if (dto.getFilesize() > 0) {
					Utility.deleteFile(basePath, oldfile);
				}
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				str = "redirect:/board/list";
			} else {
				if (dto.getFilesize() > 0) {
					Utility.deleteFile(basePath, dto.getFilename());
				}
				str = "/error/error";
			}
		} else {
			if (dto.getFilesize() > 0) {
				Utility.deleteFile(basePath, dto.getFilename());
			}
			str = "/error/passwdError";
		}
		return str;
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(int num, Model model) {

		BoardDTO dto = dao.read(num);

		model.addAttribute("dto", dto);

		return "/board/update";
	}

	@RequestMapping("/board/read")
	public String read(int num, Model model,HttpServletRequest request) {
		dao.upCount(num);
		BoardDTO dto = dao.read(num);

		model.addAttribute("dto", dto);
		model.addAttribute("content", dto.getContent().replaceAll("\r\n", "<br>"));
		
		// 댓글 처리
		String url = "read"; //댓글페이지에 매개변수
		int nPage = 1;
		if(request.getParameter("nPage")!=null) {
			nPage=Integer.parseInt(request.getParameter("nPage"));
		}
		int recordPerPage=3;
		int sno = ((nPage-1)*recordPerPage)+1;
		int eno = nPage*recordPerPage;
		
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("num", num);
		
		List<BReplyDTO> rlist = rdao.list(map);
		int total = rdao.total(num);
		
		int nowPage= Integer.parseInt(request.getParameter("nowPage"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		String paging = Utility.boardpaging(total, nowPage, recordPerPage, col, word, 
				num, nPage, url);
		
		model.addAttribute("rlist",rlist);
		model.addAttribute("nPage",nPage);
		model.addAttribute("paging",paging);

		return "/board/read";
	}

	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public String create(BoardDTO dto, HttpServletRequest request) {
		String upDir = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
		dto.setFilesize((int) dto.getFilenameMF().getSize());
		dto.setIp(request.getRemoteAddr());

		boolean flag = dao.create(dto);

		if (flag) {
			return "redirect:/board/list";
		} else {
			return "/error/error";
		}

	}

	@RequestMapping(value = "/board/create", method = RequestMethod.GET)
	public String create() {

		return "/board/create";
	}

	@RequestMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if (col.equals("total"))
			word = "";

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		// 1. model 사용
		List<BoardDTO> list = dao.getList(map);
		int total = dao.getTotal(map);
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		// 2. request 저장
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);

		return "/board/list";
	}

}
