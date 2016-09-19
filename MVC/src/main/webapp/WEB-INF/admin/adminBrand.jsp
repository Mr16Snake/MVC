<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
	<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li class="active"><a href="/admin/brand">Brands</a><span class="sr-only">(current)</span></li>
								<li><a href="/admin/budget">Budget</a></li>
								<li><a href="/admin/screensize">Screen size</a></li>
								<li><a href="/admin/smartphone">Smartphones</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	</div>
	<div class="row-fluid">
		<div class="col-md-3">
		<form:form action="/admin/brand" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
		<div class="col-md-7">
			<form:form action="/admin/brand" method="post" class="form-inline" 
				modelAttribute="brand">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="name, id"/>
				<div class="form-group">
					<label for="name"><form:errors path="name" /></label>
					<form:input id="name" path="name" placeholder="brand name" class="form-control" />
					<button type="submit" class="btn btn-primary">Create new brand</button>
				</div>
			</form:form>
			<div class="col-md-4"><h4>Brand name</h4></div>
			<div class="col-md-4"><h4>Delete</h4></div>
			<div class="col-md-4"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="brand">
				<div class="col-md-4">${brand.name}</div>
				<div class="col-md-4">
					<a href="/admin/brand/delete/${brand.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-4">
					<a href="/admin/brand/update/${brand.id}<custom:allParams/>">update</a>
				</div>
			</c:forEach>
			<div class="col-md-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}"/>
			</div>
		</div>
	</div>