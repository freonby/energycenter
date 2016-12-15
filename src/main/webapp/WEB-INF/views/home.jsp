<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<div id="wb_Form1"
		style="position: absolute; width: 663px; height: 172px;">
		<form name="Form1" method="get" action="go" enctype="text/plain"
			id="Form1">
			<input type="submit" id="Button1" name="" value="Чтение"
				style="position: absolute; left: 29px; top: 125px; width: 96px; height: 25px; z-index: 22;">
			<select name="Combobox" size="1" id="Combobox1"
				style="position: absolute; left: 31px; top: 75px; width: 95px; height: 20px; z-index: 23;">
				<c:forEach var="portName" items="${portList}">
					<option>${portName}</option>
				</c:forEach>
			</select> <input type="text" id="netAddr"
				style="position: absolute; left: 30px; top: 26px; width: 94px; height: 19px; line-height: 19px; z-index: 24;"
				name="netAddr" value="0" maxlength="3">
		</form>
	</div>
	


</body>
</html>
