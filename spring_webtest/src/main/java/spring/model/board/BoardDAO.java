package spring.model.board;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public boolean create(BoardDTO dto) {
		boolean flag = false;
		int cnt = mybatis.insert("board.create", dto);
		if (cnt > 0)
			flag = true;
		return flag;
	}

	/**
	 * 전체 레코드 갯수
	 * 
	 * @param searchColumn
	 * @param searchWord
	 * @return
	 */
	public int getTotal(Map map) {

		return mybatis.selectOne("board.total", map);

	}

	/**
	 * 게시판 글목록
	 * 
	 * @param searchColumn
	 * @param searchWord
	 * @param beginPerPage
	 * @param numPerPage
	 * @return
	 */
	public List<BoardDTO> getList(Map map) {

		return mybatis.selectList("board.list", map);

	}

	/**
	 * 조회수 증가
	 * 
	 * @param num
	 */
	public void upCount(int num) { 
		mybatis.update("board.upCount", num);
	}

	/**
	 * 게시판 글보기
	 * 
	 * @param num
	 * @return
	 */
	public BoardDTO read(int num) {

		return mybatis.selectOne("board.read", num);
	}

	/**
	 * 비밀번호 검사
	 * 
	 * @param num
	 * @param passwd
	 * @return
	 */
	public boolean passwdCheck(Map map) {
		boolean flag = false;

		int cnt = mybatis.selectOne("board.passwdCheck", map);

		if (cnt > 0)
			flag = true;

		return flag;
	}

	public boolean update(BoardDTO dto) {
		boolean flag = false;

		int cnt = mybatis.update("board.update", dto);

		if (cnt > 0)
			flag = true;

		return flag;
	}

	/**
	 * ansnum 재정렬
	 * 
	 * @param ref
	 * @param ansnum
	 */
	public void upAnsnum(Map map) {

		mybatis.update("board.upAnsnum", map);
	}

	/**
	 * 답변등록
	 * 
	 * @param dto
	 * @return
	 */
	public boolean insertReply(BoardDTO dto) {
		boolean flag = false;

		int cnt = mybatis.insert("board.insertReply", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	/**
	 * 부모글인지 확인
	 * 
	 * @param num
	 * @param con
	 * @return
	 */
	public boolean checkRefnum(int num) {

		boolean flag = false;

		int cnt = mybatis.selectOne("board.checkRefnum", num);

		if (cnt > 0)
			flag = true;

		return flag;
	}

	/**
	 * 게시판 글 삭제
	 * 
	 * @param num
	 * @return
	 */
	public boolean delete(int num) {

		boolean flag = false;

		int cnt = mybatis.delete("board.delete", num);

		if (cnt > 0)
			flag = true;

		return flag;
	}

	/**
	 * 부모의 ref,indent,ansnum 가져오기
	 * 
	 * @param num
	 * @return
	 */
	public BoardDTO readReply(int num) {

		return mybatis.selectOne("board.readReply", num);

	}

	public boolean replyCreate(BoardDTO dto) {
		boolean flag = false;

		int cnt = mybatis.insert("board.replyCreate", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public BoardDTO replyRead(int num) {

		return mybatis.selectOne("board.replyRead", num);
	}

}
