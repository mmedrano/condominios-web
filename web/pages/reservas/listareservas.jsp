<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Reservas</title>
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
            <c:choose>
                <c:when test="${usuario.residente != null}">
                    <%@include file="../../templates/navegacionresidente.html" %>
                </c:when>
                <c:otherwise>
                    <%@include file="../../templates/navegacion.html" %>
                </c:otherwise>
            </c:choose>
            <!-- Contenido -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h4 class="page-header">Reservas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Lista de reservas
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="ListaReserva" method="get">
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                <thead>
                                                    <tr>
                                                        <c:if test="${usuario.residente == null}">
                                                            <th>Residente</th>
                                                        </c:if>
                                                        <th>&Aacute;rea com&uacute;n</th>
                                                        <th>Fecha inicio</th>
                                                        <th>Fecha fin</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty lstReservas}">
                                                        <c:forEach items="${lstReservas}" var="reserva" varStatus="status">
                                                            <tr>
                                                                <c:if test="${usuario.residente == null}">
                                                                    <td><c:out value="${reserva.residente.nombreCompleto}"/></td>
                                                                </c:if>
                                                                <td><c:out value="${reserva.areaComun.areaComun}"/></td>
                                                                <td><c:out value="${reserva.strFechaReservaInicio}"/></td>
                                                                <td><c:out value="${reserva.strFechaReservaFin}"/></td>
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
