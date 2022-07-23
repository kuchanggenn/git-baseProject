package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,CodeVo codeVo) throws Exception{
		
		
		
			String codeId =  codeVo.getCodeId();
			System.out.println("조회하는 codeId 몰록:" + codeId);
		
			//게시판에 list 목록
			List<BoardVo> boardList = new ArrayList<BoardVo>();
			//체크박스 목록
			List<CodeVo> listCodeName = new ArrayList<CodeVo>();
			
			int page = 1;	
			int totalCnt = 0;
			
			if(codeVo.getPageNo() == 0){
				codeVo.setPageNo(page);
			}
			
			
			listCodeName = boardService.codeNameSelect();
			boardList = boardService.SelectBoardList(codeVo);
			totalCnt = boardService.selectBoardCnt(codeVo);	
			
			System.out.println("boardList ToString:" + boardList.toString());
			
			model.addAttribute("boardList", boardList);
			System.out.println("새로운 보드리스트:" + boardList);
			model.addAttribute("listCodeName", listCodeName);
			System.out.println("새로운 listCodeName:" + listCodeName);
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("pageNo", page);
			
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/boarListAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardListAction(Locale locale, HttpServletRequest request,
								   BoardVo boardVo, CodeVo codeVo, PageVo pageVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		System.out.println("list POST로 넘어온 code_id: " + boardVo.getCodeVo().getCodeId());
		
		
		
		
		result.put("success", "조회성공");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
				
		System.out.println("callbackMsg::"+callbackMsg);
	
		return callbackMsg;
	}
	
	
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		System.out.println("리스트에서 넘어온 boardType:" + boardType);
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model, PageVo pageVo, CodeVo codeVo
			) throws Exception{
		
		System.out.println("현재 페이지번호: " + pageVo.getPageNo());
		model.addAttribute("pageNo",  pageVo.getPageNo());
			
		List<CodeVo> listCodeName = new ArrayList<CodeVo>();
		listCodeName = boardService.codeNameSelect();
		//System.out.println("listCodeName: " + listCodeName);
		model.addAttribute("listCodeName", listCodeName);
		
		return "board/boardWrite";	
	}
	

//	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String boardWriteAction(Locale locale, HttpServletRequest request,
//								   String[] boardTitle, String[] boardComment) throws Exception{
//		
////		System.out.println("타이틀값  " + params.get("boardTitle"));
////		System.out.println("컨텐트값  " + params.get("boardComment"));
////		System.out.println("타이틀값  " + params.get("boardTitle"));
////		System.out.println("컨텐트값  " + params.get("boardComment"));
//		
////		HashMap<String, Object> paramMap = new HashMap<String, Object>();
////		
////		HashMap<String, Object> titleCommentMap = null;
////		
////		List<HashMap<String, Object>> titleCommentList = new ArrayList<HashMap<String, Object>>();
////		for (int i=0; i<2; i++) {
////			
////			titleCommentMap = new HashMap<String, Object>();
////			titleCommentMap.put("boardTitle", params);
////			titleCommentMap.put("boardComment", params);
////			
////			titleCommentList.add(titleCommentMap);
////		}
////		paramMap.put("titleCommentList", titleCommentList);
//		
////		HashMap<String, Object> titleCommentMap = new HashMap<String, Object>();
////		
////		List<Map<String, Object>> titleCommentList = (List<Map<String, Object>>) params.get("data");
////		titleCommentMap.put("titleCommentList", titleCommentList);	
//		
////		System.out.println("-------------------------------");
////		System.out.println("컨트롤러로 오는 title: " + boardVo.get
////		System.out.println("컨트롤러로 오는 comment: " + boardVo.getBoardComment());
////		System.out.println("전체" + boardVo);
////		System.out.println("-------------------------------");
//		
////		System.out.println("-------------------------------");
////		
////		System.out.println(boardVo);
////		for(int i=0; i<boardVo.getBoardTtile_List().length; i++) {
////			boardVo.setBoardTitle(boardVo.getBoardTtile_List()[i]);
////			boardVo.setBoardComment(boardVo.getBoardComment_List()[i]);
////		}
////		
////		
////		System.out.println("-------------------------------");
//		
////		System.out.println("-------------------------------");
////		
////		
////		
////		
////		
////		String boardTitleStr = boardVo.getBoardTitle();
////		String boardCommentStr = boardVo.getBoardComment();
//		
////		System.out.println("1111111-------------------------------");
////		System.out.println("타이틀:  " + boardVo.getBoardTitle());
////		System.out.println("내용들:  " + boardVo.getBoardComment());
////		System.out.println("1111111-------------------------------");
////			
////		String[] BoardTitleArr = boardTitleStr.split(",");
////		String[] BoardCommentArr = boardCommentStr.split(",");	
////		
////		
////		
////		if(BoardTitleArr.length == 0) {
////			boardService.boardInsert(boardVo);
////			resultCnt++;
////		} else {		
////			for (int i=0; i<BoardTitleArr.length; i++) {
////				boardVo.setBoardTitle(BoardTitleArr[i]);
////				boardVo.setBoardComment(BoardCommentArr[i]);
////				boardService.boardInsert(boardVo);
////				resultCnt++;
////			}	
////		}
//		
//		
//		int resultCnt = boardService.boardInsert(boardTitle, boardComment);
//		
//		
//		HashMap<String, String> result = new HashMap<String, String>();
//		CommonUtil commonUtil = new CommonUtil();
//		
//	
//		
//		result.put("success", (resultCnt > 0)?"Y":"N");
//		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
//		
//		
//		System.out.println("callbackMsg::"+callbackMsg);
//		
//		
//		
//		return callbackMsg;
//	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale, HttpServletRequest request,
								   BoardVo boardVo) throws Exception{
		
//		System.out.println("타이틀값  " + params.get("boardTitle"));
//		System.out.println("컨텐트값  " + params.get("boardComment"));
//		System.out.println("타이틀값  " + params.get("boardTitle"));
//		System.out.println("컨텐트값  " + params.get("boardComment"));
//		
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//	
//		HashMap<String, Object> titleCommentMap = null;
//		
//		List<HashMap<String, Object>> titleCommentList = new ArrayList<HashMap<String, Object>>();
//		for (int i=0; i<2; i++) {
//			
//			titleCommentMap = new HashMap<String, Object>();
//			titleCommentMap.put("boardTitle", params);
//			titleCommentMap.put("boardComment", params);
//			
//			titleCommentList.add(titleCommentMap);
//		}
//		paramMap.put("titleCommentList", titleCommentList);
//	
//		HashMap<String, Object> titleCommentMap = new HashMap<String, Object>();
//		
//		List<Map<String, Object>> titleCommentList = (List<Map<String, Object>>) params.get("data");
//		titleCommentMap.put("titleCommentList", titleCommentList);	
//
//		System.out.println("-------------------------------");
//		System.out.println("컨트롤러로 오는 title: " + boardVo.get
//		System.out.println("컨트롤러로 오는 comment: " + boardVo.getBoardComment());
//		System.out.println("전체" + boardVo);
//		System.out.println("-------------------------------");
//		
//		System.out.println("-------------------------------");
//		
//		System.out.println(boardVo);
//		for(int i=0; i<boardVo.getBoardTtile_List().length; i++) {
//			boardVo.setBoardTitle(boardVo.getBoardTtile_List()[i]);
//			boardVo.setBoardComment(boardVo.getBoardComment_List()[i]);
//		}		
		
		List<BoardVo> boardList = boardVo.getBoardVoList();
		System.out.println("컨트롤에서 받은 boardList: " + boardList);
		System.out.println("컨트롤에서 받은 boardList: " + boardVo.getBoardTitle());
		
		System.out.println("컨트롤에서 받은 boardList: " + boardVo.getBoardComment());
		System.out.println("컨트롤에서 받은 boardList: " + boardVo.getBoardType());
		System.out.println("컨트롤에서 받은 boardList: " + boardVo);

		
		String[] BoardTitleArr = boardVo.getBoardTitle().split(",");
		String[] BoardCommentArr =  boardVo.getBoardComment().split(",");	
		String[] BoardCodeTypeArr =  boardVo.getBoardType().split(",");
		
	
		
		
		int resultCnt = 0;
		if(BoardTitleArr.length == 0) {
			boardService.boardInsert(boardVo);
			resultCnt++;
		} else {		
			for (int i=0; i<BoardTitleArr.length; i++) {
				boardVo.setBoardType(BoardCodeTypeArr[i]);
				boardVo.setBoardTitle(BoardTitleArr[i]);
				boardVo.setBoardComment(BoardCommentArr[i]);
				boardService.boardInsert(boardVo);
				resultCnt++;
			}	
		}
			
		
//		String boardTitleStr = boardVo.getBoardTitle();
//		String boardCommentStr = boardVo.getBoardComment();
//		String boardCodeTypeStr = boardVo.getBoardType();
//		
//		System.out.println("컨트롤로 넘어온 boardType: " + boardVo.getBoardType());
//		
//		String[] BoardTitleArr = boardTitleStr.split(",");
//		String[] BoardCommentArr = boardCommentStr.split(",");	
//		String[] BoardCodeTypeArr = boardCodeTypeStr.split(",");
//		
//		
//		int resultCnt = 0;
//		if(BoardTitleArr.length == 0) {
//			boardService.boardInsert(boardVo);
//			resultCnt++;
//		} else {		
//			for (int i=0; i<BoardTitleArr.length; i++) {
//				boardVo.setBoardType(BoardCodeTypeArr[i]);
//				boardVo.setBoardTitle(BoardTitleArr[i]);
//				boardVo.setBoardComment(BoardCommentArr[i]);
//				boardService.boardInsert(boardVo);
//				resultCnt++;
//			}	
//		}
//		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
				
		System.out.println("callbackMsg::"+callbackMsg);
	
		return "callbackMsg";
	}
	
	//수정 화면
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardModify.do", method = RequestMethod.GET)
	public String boardModify(Locale locale, Model model,
			@PathVariable("boardType")String boardType,
			@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardModify";
	}
	
	//수정 완료 화면 보여주기
	@RequestMapping(value = "/board/boardModifyOk.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardModifyOk(Locale locale, BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.updateBoard(boardVo);
		System.out.println("---------------------------------");
		System.out.println(boardVo.getBoardTitle());	
		System.out.println(boardVo.getBoardComment());
		System.out.println(boardVo.getBoardType());
		System.out.println(boardVo.getBoardNum());
		System.out.println("---------------------------------");
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		
		
		return callbackMsg;	
	}
	
	//게시글 삭제
	@RequestMapping(value = "/board/boardDeleteOk.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDeleteOk(Locale locale, BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.deleteBoard(boardVo);
		System.out.println("---------------------------------");
		System.out.println(boardVo.getBoardTitle());	
		System.out.println(boardVo.getBoardComment());
		System.out.println(boardVo.getBoardType());
		System.out.println(boardVo.getBoardNum());
		System.out.println("---------------------------------");
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;	
	}
	
	

	
	
}
