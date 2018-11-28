package spring.model.img;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class imgDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public boolean checkRefnum(int no) {
		boolean flag = false;
		
		int cnt = mybatis.selectOne("img.checkRefnum",no);
		if(cnt>0) flag = true;
		
		return flag;
	}

	public boolean create(imgDTO dto) {
		boolean flag =false;
		
		int cnt = mybatis.insert("img.create",dto);
		if(cnt>0) flag = true;

		return flag;
	}


	public boolean update(imgDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.update("img.update",dto);
		if(cnt>0) flag = true;

		return flag;
	}

	public boolean delete(int no) {
		boolean flag = false;
		
		int cnt = mybatis.delete("img.delete",no);
		if(cnt>0) flag = true;
		
		return flag;

	}


	public List<imgDTO> list(Map map) {
		
		return mybatis.selectList("img.list",map);
	}

	public int total(Map map) {
		
		return mybatis.selectOne("img.total",map);

	}



public boolean replyCreate(imgDTO dto){
	
	boolean flag = false;
	
	int cnt = mybatis.insert("img.replyCreate",dto);
	if(cnt>0) flag = true;
	
	return flag;
}

public imgDTO replyread(int no) {
	
return mybatis.selectOne("img.replyRead",no);

}

public void upAnsnum(Map map) {

	mybatis.update("img.upAnsnum",map);
	
}
public void upviewcnt(int no) {
	
	mybatis.update("img.upViewcnt",no);

}
public boolean passcheck(Map map) {
	boolean flag = false;
	
	int cnt = mybatis.selectOne("img.passCheck",map);
	if(cnt>0) flag = true;
	

	return flag;
}

public List imgRead(int no) {
	
	List list = new ArrayList();
	Map map = mybatis.selectOne("img.imgRead",no);
	String[] files= {
	(String)map.get("PRE_FILE2"),
	(String)map.get("PRE_FILE1"),
	(String)map.get("IMG"),
	(String)map.get("NEX_FILE1"),
	(String)map.get("NEX_FILE2")
	};
	
		
	int[] noArr = { 
			((BigDecimal)map.get("PRE_NO2")).intValue(),
			((BigDecimal)map.get("PRE_NO1")).intValue(),
			((BigDecimal)map.get("NO")).intValue(),
			((BigDecimal)map.get("NEX_NO1")).intValue(),
			((BigDecimal)map.get("NEX_NO2")).intValue()
			};

			list.add(noArr);
			list.add(files);
			
//			System.out.println(files[0]);
//			System.out.println(files[1]);
//			System.out.println(files[2]);
//			System.out.println(files[3]);
//			System.out.println(files[4]);
//			
//			System.out.println(noArr[0]);
//			System.out.println(noArr[1]);
//			System.out.println(noArr[2]);
//			System.out.println(noArr[3]);
//			System.out.println(noArr[4]);

	return list;
}

public imgDTO read(int no) {

	return mybatis.selectOne("img.read",no);
}

public String getimg(int no) {
	
	return mybatis.selectOne("img.getimg",no);
}


}


