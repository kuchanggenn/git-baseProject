<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		//전체체크버튼 클릭시
		$j("input[name='chkBoardTypeAll']").click(function() {
			console.log("검색버튼이 클릭 됨");
			//is -> checked 체크가 되어있으면 true 
			
			$j($j(this)).each(function() {
				if($j(this).prop("checked")) {
					if(!$j("input[name='chkBoardType']").prop("disabled")) {
						$j("input[name='chkBoardType']").prop("checked", true)
					}
				}else {
					$j("input[name='chkBoardType']").prop("checked", false);
				}				
			});
		});
		
			
		//개별체크박스
		$j("input[name='chkBoardType']").click(function() {
			console.log("체크박스 몇개 선택되었는가? " + $j("input[name='chkBoardType']:checked").length)
			console.log("개별체크박스 선택됨");
			
			var checked = $j(this).is(":checked");

			  if (!checked) {
			    $j("input[name='chkBoardTypeAll']").prop("checked", false);
			  } else {
				  
			    var is_checked = true;
			    
			    $j("input[name='chkBoardType']").each(function(){
			      is_checked = is_checked && $j(this).is(":checked");
			    });
			    
			    $j("input[name='chkBoardTypeAll']").prop("checked", is_checked);
			  }			
			});		
		
		
		//검색버튼
		$j("#chkSelect").click(function() {
			
			var chk_arr = [];
			
			$j("[name='chkBoardType']:checked").each(function() {
				var chk = $j(this).val();
				chk_arr.push(chk);
			});
			
			var codeIdArr = chk_arr;
				
			console.log("체크된 값들: " + chk_arr);
			
			location.href = "/board/boardList.do?codeId=" + codeIdArr;
			
		
		});	
	});	
		//게시물 code_id 별로 조회
/* 		$j("#chkSelect").click(function() {
				
				var chk_arr = [];
				
				$j("[name='chkBoardType']:checked").each(function() {
					var chk = $j(this).val();
					chk_arr.push(chk);
				});
				
				var codeIdArr = chk_arr;
					
				console.log("체크된 값들: " + chk_arr);
			
				$j.ajax({
					url: "/board/boardList.do",
					dataType: "json",
					type: "GET",
					data: codeIdArr,
					success: function(data, textStatus, jqXHR)
					{
						console.log(data);
						alert("수정완료");
						
						alert("메세지: "  + data.success);
						
						location.href = "/board/boardList.do" 
								                     
					}, 
					error: function(jqXHR, textStatus, errorThrown)
					{
						console.log("data: " + JSON.stringify(code_id));
						alert("실패입니다.");
					
					}
					
				});	
		});	
		
	}); */

</script>
<body>
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
		
					<tr>
						<td align="center">					
							${list.codeVo.codeName}		
						</td>
						<td align="center">
							${list.boardNum}
						</td>
						<td align="center">
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do?${pageNo}">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td align="left">
				<label><input type="checkbox" name="chkBoardTypeAll">전체</label>
 					<c:forEach var = "listCodeName" items ="${listCodeName}">
							<label><input type="checkbox" name="chkBoardType" value="${listCodeName.codeId}">${listCodeName.codeName}</label>
					</c:forEach>
			<input type = "submit" id="chkSelect" name="selectBoardType" value = "검색">
		</td>
	</tr>
</table>	
</body>
</html>