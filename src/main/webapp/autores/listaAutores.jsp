<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Lista de autores</title>
<%@ include file='/cabecera.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<jsp:include page="/menu.jsp"/>  
	<div class="container">
		<div class="row">
			<h3>Lista de autores</h3>
		</div>
		<div class="row">
			<div class="col-md-10">
				<a type="button" class="btn btn-primary btn-md"
					href="/MVC/AutoresController?op=nuevo"> Nuevo autor</a> <br />
				<br />
				<table class="table table-striped table-bordered table-hover"
					id="tabla">
					<thead>
						<tr>
							<th>Codigo del autor</th>
							<th>Nombre del autor</th>
							<th>Nacionalidad</th>
							<th>Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.listaAutores}" var="autores">
							<tr>
								<td>${autores.codigoAutor}</td>
								<td>${autores.nombreAutor}</td>
								<td>${autores.nacionalidad}</td>
								<td><a class="btn btn-primary"
									href="/MVC/AutoresController?op=obtener&id=${autores.codigoAutor}">
									<span class="glyphicon glyphicon-edit"></span> Editar</a> 
									<a class="btn btn-danger"
									href="javascript:eliminar('${autores.codigoAutor}')"><span
										class="glyphicon glyphicontrash"></span> Eliminar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
	<script>
		$(document).ready(function() {
			$('#tabla').DataTable();
		});
		<c:if test="${not empty exito}">
		alertify.success('${exito}');
		<c:set var="exito" value="" scope="session" />
		</c:if>
		<c:if test="${not empty fracaso}">
		alertify.error('${fracaso}');
		<c:set var="fracaso" value="" scope="session" />
		</c:if>
		if(!alertify.myAlert){
			  //define a new dialog
			  alertify.dialog('myAlert',function(){
			    return{
			      main:function(message){
			        this.message = message;
			      },
			      setup:function(){
			          return { 
			            buttons:[{text: "cool!", key:27/*Esc*/}],
			            focus: { element:0 }
			          };
			      },
			      prepare:function(){
			        this.setContent(this.message);
			      }
			  }});
		}
		
		
		function eliminar(id) {
			alertify.confirm("¿Realmente decea eliminar este Autor?", function(e) {
				if (e) {
					location.href = "/MVC/AutoresController?op=eliminar&id=" + id;
				}else{
					
				}
			});
		}
	</script>
</body>
</html>