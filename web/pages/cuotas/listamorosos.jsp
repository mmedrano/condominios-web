<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Morosos</title>
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
                            <h4 class="page-header">Cuotas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Lista de morosos
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="ListaMoroso" method="get">
                                        <div class="input-group">
                                            <label>Periodo</label>
                                            <select class="form-control" name="periodo" onchange="this.form.submit()">
                                                <option value="0">Todos</option>
                                                <c:if test="${not empty lstPeriodos}">
                                                    <c:forEach items="${lstPeriodos}" var="per" varStatus="status">
                                                        <option value="<c:out value="${per.idPeriodo}"/>" ${periodoSel.idPeriodo == per.idPeriodo ? 'selected' : ''} >
                                                            <c:out value="${per.periodo}"/>
                                                        </option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                        <br/>
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                <thead>
                                                    <tr>
                                                        <th>Torre</th>
                                                        <th>Numero</th>
                                                        <th>Residente</th>
                                                        <th>Importe</th>
                                                        <th>Vencimiento</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty lstMorosos}">
                                                        <c:forEach items="${lstMorosos}" var="moroso" varStatus="status">
                                                            <tr>
                                                                <td><c:out value="${moroso.vivienda.torre}"/></td>
                                                                <td><c:out value="${moroso.vivienda.numero}"/></td>
                                                                <td><c:out value="${moroso.vivienda.residente.nombreCompleto}"/></td>
                                                                <td><c:out value="${moroso.importe}"/></td>
                                                                <td><c:out value="${moroso.strFechaVenc}"/></td>
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
