package com.spring.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Map<String, Object> pageCodeDatas = new HashMap<String, Object>();
		 * 
		 * pageCodeDatas.put("pageNo", pageVo.getPageNo()); pageCodeDatas.put("codeId",
		 * codeId);
		 * 
		 * boardDao.selectBoardList(pageCodeDatas);
		 */
		
		return boardDao.selectBoardList(codeVo);
	}
	
	@Override
	public int selectBoardCnt(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt(codeVo);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		
//		Map<String, Object> titleCommentMap = new HashMap<String, Object>();
//		map.put("num", "1");
//		map.put("id", "test1");
//		map.put("pw", "1111");
//		map.put("tel", "010-1111-1111");
//		map.put("add", "대전 서구 탄방동");
//		listMap.add(map);
		
		
		
//		System.out.println("---------------------------");
//		System.out.println("서비스로 넘어오는 datas");
//		System.out.println("서비스로 넘어온 Title정보: " + boardVo.getBoardTitle());
//		System.out.println("서비스로 넘어온 comment 정보: " + boardVo.getBoardComment());
//		System.out.println("사이즈: " + boardVo.getBoardComment().length());
//		
//		System.out.println("----------------------------");
		/*
		 * String title = boardVo.getBoardTitle(); String chTitle =
		 * title.replaceAll(",", "<"); System.out.println("변환된문자들: " + chTitle);
		 */

		
		
//		Map<String, Object> titleCommentMap = new HashMap<String, Object>();
//		
//		List<Map<String, Object>> titleCommentList =  new ArrayList<Map<String, Object>>();
//		
//		for(int i=0; i<3; i++) {
//			
//			titleCommentMap.put("boardTitle", boardVo.getBoardTitle());
//			titleCommentMap.put("boardComment", boardVo.getBoardComment());
//			titleCommentList.add(titleCommentMap);
//			
//		}
//		System.out.println("ㅁㅁㅁㅁㅁ맵:" + titleCommentMap);
		
		
		
		
		
		
		
		/*
		 * ArrayList<String> tico = new ArrayList<String>();
		 * tico.add(boardVo.getBoardTitle()); tico.add(boardVo.getBoardComment());
		 * System.out.println("티코: " + tico);
		 * 
		 * String STtico = tico.get(0); String SCtico = tico.get(1);
		 * 
		 * 
		 * String[] STdateArr = new String[5]; String[] SCdateArr = new String[5];
		 * 
		 * String STdate[] = STtico.split(","); String SCdate[] = SCtico.split(",");
		 * 
		 * int date = STtico.split(",").length;
		 * System.out.println("ddddddaaaaaattttaaaa: " + date); if(date == 1) {
		 * System.out.println("STdate["+0+"]: " + STdate[0]);
		 * System.out.println("SCdate["+0+"]: " + SCdate[0]); STdateArr[0] = STdate[0];
		 * SCdateArr[0] = SCdate[0]; boardVo.setBoardTitle(STdateArr[0]);
		 * boardVo.setBoardComment(SCdateArr[0]); System.out.println("if문실행"); return
		 * boardDao.boardInsert(boardVo);
		 * 
		 * } else { for(int i=0; i<date; i++) { System.out.println("STdate["+ i +"]: " +
		 * STdate[i]); System.out.println("SCdate["+ i +"]: " + SCdate[i]); STdateArr[i]
		 * = STdate[i]; SCdateArr[i] = SCdate[i]; boardVo.setBoardTitle(STdateArr[i]);
		 * boardVo.setBoardComment(SCdateArr[i]); System.out.println("boarVoSet: " +
		 * boardVo.getBoardTitle()); System.out.println("boarVoSet: "
		 * +boardVo.getBoardComment()); System.out.println("for문 실행");
		 * System.out.println("for문" + (i) + "번째 실행중"); if(date - i > 1) {
		 * boardDao.boardInsert(boardVo); } else { return boardDao.boardInsert(boardVo);
		 * } } }
		 */	
		
		
		
		
		//맵으로
//		int resultCnt = 0;
//		List<Map<String, String>> titleCommentMaplistMap = new ArrayList<Map<String, String>>();
//		Map<String, String> titleCommentMap = new HashMap<String, String>();
//		for(int i=0; i<boardTitle.length; i++) {
//			titleCommentMap.put("boardTitle", boardTitle[i]);
//			titleCommentMap.put("boardComment", boardComment[i]);
//			titleCommentMaplistMap.add(titleCommentMap);
//			resultCnt++;
//		}
//		System.out.println("Map에 들어가 제목 내용:" + titleCommentMap);
		
		return boardDao.boardInsert(boardVo);
		
		
	}
	
	//게시글 수정
	@Override
	public int updateBoard(BoardVo boardVo) throws Exception {
		
		return boardDao.updateBoard(boardVo);
	}
	
	//게시글 삭제
	@Override
	public int deleteBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.deleteBoard(boardVo);
	}
	
	//코드네임(셀렉트박스)
	public List<CodeVo> codeNameSelect() throws Exception { 
		return boardDao.codeNameSelect();
	}
}
