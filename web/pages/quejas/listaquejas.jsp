<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Quejas</title>
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
                            <h4 class="page-header">Quejas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Lista de quejas
                                </div>
                                <div class="panel-body">                                    
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                            <thead>
                                                <tr>
                                                    <th>Residente</th>
                                                    <th>Tipo</th>
                                                    <th>Motivo</th>
                                                    <th>Fecha</th>
                                                    <th>Estado</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty lstQuejas}">
                                                    <c:forEach items="${lstQuejas}" var="queja" varStatus="status">
                                                        <tr>
                                                            <td><c:out value="${queja.residente.nombreCompleto}"/></td>
                                                            <td><c:out value="${queja.tipoQueja.tipoQueja}"/></td>
                                                            <td><c:out value="${queja.motivo}"/></td>
                                                            <td><c:out value="${queja.strFechaQueja}"/></td>
                                                            <td><c:out value="${queja.estadoQueja.estadoQueja}"/></td>
                                                            <td class="center">
                                                                <c:if test="${queja.estadoQueja.idEstadoQueja == 1}">
                                                                    <a href="ListaQueja?action=atd&idq=<c:out value="${queja.idQueja}"/>">Antender</a>
                                                                </c:if>
                                                            </td>
                                                            <td class="center">
                                                                <c:if test="${queja.estadoQueja.idEstadoQueja == 1}">
                                                                    <a href="ListaQueja?action=rec&idq=<c:out value="${queja.idQueja}"/>">Rechazar</a>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
