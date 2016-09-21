<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/brand">Brands</a></li>
								<li><a href="/admin/budget">Budget</a></li>
								<li><a href="/admin/screensize">Screen size</a></li>
								<li><a href="/admin/smartphone">Smartphones</a></li>
								<li class="active"><a href="/admin/brand/smartphone">Brand and Smartphones</a>
									<span class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
			
	</div>
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