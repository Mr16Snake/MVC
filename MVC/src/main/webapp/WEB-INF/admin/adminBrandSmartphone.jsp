<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<tr>
		<th>Brand</th>
		<th>Smartphone</th>
	</tr>
	<c:forEach items="${brands}" var="brand">
		<c:forEach items="${brand.smartphones}" var="smartphone" varStatus="vs">
			<tr>
				<c:choose>
					<c:when test="${vs.isFirst()}">
						<td>${brand.name}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
				<td>${smartphone.model}</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>