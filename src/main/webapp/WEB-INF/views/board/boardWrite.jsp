<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>boardWrite</title>
</head>
<script type="text/javascript">

$j(document).ready(function(){
			
			//행추가 		
			var trCnt = 2;
			$j(".a:button").click(function() {
			console.log("행추가 버튼 클릭됨");
			
				if ($j("input[name='table2Chk']").length < 3 ) {		
					console.log("숫자카운트 :" + trCnt);
/* 					var innerHtml = "";
					var innerHtml = "<tr>" +
										"<td width='120' align='center'>" + 
										"<label><input type='checkbox' name='table2Chk' value="+trCnt+" style'=margin-right:5px';></label>" +
										"Title" +
										"</td>" +
										"<td width='400'>" +
										"<input name='boardTitle' type='text' size='50' value='${board.boardTitle}'>" + 
										"</td>" +
									"</tr"; */
				    var a =$j(".table2 tr:nth-child(1)").clone();			
					var b = $j(".table2 tr:nth-child(2)").clone();
					var c = $j(".table2 tr:nth-child(3)").clone();
					b.find("input").val("");
					c.find("textarea").val("");
								
					$j(".table2 tr:last").before(a).before(b).before(c);	
					return trCnt++;
				} 
				else  {
					alert("3개까지만");
				}				
			});
			
			//행삭제
			$j(".b:button").click(function() {
				console.log("행삭제버튼 클림됨");
				
				if($j("input[name='table2Chk']").length == 1) {
					alert("삭제할수 없습니다.");
				} else if($j("input[name='table2Chk']:checked").length  == 0) {
					alert("선택된 항목이 없습니다.");
				} else if($j("input[name='table2Chk']:checked").length  == $j("input[name='table2Chk']").length) {
					alert("작성글 하나 이상은 있어야 합니다.");
				}else {
					$j("#Table2 input[type='checkbox']:checked").closest('tr').prev().remove();
					$j("#Table2 input[type='checkbox']:checked").closest('tr').next().remove();
					$j("#Table2 input[type='checkbox']:checked").closest('tr').remove();
				}		
			});	
			
		
		
			
			
			
		$j("#submit").on("click",function(){
				
			var page = $j('#hiddenPage').val();
			console.log("page: "+ page);	
			
			var boardType = $j("[name='boardType']").length;
			console.log("코드타입 길이: " + boardType);
			
		/* 	 var codeTypeArr = new Array();
	   		    
				for(var i=0; i<codeType; i++) {
					codeTypeArr[1] = $j("[name='codeType']").eq(i).val();
					console.log(codeTypeArr[i]);
				} */
			
			var $frm = $j('.boardWrite :input, select');
			
			$frm.val(function(index, value) {
			return value.replace(/,/g, '!!');	
			});
			
			var param = $frm.serialize();
			console.log(param);
			
		/* 	for(i=0; i<arr.size; i++) {
				 param = changeSerialize(param, "boardTitle", arr[0]);
			}
			 
			
					
			function changeSerialize(values, k, v) {
				var found = false;
				console.log("값길이: " + size);
				for (i = 0; i < size || !found; i++) {
					
			        if ( values[i].name == k ) { 

			            values[i].value = v.replace(/,/g, '!!');
						console.log("boardTitle" + i + " :" + boardTitle)
			            found = true;

			        }

			    }
 				if(!found) {
					values.push (
						{
							name : k,
							value : v
						}	
					);
				} 
				return values;
			}   */
			
		 	/* param = changeSerialize(param, "boardTitle", boardTitle);
			
			function changeSerialize(values, k, v) {
				var found = false;
				console.log("값길이: " + size);
				for (i = 0; i < size || !found; i++) {
					
			        if ( values[i].name == k ) { 

			            values[i].value = v.replace(/,/g, '!!');

			            found = true;

			        }

			    }
 				if(!found) {
					values.push (
						{
							name : k,
							value : v
						}	
					);
				} 
				return values;
			}  */
					 
		   $j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
			    	
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					console.log("data: " + JSON.stringify(param));
					
					location.href = "/board/boardList.do?pageNo=" + page; 
					
																  
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	console.log("ddata: " + param);
			    	alert("실패입니다.");
			    }
			});       
		});
	});
	

</script>
<body>

<input type="hidden" id="hiddenPage" value="${pageNo}">

<form class="boardWrite">
	<table align="center">
	  
		<tr>
			<td>
			<input type="button" name="aaa" class='a' value="행추가">
			<input type="button" name="bbb" class='b' value="행삭제">
			<input id="submit" type="button" value="작성" style="float: right">
			</td>
		</tr>
		
	
	<!--
		<label>
		<div id="check">
			<input type="checkbox" name="createChkBox1" value="createChkBox" style="margin-right:5px;"> 행추가
		</div>
		<div id="close"><input id="submit" type="button" value="작성"></div>
		</label>
	-->
	
		<tr>
			<td>
				<table border ="1" class="table2" id="Table2"> 
				 
					<tr>
						<td width="120" align="center">
							Type	
						</td>
						<td width="400">
							<select id="boardType" name="boardType">
								<c:forEach items="${listCodeName}" var="boardType">
									<option value="${boardType.codeId}">${boardType.codeName}</option>
 								</c:forEach>
							</select>
						</td>
					</tr>
				
					<tr>
						<td width="120" align="center">
							<input type="checkbox" name="table2Chk" value="1" style="margin-right:0px;">
							Title	
						</td>
						<td width="400">
						<input name="boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardComment"  rows="20" cols="55"><c:out value="${board.boardComment}" /></textarea>
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
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>