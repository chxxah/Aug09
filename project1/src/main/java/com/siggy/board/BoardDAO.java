package com.siggy.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

//inject사용해보기

@Repository("boardDAO")
public class BoardDAO {

	@Inject
	@Named("sqlSession")
	private SqlSession sqlsession;

	public List<BoardDTO> boardlist(PageDTO page) {
		return sqlsession.selectList("board.boardList", page);
	}

	public BoardDTO detail(BoardDTO dto2) {
		return sqlsession.selectOne("board.detail", dto2); // 앞에는 네임스페이스.아이디, 값
	}

	public void write(BoardDTO dto) {
		sqlsession.insert("board.write", dto);

	}

	public void delete(BoardDTO dto) {
		sqlsession.update("board.delete", dto);
	}

	public void edit(BoardDTO dto) {
		sqlsession.update("board.edit", dto);
	}

	public void likeUp(BoardDTO dto2) {
		sqlsession.update("board.likeUp", dto2);
	}

	public int totalCount() {
		return sqlsession.selectOne("board.totalCount");
	}

	public List<Map<String, Object>> commentsList(int bno) {
		return sqlsession.selectList("board.commentsList", bno);
	}

	public int cdel(Map<String, Object> map) {
		return sqlsession.update("board.cdel", map);
	}
	public int cedit(Map<String, Object> map) {
	      return sqlsession.update("board.cedit", map);
	   }

}
