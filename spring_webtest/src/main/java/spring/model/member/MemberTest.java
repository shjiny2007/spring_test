package spring.model.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;

public class MemberTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory= new XmlBeanFactory(resource);
		MemberDAO dao = (MemberDAO)factory.getBean("member");
		
		//create(dao);
		//list(dao);
		//total(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//passCheck(dao);
		//updateFile(dao);
		//updatePw(dao);
		//getGrade(dao);
		//getFname(dao);
		//getPwFind(dao);
		//getIdFind(dao);
		//duplicatedID(dao);
		//duplicatedEmail(dao);
		//loginCheck(dao);
	}

	private static void loginCheck(MemberDAO dao) {
		Map map = new HashMap();
		map.put("id", "25");
		map.put("passwd", "25");
		
		if(dao.loginCheck(map)) {
			p("로그인되었습니다.");
		}else {
			p("로그인 되지 않았습니다.");
		}	
	}


	private static void duplicatedEmail(MemberDAO dao) {
		
		if(dao.duplicatedEmail("26@mail.com")) {
			p("중복.사용불가");
		}else {
			p("중복아님.사용가능");
		}
	}


	private static void duplicatedID(MemberDAO dao) {
		
		if(dao.duplicatedID("7")) {
			p("중복.사용불가");
		}else {
			p("중복아님.사용가능");
		}
	}


	private static void getIdFind(MemberDAO dao) {
		Map map = new HashMap();
		map.put("mname", "송");
		map.put("email", "25@mail.com");
		p(dao.getIdFind(map));
	}


	private static void getPwFind(MemberDAO dao) {
		Map map = new HashMap();
		map.put("mname", "송");
		map.put("id", "25");
		p(dao.getPwFind(map));
	}


	private static void getFname(MemberDAO dao) {
		p(dao.getFname("25"));
	}


	private static void getGrade(MemberDAO dao) {
		p(dao.getGrade("25"));
		
		
	}


	private static void updatePw(MemberDAO dao) {
		Map map = new HashMap();
		map.put("passwd", "1234");
		map.put("id", "25");
		
		if(dao.updatePw(map)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}


	private static void updateFile(MemberDAO dao) {
		Map map = new HashMap();
		map.put("fname", "koala.jpg");
		map.put("id", "25");
		
		if(dao.updateFile(map)) {
			p("성공");
		}else {
			p("실패");
		}
		
	}


	private static void total(MemberDAO dao) {
		Map map = new HashMap();
		map.put("col", "id");
		map.put("word", "");
		
		int total = dao.total(map);
		p("전체 레코드 수: " + total);
	
	}


	private static void passCheck(MemberDAO dao) {
		Map map=new HashMap();
		map.put("id", "25");
		map.put("passwd", "1234");
		if(dao.passCheck(map)) {
			p("올바른 비밀번호 입니다.");
		}else {
			p("잘못된 비밀번호 입니다.");
		}
		
	}
	private static void list(MemberDAO dao) {
		Map map = new HashMap();
		map.put("col", "");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		
		List<MemberDTO> list=dao.list(map);
		for(int i=0;i<list.size();i++) {
			MemberDTO dto=list.get(i);
			p(dto);
			p("--------------------------------------");
		}
	}


	private static void p(String string) {
		System.out.println(string);	
	}

	private static void p(MemberDTO dto) {
		p("아이디: "+dto.getId());	
		p("이름: "+dto.getMname());
		p("번호: "+dto.getTel());
		p("이메일:"+dto.getEmail());
		p("우편번호:" +dto.getZipcode());
		p("주소:"+dto.getAddress1());
		p("상세주소:"+dto.getAddress2());
		p("이미지:"+dto.getFname());
	}

	private static void create(MemberDAO dao) {
		MemberDTO dto=new MemberDTO();
		dto.setId("22");
		dto.setPasswd("1234");
		dto.setMname("17");
		dto.setTel("010-2222-3333");
		dto.setEmail("22@mail.com");
		dto.setZipcode("12333");
		dto.setAddress1("서울시 강남구");
		dto.setAddress2("삼성동 어딘가");
		dto.setJob("학생");
		dto.setFname("image.jpg");
		
		if(dao.create(dto)) {
			p("등록성공");
		}else {
			p("등록실패");
		}
	}

	private static void read(MemberDAO dao) {
		MemberDTO dto = new MemberDTO();
		dto = dao.read("10");
		p(dto);

	}

	private static void update(MemberDAO dao) {
		MemberDTO dto=new MemberDTO();
		dto=dao.read("25");
		dto.setTel("010-1111-1111");
		dto.setEmail("25@mail.com");
		dto.setZipcode("02223");
		dto.setAddress1("서울시 강동구");
		dto.setAddress2("어딘가");
		dto.setJob("기자");
		
		if(dao.update(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void delete(MemberDAO dao) {
		if (dao.delete("23")) {
			p("성공");
		} else {
			p("실패");
		}

	}

}


