package com.spring.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",codeVo);
	}
	
	@Override
	public int selectBoardCnt(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotal", codeVo);
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------------------------------------------");
		System.out.println("boardVo 타이틀:" + boardVo.getBoardTitle().replace("!!", ","));
		System.out.println("boardVo 내용:" + boardVo.getBoardComment().replace("!!", ","));
		System.out.println("------------------------------------------");
		boardVo.setBoardTitle(boardVo.getBoardTitle().replace("!!", ","));
		boardVo.setBoardComment(boardVo.getBoardComment().replace("!!", ","));
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	
	//게시글 수정
	@Override
	public int updateBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("board.updateBoard", boardVo);
	}
	
	//게시글 삭제
	@Override
	public int deleteBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("board.deleteBoard", boardVo);
	}
	
	//코드네임(TYPE조회)
	@Override
	public List<CodeVo> codeNameSelect() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.codeNameSelect");
	}

	
	
	
	
}
