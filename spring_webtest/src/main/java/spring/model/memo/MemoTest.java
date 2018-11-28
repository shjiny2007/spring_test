package spring.model.memo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class MemoTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory= new XmlBeanFactory(resource);
		MemoDAO dao = (MemoDAO)factory.getBean("memo");
		
		//create(dao);
		//list(dao);
		//total(dao);
		read(dao);
		//update(dao);
		//delete(dao);
		//reply(dao);
		//checkRefnum(dao);
		//upViewcnt(dao);
		

	}
	
	
	private static void checkRefnum(MemoDAO dao) {
		if(dao.refnumCheck(1402)) {
			p("답변이 있는 글이니까 삭제 할 수 없습니다.");
		}else {
			p("삭제가 가능합니다.");
		}
	}


	private static void reply(MemoDAO dao) {
		MemoDTO dto = dao.replyRead(1404);
		dto.setTitle("답변글제목1");
		dto.setContent("답변글1내용");
		
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

	private static void total(MemoDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word", "1");
		
		int total = dao.total(map);
		p("전체 레코드 수: " + total);
		
		
	}
	private static void list(MemoDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		
		List<MemoDTO> list=dao.list(map);
		for(int i=0;i<list.size();i++) {
			MemoDTO dto=list.get(i);
			p(dto);
			p("--------------------------------------");
		}
	}


	private static void p(String string) {
		System.out.println(string);	
	}

	private static void p(MemoDTO dto) {
		p("번호: "+dto.getMemono());
		p("글제목 : "+dto.getTitle());
		p("글내용 : "+dto.getContent());
		p("등록날짜 : "+dto.getWdate());		
		p("조회수 : "+dto.getViewcnt());	
	}

	private static void create(MemoDAO dao) {
		MemoDTO dto=new MemoDTO();
		dto.setTitle("wmaek 3");
		dto.setContent("w아직 5시다.3");

		if(dao.create(dto)) {
			p("등록성공");
		}else {
			p("등록실패");
		}
	}

	private static void read(MemoDAO dao) {
		MemoDTO dto=new MemoDTO();
		dto=dao.read(1402);
		p(dto);
		
	}

	private static void update(MemoDAO dao) {
		MemoDTO dto=new MemoDTO();
		dto=dao.read(1402);
		dto.setTitle("배불러");
		dto.setContent("배부르니까 졸랴");
		
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void delete(MemoDAO dao) {
		if(dao.delete(1403)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void upViewcnt(MemoDAO dao) {
		dao.upViewcnt(1402);
	}
}


