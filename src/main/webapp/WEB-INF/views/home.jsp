<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
</head>
<body>

	<form:form method="get" action="check-form" commandName="device">
		<form:label path="netAddress">Сетевой адрес:</form:label>
		<form:input path="netAddress" />
		<form:label path="portName">Порт:</form:label>
		<form:select path="portName">
			<c:forEach var="portItem" items="${portList}">
				<form:option value="${portItem}"></form:option>
			</c:forEach>
		</form:select>
		<input type="submit" value="Read">
	</form:form>




</body>
</html>
