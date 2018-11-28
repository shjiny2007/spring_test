package spring.model.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TeamTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		TeamDAO dao = (TeamDAO) factory.getBean("team");

		create(dao);
		// list(dao);
		// total(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		// reply(dao);
		// checkRefnum(dao);
		// upViewcnt(dao);
		

	}


	private static void checkRefnum(TeamDAO dao) {
		if (dao.checkRefnum(1)) {
			p("답변이 있는 글이니까 삭제 할 수 없습니다.");
		} else {
			p("삭제가 가능합니다.");
		}
	}

	private static void reply(TeamDAO dao) {
		TeamDTO dto = dao.replyRead(1404);
		

		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());

		dao.upAnsnum(map);
		if (dao.replyCreate(dto)) {
			p("성공");

		} else {
			p("실패");
		}
	}

	private static void total(TeamDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word", "1");

		int total = dao.total(map);
		p("전체 레코드 수: " + total);

	}

	private static void list(TeamDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);

		List<TeamDTO> list = dao.list(map);
		for (int i = 0; i < list.size(); i++) {
			TeamDTO dto = list.get(i);
			p(dto);
			p("--------------------------------------");
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}

	private static void p(TeamDTO dto) {
		
		p("번호: "+dto.getNo());
		p("글쓴이 : "+dto.getName());
		p("성별 : "+dto.getGender());
		p("취미 : "+dto.getHobby());
		p("스킬 : "+dto.getSkills());
		p("번호 : "+dto.getPhone());		
		p("우편번호 : "+dto.getZipcode());		
		p("주소 : "+dto.getAddress());	
		p("상세주소 : "+dto.getAddress2());	

	}

	private static void create(TeamDAO dao) {
		TeamDTO dto = new TeamDTO();
		dto.setName("김길동");
		dto.setGender("여자");
		dto.setHobby("게임");
		dto.setSkills("먹기");
		dto.setPhone("010-2222-3333");
		dto.setZipcode("22334");
		dto.setAddress("경기도 하남시");
		dto.setAddress2("어딘가");

		if (dao.create(dto)) {
			p("등록성공");
		} else {
			p("등록실패");
		}
	}

	private static void read(TeamDAO dao) {
		TeamDTO dto = new TeamDTO();
		dto = dao.read(1);
		p(dto);

	}

	private static void update(TeamDAO dao) {
		TeamDTO dto = new TeamDTO();
		dto.setNo(5);
		dto.setHobby("게임");
		dto.setSkills("먹기");
		dto.setPhone("010-2222-3333");
		dto.setZipcode("22334");
		dto.setAddress("경기도 하남시");
		dto.setAddress2("어딘가");

		if (dao.update(dto)) {
			p("성공");
		} else {
			p("실패");
		}
	}

	private static void delete(TeamDAO dao) {
		if (dao.delete(4)) {
			p("성공");
		} else {
			p("실패");
		}

	}

	private static void upViewcnt(TeamDAO dao) {
		dao.upViewcnt(1);
	}
}
