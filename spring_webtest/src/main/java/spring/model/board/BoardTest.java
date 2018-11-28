package spring.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class BoardTest {

	public static void main(String[] args) {

		Resource resource=new ClassPathResource("daoTest.xml");
		
		BeanFactory factory =new XmlBeanFactory(resource);
		BoardDAO dao=(BoardDAO)factory.getBean("dao");
		
		//read(dao);
		//list(dao);
		//total(dao);
		//create(dao);
		//upViewCount(dao);
		//passwdCheck(dao);
		//update(dao);
		//replyRead(dao);
		//replyCreate(dao);
		//checkRefnum(dao);
		delete(dao);
		
	}


	private static void checkRefnum(BoardDAO dao) {
		if(dao.checkRefnum(22)) {
			p("답변이 있는 글이니까 삭제 할 수 없습니다.");
		}else {
			p("삭제가 가능합니다.");
		}
		
		
	}


	private static void total(BoardDAO dao) {
		Map map=new HashMap();
		map.put("col", "name");
		map.put("word", "test");
		
		int total=dao.getTotal(map);
		p("전체 레코드 수: "+total);
		
	}


	private static void create(BoardDAO dao) {
		BoardDTO dto=new BoardDTO();
		dto.setName("점심");
		dto.setSubject("금요일");
		dto.setContent("아빠생일...");
		dto.setPasswd("1234");
		dto.setIp("127.0.10.5");
		dto.setFilename("생일이름");
		dto.setFilesize(70);
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void p(String string) {
		System.out.println(string);
		
	}

	private static void read(BoardDAO dao) {
		BoardDTO dto=new BoardDTO();
		dto=dao.read(7);
		p(dto);
		
	}


	private static void p(BoardDTO dto) {
		p("번호:"+dto.getNum());
		p("이름:"+dto.getName());
		p("제목:"+dto.getSubject());
		p("내용:"+dto.getContent());
		p("등록날짜:"+dto.getRegdate());
		p("비밀번호:"+dto.getPasswd());
		p("ip주소:"+dto.getIp());
		p("파일이름:"+dto.getFilename());
		p("파일크기:"+dto.getFilesize());
		
	}
	
	private static void update(BoardDAO dao) {
		BoardDTO dto=new BoardDTO();
		dto=dao.read(3);
		dto.setName("수정");
		dto.setSubject("추원 ");
		dto.setContent("내용이다.");
		dto.setFilename("testfile.jsp");
		dto.setFilesize(40);
		
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}
	

	private static void delete(BoardDAO dao) {
		if(dao.delete(22)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void list(BoardDAO dao) {
		Map map = new HashMap();
		map.put("col", "");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		
		List<BoardDTO> list=dao.getList(map);
		for(int i=0;i<list.size();i++) {
			BoardDTO dto=list.get(i);
			p(dto);
			p("--------------------------------------");
		}
		
	}
	

	private static void replyCreate(BoardDAO dao) {
		BoardDTO dto=dao.replyRead(2);
		dto.setName("reply3");
		dto.setSubject("reply33");
		dto.setContent("425입니당~~.");
		dto.setPasswd("123");
		dto.setIp("123.2.1.5");
		dto.setFilename("3.txt");
		dto.setFilesize(10);
		
		
		Map map=new HashMap();
		map.put("ref", dto.getRef());
		map.put("ansnum", dto.getAnsnum());
		dao.upAnsnum(map);
		if(dao.replyCreate(dto)) {
			p("등록성공");
		}else {
			p("실패");
		}
		
		

		
	}

	private static void passwdCheck(BoardDAO dao) {
		Map map=new HashMap();
		map.put("num", 4);
		map.put("passwd", "123");
		
		if(dao.passwdCheck(map)) {
			p("맞음");
		}else {
			p("틀림");
		}
		
	}


	private static void replyRead(BoardDAO dao) {
		BoardDTO dto=new BoardDTO();
		dto=dao.replyRead(6);
		p(dto);
		
	}
	
	private static void upViewCount(BoardDAO dao) {
		dao.upCount(5);
		
	}


}
