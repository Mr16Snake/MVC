<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row-fluid">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/brand">Brands</a></li>
								<li><a href="/admin/budget">Budget</a></li>
								<li class="active"><a href="/admin/screensize">Screen size</a><span	class="sr-only">(current)</span></li>
								<li><a href="/admin/smartphone">Smartphones</a></li>
								<li><a href="/admin/brand/smartphone">Brand and Smartphones</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
 		<div class="col-md-12 col-xs-12">
		<form:form action="/admin/screensize" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
		</div>
		<div class="col-md-7 col-xs-12">
 		<div class="col-md-12 col-xs-12">
			<form:form action="/admin/screensize" method="post" class="form-inline" 
				modelAttribute="screenSize">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="value, id"/>
				<div class="form-group">
					<form:input id="value" path="value" placeholder="screen size value" class="form-control" />
					<label for="value"><form:errors path="value" /></label>
					<button type="submit" class="btn btn-primary">Create new screen size</button>
				</div>
			</form:form>
		</div>
 			<div class="col-md-4 col-xs-4"><h4>Screen size value</h4></div>
 			<div class="col-md-4 col-xs-4"><h4>Delete</h4></div>
 			<div class="col-md-4 col-xs-4"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="screenSize">
				<div class="col-md-4 col-xs-4">${screenSize.value}</div>
 				<div class="col-md-4 col-xs-4">
					<a href="/admin/screensize/delete/${screenSize.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-4 col-xs-4">
					<a href="/admin/screensize/update/${screenSize.id}<custom:allParams/>">update</a>
				</div>
			</c:forEach>
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		<div class="col-md-2 col-xs-12">
 			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Value asc" paramValue="value"/>
						<custom:sort innerHtml="Value desc" paramValue="value,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}"/>
			</div>
		</div>
	</div>