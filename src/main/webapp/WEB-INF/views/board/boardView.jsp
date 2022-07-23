<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>boardView</title>
</head>

<script type="text/javascript">
	$j(document).ready(function() {
		
		var hiddenBoardType = $j("#hiddenBoardType").val();
		var hiddenBoardNum = $j("#hiddenBoardNum").val();
		console.log(hiddenBoardType);
		console.log(hiddenBoardNum);
		
		
		//리스트버튼
		$j("#listBtn").click(function() {
			location = "/board/boardList.do";
		});
		
		//게시물 수정 버튼
		$j("#modifyBtn").click(function() {			
			location = "/board/" + hiddenBoardType + "/" + 
			           + hiddenBoardNum + "/boardModify.do"
		});
		
		//게시물 삭제버튼
		$j("#deleteBtn").click(function() {
			
			if(confirm("정말로 삭제 하시겠습니까?")) {
				$j.ajax({
					url: "/board/boardDeleteOk.do",
					dataType: "json",
					type: "POST",
					data: {
						boardType : hiddenBoardType,
						boardNum : hiddenBoardNum
					},
					success: function(data, textStatus, jqXHR)
					{
						console.log(data);
						alert("삭제완료");
						
						alert("메세지: "  + data.success);
						
						location.href = "/board/boardList.do"; 
					},
					error: function(jqXHR, textStatus, errorThrown)
					{
						alert("실패입니다.");
					
					}
				});
			}	
		});		 
	});
</script>

<body>

<input type="hidden" id="hiddenBoardType" value="${boardType}">
<input type="hidden" id="hiddenBoardNum" value="${boardNum}">

<table align="center">
	<tr>
		<td>
			<table border ="1">
		       <tr>
					<td width="120" align="center">
					Type	
					</td>
					<td width="400">
					${board.codeVo.codeName}
					</td>
			    </tr>
			
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<input id="listBtn" type="button" value="list">
			<input id="modifyBtn" type="button" value="modify">
			<input id="deleteBtn" type="button" value="delete">
		</td>
	</tr>
</table>	
</body>
</html>