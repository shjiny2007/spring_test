package spring.model.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class BbsTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory= new XmlBeanFactory(resource);
		BbsDAO dao = (BbsDAO)factory.getBean("dao");
		
		//create(dao);
		//list(dao);
		//total(dao);
		//read(dao);
		//update(dao);
		delete(dao);
		//passCheck(dao);
		//reply(dao);
		//checkRefnum(dao);
		

	}
	
	
	private static void checkRefnum(BbsDAO dao) {
		if(dao.checkRefnum(51)) {
			p("답변이 있는 글이니까 삭제 할 수 없습니다.");
		}else {
			p("삭제가 가능합니다.");
		}
	}


	private static void reply(BbsDAO dao) {
		BbsDTO dto = dao.replyRead(5);
		dto.setWname("답변글1");
		dto.setTitle("답변글1제목");
		dto.setContent("답변글1내용");
		dto.setFilename("test.txt");
		dto.setFilesize(11);
		dto.setPasswd("1234");
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum",dto.getAnsnum());
		
		dao.upAnsnum(map);
		if(dao.replyCreate(dto)) {
			p("성공");
			
		}else {
			p("실패");
		}
		
		
		
	}


	private static void total(BbsDAO dao) {
		Map map = new HashMap();
		map.put("col", "name");
		map.put("word", "");
		
		int total = dao.total(map);
		p("전체 레코드 수: " + total);
		
		
	}


	private static void passCheck(BbsDAO dao) {
		Map map=new HashMap();
		map.put("bbsno", 2);
		map.put("passwd", "1234");
		if(dao.passCheck(map)) {
			p("올바른 비밀번호 입니다.");
		}else {
			p("잘못된 비밀번호 입니다.");
		}
		
	}
	private static void list(BbsDAO dao) {
		Map map = new HashMap();
		map.put("col", "");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		
		List<BbsDTO> list=dao.list(map);
		for(int i=0;i<list.size();i++) {
			BbsDTO dto=list.get(i);
			p(dto);
			p("--------------------------------------");
		}
	}


	private static void p(String string) {
		System.out.println(string);	
	}

	private static void p(BbsDTO dto) {
		p("번호: "+dto.getBbsno());
		p("글쓴이 : "+dto.getWname());
		p("글제목 : "+dto.getTitle());
		p("글내용 : "+dto.getContent());
		p("조회수 : "+dto.getViewcnt());
		p("등록날짜 : "+dto.getWdate());		
		p("grpno : "+dto.getGrpno());		
		p("ansnum : "+dto.getAnsnum());		
	}

	private static void create(BbsDAO dao) {
		BbsDTO dto=new BbsDTO();
		dto.setWname("w2make");
		dto.setTitle("wmaek 것");
		dto.setContent("w아직 5시다.");
		dto.setPasswd("1234");
		//dto.setFilename("ㅇㅅㅇ.txt");
		//dto.setFilesize(12);
		if(dao.create(dto)) {
			p("등록성공");
		}else {
			p("등록실패");
		}
	}

	private static void read(BbsDAO dao) {
		BbsDTO dto=new BbsDTO();
		dto=dao.read(8);
		p(dto);
		
	}

	private static void update(BbsDAO dao) {
		BbsDTO dto=new BbsDTO();
		dto=dao.read(50);
		dto.setWname("th");
		dto.setTitle("점심이란");
		dto.setContent("입맛이 없다.....");
		dto.setFilename("text.txt");
		dto.setFilesize(11);
		
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void delete(BbsDAO dao) {
		if(dao.delete(62)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void upviewcnt(BbsDAO dao) {
		dao.upViewcnt(3);
	}
}


