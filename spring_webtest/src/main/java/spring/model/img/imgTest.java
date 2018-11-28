package spring.model.img;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class imgTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory= new XmlBeanFactory(resource);
		imgDAO dao = (imgDAO)factory.getBean("img");
		
		//create(dao);
		//list(dao);
		//total(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//passCheck(dao);
		//reply(dao);
		//checkRefnum(dao);
		//getimg(dao);
		imgRead(dao);
		

	}
	
	
	private static void imgRead(imgDAO dao) {
		List list = dao.imgRead(4);
	}


	private static void getimg(imgDAO dao) {
		p(dao.getimg(5));
	}


	private static void checkRefnum(imgDAO dao) {
		if(dao.checkRefnum(5)) {
			p("답변이 있는 글이니까 삭제 할 수 없습니다.");
		}else {
			p("삭제가 가능합니다.");
		}
	}


	private static void reply(imgDAO dao) {
		imgDTO dto = dao.replyread(5);
		dto.setTitle("답변글1제목");
		dto.setName("답변글1");
		dto.setImg("옴팡이.jpg");
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


	private static void total(imgDAO dao) {
		Map map = new HashMap();
		map.put("col", "name");
		map.put("word", "");
		
		int total = dao.total(map);
		p("전체 레코드 수: " + total);
		
		
	}


	private static void passCheck(imgDAO dao) {
		Map map=new HashMap();
		map.put("no", 7);
		map.put("passwd", "14");
		if(dao.passcheck(map)) {
			p("올바른 비밀번호 입니다.");
		}else {
			p("잘못된 비밀번호 입니다.");
		}
		
	}
	private static void list(imgDAO dao) {
		Map map = new HashMap();
		map.put("col", "");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		
		List<imgDTO> list=dao.list(map);
		for(int i=0;i<list.size();i++) {
			imgDTO dto=list.get(i);
			p(dto);
			p("--------------------------------------");
		}
	}


	private static void p(String string) {
		System.out.println(string);	
	}

	private static void p(imgDTO dto) {
		p("번호: "+dto.getNo());
		p("글쓴이 : "+dto.getName());
		p("글제목 : "+dto.getTitle());
		p("조회수 : "+dto.getViewcnt());
		p("등록날짜 : "+dto.getMdate());		
		p("grpno : "+dto.getGrpno());	
		p("indent: " + dto.getIndent());
		p("ansnum : "+dto.getAnsnum());		
	}

	private static void create(imgDAO dao) {
		imgDTO dto=new imgDTO();
		dto.setTitle("제목");
		dto.setName("1");
		dto.setImg("옴팡이.jpg");
		dto.setPasswd("1234");
		
		if(dao.create(dto)) {
			p("등록성공");
		}else {
			p("등록실패");
		}
	}

	private static void read(imgDAO dao) {
		imgDTO dto=new imgDTO();
		dto=dao.read(7);
		p(dto);
		
	}

	private static void update(imgDAO dao) {
		imgDTO dto=new imgDTO();
		dto=dao.read(3);
		dto.setName("업");
		dto.setTitle("업제목");
		dto.setImg("1.jpg");
		
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void delete(imgDAO dao) {
		if(dao.delete(3)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}

	private static void upViewcnt(imgDAO dao) {
		dao.upviewcnt(3);
	}
}


