package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(CodeVo codeVo) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt(CodeVo codeVo) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	//게시글 수정
	public int updateBoard(BoardVo boardVo) throws Exception;
	
	//게시글 삭제
	public int deleteBoard(BoardVo boardVo) throws Exception;
	
	//코드네임(TYPE조회)
	public List<CodeVo> codeNameSelect() throws Exception;

}
