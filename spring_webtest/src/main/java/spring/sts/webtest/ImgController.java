package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.img.imgDAO;
import spring.model.img.imgDTO;
import spring.model.img.imgService;
import spring.utility.webtest.Utility;



@Controller
public class ImgController {
	
	@Autowired
	private imgDAO dao;
	
	@Autowired
	private imgService ser;
	
	
	
	@RequestMapping(value="/img/delete", method=RequestMethod.GET)
	public String delete(int no, HttpServletRequest request) {	
		boolean flag=dao.checkRefnum(no);
		request.setAttribute("flag", flag);
		return "/img/delete";		
	}
	
	@RequestMapping(value="/img/delete", method=RequestMethod.POST)
	public String delete(HttpServletRequest request,int no, String passwd, String oldfile) {
		String upDir=request.getRealPath("/img/storage");
		
		Map map=new HashMap();
		map.put("no",no);
		map.put("passwd",passwd);
		
		boolean pflag=dao.passcheck(map);
		boolean flag=false;
		if(pflag){
			if(dao.delete(no)) {
			if(oldfile!=null)
				Utility.deleteFile(upDir, oldfile);
			return "redirect:/img/list";
			}else {
				return "/error/error";
			}
				
		}else {
			return "/error/passwdError";
		}

	}
	
	@RequestMapping(value="/img/update", method=RequestMethod.GET)
	public String update(int no, HttpServletRequest request) {

		imgDTO dto = dao.read(no);		
		request.setAttribute("dto", dto);		
		
		return "/img/update";
	}
	
	@RequestMapping(value="/img/update", method=RequestMethod.POST)
	public String update(imgDTO dto, HttpServletRequest request,String oldfile,Model model) {
		String upDir = request.getRealPath("/img/storage");
		Map map = new HashMap();
		map.put("no", dto.getNo()); 
		map.put("passwd", dto.getPasswd());
		
		int filesize=(int)dto.getFilenameMF().getSize();
		String img = "";
		boolean pflag = dao.passcheck(map);	
		
		if(pflag){
			if(filesize>0) {
				if(oldfile!=null){
					Utility.deleteFile(upDir, oldfile);
					img = Utility.saveFileSpring(dto.getFilenameMF(), upDir);
				}
			}		
			dto.setImg(img);
			boolean flag=dao.update(dto);
			
			if(flag) {
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));

				return "redirect:/img/list";
			}else {
				return "/error/error";
			}
		 }else {
			 return "/error/passwdError";
		 }		
	}

	
	@RequestMapping(value="/img/reply", method=RequestMethod.GET)
	public String reply(int no, HttpServletRequest request) {
		
		imgDTO dto = dao.replyread(no);		
		request.setAttribute("dto", dto);
		
		return "/img/reply";
	}
	
	@RequestMapping(value="/img/reply", method=RequestMethod.POST)
	public String reply(HttpServletRequest request, imgDTO dto) {	
		String upDir = request.getRealPath("/img/storage");		
		int size = (int)dto.getFilenameMF().getSize();
		String img = "";
		if(size>0){
			img = Utility.saveFileSpring(dto.getFilenameMF(),upDir);
		}

		dto.setImg(img);
		boolean flag=ser.reply(dto);
		
		if(flag) {
			return "redirect:/img/list";
		}else {
			if(img.equals("")) {
				Utility.deleteFile(upDir, img);
			}
			return "/error/error";
		}
		
	}

	
	@RequestMapping(value="/img/create", method=RequestMethod.GET)
	public String create() {
		
		return "/img/create";
	}
	
	@RequestMapping(value="/img/create", method=RequestMethod.POST)
	public String create(HttpServletRequest request,imgDTO dto) {
		String upDir = "/img/storage";
		upDir = request.getRealPath(upDir);
	
		String img =( Utility.saveFileSpring(dto.getFilenameMF(), upDir));

		dto.setImg(img);
		dto.setTitle(dto.getTitle());
		dto.setName(dto.getName());
		dto.setPasswd(dto.getPasswd());
		
		boolean flag= dao.create(dto);
		if(flag) {
			return "redirect:/img/list";
		}else {
			return "/error/error";
		}
		
	}

	
	@RequestMapping("/img/read")
	public String read(int no, HttpServletRequest request) {
		dao.upviewcnt(no);
		imgDTO dto = dao.read(no);
		List list=dao.imgRead(no);
		
		int[] noArr=(int[])list.get(0);
		String[] files=(String[])list.get(1);

		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("noArr", noArr);
		request.setAttribute("files", files);
		
		return "/img/read";
	}

	
	@RequestMapping("/img/list")
	public String list(HttpServletRequest request, Model model) {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total")) word="";
		
		int nowPage= 1;
		int recordPerPage = 10;
		
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		int sno = ((nowPage-1)*recordPerPage) +1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
			
		List<imgDTO> list = dao.list(map);
		// 전체 레코드 개수(col,word)
		int totalRecord = dao.total(map); 
		String paging=Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		
		return "/img/list";
	}

}
