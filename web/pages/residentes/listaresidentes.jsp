<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista residentes</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="wrapper">
            <!-- Navegación -->
            <%@include file="../../templates/navegacion.html" %>
            <!-- Contenido -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h4 class="page-header">Residentes</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Lista de residentes
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="ListaResidente" method="get">
                                        <div class="dataTable_wrapper">
                                            <div class="input-group">
                                                <input type="text" name="nombre" class="form-control" placeholder="Ingrese el nombre del residente a buscar...">
                                                <span class="input-group-btn">
                                                  <button class="btn btn-default" type="button">Buscar</button>
                                                </span>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                <thead>
                                                    <tr>
                                                        <th>Documento</th>
                                                        <th>Nombre</th>
                                                        <th>Correo</th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty lstResidentes}">
                                                        <c:forEach items="${lstResidentes}" var="residente" varStatus="status">
                                                            <tr>
                                                                <td><c:out value="${residente.documento}"/></td>
                                                                <td><c:out value="${residente.nombreCompleto}"/></td>
                                                                <td><c:out value="${residente.correo}"/></td>
                                                                <td class="center"><a href="Residente?action=edit&idr=<c:out value="${residente.idResidente}"/>" class="fa fa-pencil-square-o"></a></td>
                                                                <td class="center"><a href="#" class="fa fa-times"></a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
