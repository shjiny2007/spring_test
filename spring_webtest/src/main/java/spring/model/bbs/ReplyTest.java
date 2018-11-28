package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import spring.model.bbs.ReplyDAO;

public class ReplyTest {

	public static void main(String[] args) {

		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		ReplyDAO dao = (ReplyDAO)factory.getBean("reply");
		
		//list(dao);
		//update(dao);
		//read(dao);
		//create(dao);
		//delete(dao);
		//bdelete(dao);
	}

	private static void bdelete(ReplyDAO dao) {
		if(dao.bdelete(3))
			p("삭제");
		else
			p("삭제 불가");
	}

	private static void read(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		p(dto);
		
	}

	private static void delete(ReplyDAO dao) {
		if(dao.delete(1))
			p("삭제");
		else
			p("삭제 불가");
		
	}



	private static void create(ReplyDAO dao) {
		ReplyDTO dto = new ReplyDTO();
		dto.setName("test4");
		dto.setContent("testtest");
		dto.setNum(3);
		if(dao.create(dto))
			p("성공");
		else
			p("실패");
	}
 
	private static void update(ReplyDAO dao) {
			ReplyDTO dto = dao.read(1);
			dto.setContent("집좀");
			if(dao.update(dto))
				p("성공");
			else {
				p("실패");
			}
	}

	private static void list(ReplyDAO dao) {
		Map map = new HashMap();
		map.put("bbsno", "1");
		map.put("sno", "1");
		map.put("eno", "5");
		
		List<ReplyDTO> list = dao.list(map);
		Iterator<ReplyDTO> iter = list.iterator();
		while(iter.hasNext()) {
			ReplyDTO dto = iter.next();
			p(dto);
			p("=========================");
		}
	}

	private static void p(String string) {
		System.out.println(string);
		
	}

	private static void p(ReplyDTO dto) {
		p("번호"+dto.getRnum());
		p("내용:"+dto.getContent());
		p("날짜:"+dto.getRegdate());
		p("아이디:"+dto.getName());
		p("부모글 번호:"+dto.getNum());
				
	}

}
