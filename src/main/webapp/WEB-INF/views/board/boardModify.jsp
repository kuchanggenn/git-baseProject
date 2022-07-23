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
		
		
		
		$j("#modifyBtnOk").click(function() {
			
			var boardTitle = $j("#boardTitle").val();
			var boardComment = $j("#boardComment").val();
			var hiddenBoardType = $j("#hiddenBoardType").val();
			var hiddenBoardNum = $j("#hiddenBoardNum").val();
			
		 	$j.ajax({
				url: "/board/boardModifyOk.do",
				dataType: "json",
				type: "POST",
				data: {
					boardTitle : boardTitle,
					boardComment : boardComment,
					boardType : hiddenBoardType,
					boardNum : hiddenBoardNum
				},
				success: function(data, textStatus, jqXHR)
				{
					console.log(data);
					alert("수정완료");
					
					alert("메세지: "  + data.success);
					
					location.href = "/board/" + hiddenBoardType  
					                          + "/" 
					                          + hiddenBoardNum
					                          + "/boardView.do" 
							                     
				}, 
				error: function(jqXHR, textStatus, errorThrown)
				{
					alert("실패입니다.");
				
				}
				
			}); 
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
					Title
					</td>
					<td width="400">
					<input type="text" id="boardTitle" name="boardTitle" value="${board.boardComment}" style="width:400px;">
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					<input type="text" id="boardComment" name="boardComment" value="${board.boardComment}" style="width:400px; height:300px;">	
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
			<input id="modifyBtnOk" type="button" value="modifyOk">
		</td>
	</tr>
</table>	
</body>
</html>