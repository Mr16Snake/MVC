<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script>
$(function() {
	$('select[name=brand]').chosen();
});
</script>
<div class="row-fluid">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="collapse navbar-collapse" id="">
						<ul class="nav navbar-nav">
								<li><a href="/admin/brand">Brands</a></li>
								<li><a href="/admin/budget">Budget</a></li>
								<li><a href="/admin/screensize">Screen size</a></li>
								<li class="active"><a href="/admin/smartphone">Smartphones</a><span	class="sr-only">(current)</span></li>
								<li><a href="/admin/brand/smartphone">Brand and Smartphones</a></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>
		<div class="row-fluid">
		<div class="col-md-3">
		<form:form action="/admin/brand" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
<%-- 					<form:input path="search" placeholder="search" class="form-control" /> 
					<button type="submit" class="btn btn-primary">Ok</button> --%>
				</div>
			</form:form>
		</div>
		<div class="col-md-7">
	<form:form action="/admin/smartphone" method="post" modelAttribute="form" class="form-inline" enctype="multipart/form-data" >
		<form:errors path="*"/>
		<form:hidden path="id" />
		<form:hidden path="path" />
		<form:hidden path="version" />
		<custom:hiddenInputs excludeParams="model, id, brand, budget, screenSize, mainCamera, frontCamera, powerful, batteryGood, material,
		url, description, path, version"/>
			<div class="form-group">
			<form:select path="brand" items="${brands}" itemLabel="name" itemValue="id">
				<option value="0">Brand</option>
			</form:select>
			<label for="model"><form:errors path="model"/></label>
			<form:input path="model" id="model" class="form-control"  placeholder="Model name" />
			<form:select path="budget" items="${budgets}" itemLabel="value" itemValue="id">
				<option value="0">Budget</option>
			</form:select>
			<form:select path="screenSize" items="${screens}" itemLabel="value" itemValue="id">
				<option value="0">ScreenSize</option>
			</form:select>
			<form:select path="material" id="material" itemLabel="material" itemValue="id">
						<option value="OTHER">Other</option>
						<option value="METAL">Metal</option>
						<option value="GLASS">Glass</option>
			</form:select></br>
			<form:checkbox path="mainCamera" />Check if main camera is good</br>
			<form:checkbox path="frontCamera" />Check if front camera is good</br>
			<form:checkbox path="batteryGood" />Check if battery is good</br>
			<form:checkbox path="powerful" />Check if processor is good for 3D games</br>
			<label for="url"><form:errors path="url" /></label>
			<form:input path="url" id="url" class="form-control" placeholder="URL to check price" />
			<form:input path="description" id="description" class="form-control" placeholder="description to smartphone"/>
			<label class="btn btn-default btn-file">
         		Browse <input type="file" name="file" style="display: none;">
     		</label>
			<button type="submit" class="btn btn-primary">Create new smartphone</button>
			</div>
	</form:form>
	<div>
		<div class="col-md-3"><h4>Image</h4></div>
		<div class="col-md-3"><h4>Model</h4></div>
		<div class="col-md-3"><h4>Delete</h4></div>
		<div class="col-md-3"><h4>Update</h4></div>
	</div>
		<c:forEach items="${page.content}" var="smartphone">
		<div class="row">
			<div class="col-md-3"><img class="img-thumbnail" width="100" src="/images/smartphone/${smartphone.id}${smartphone.path}?version=${smartphone.version}" /></div>
			<div class="col-md-3"><h4>${smartphone.model}</h4></div>
			<div class="col-md-3"><h4><a href="/admin/smartphone/delete/${smartphone.id}<custom:allParams/>">delete</a></h4></div>
			<div class="col-md-3"><h4><a href="/admin/smartphone/update/${smartphone.id}<custom:allParams/>">update</a></h4></div>
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
						<custom:sort innerHtml="Brand name asc" paramValue="brand.name"/>
						<custom:sort innerHtml="Brand name desc" paramValue="brand.name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
			</div>
		</div>
	</div>