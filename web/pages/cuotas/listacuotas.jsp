<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Cuotas</title>
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
                            <h4 class="page-header">Cuotas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Lista de cuotas por vivienda
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="ListaCuota" method="get">
                                        <c:if test="${usuario.residente == null}">
                                            <div class="input-group">
                                                <label>Vivienda</label>
                                                <select class="form-control" name="vivienda" onchange="this.form.submit()">
                                                    <option value="0">Seleccione</option>
                                                    <c:if test="${not empty lstViviendas}">
                                                        <c:forEach items="${lstViviendas}" var="viv" varStatus="status">
                                                            <option value="<c:out value="${viv.idVivienda}"/>" ${viviendaSel.idVivienda == viv.idVivienda ? 'selected' : ''} >
                                                                <c:out value="Torre ${viv.torre}, Número: ${viv.numero} - Residente: ${viv.residente.nombreCompleto}"/>
                                                            </option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                            <br/>
                                        </c:if>
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                <thead>
                                                    <tr>
                                                        <c:if test="${usuario.residente != null}">
                                                            <th>Torre</th>
                                                            <th>N&uacute;mero</th>
                                                        </c:if>
                                                        <th>Periodo</th>
                                                        <th>Importe</th>
                                                        <th>Pagado</th>
                                                        <th>Vencimiento</th>
                                                        <th>Estado</th>
                                                        <c:if test="${usuario.residente != null}">
                                                            <th></th>
                                                        </c:if>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty lstCuotas}">
                                                        <c:forEach items="${lstCuotas}" var="cuota" varStatus="status">
                                                            <tr>
                                                                <c:if test="${usuario.residente != null}">
                                                                    <td><c:out value="${cuota.vivienda.torre}"/></td>
                                                                    <td><c:out value="${cuota.vivienda.numero}"/></td>
                                                                </c:if>
                                                                <td><c:out value="${cuota.periodo.periodo}"/></td>
                                                                <td><c:out value="${cuota.importe}"/></td>
                                                                <td><c:out value="${cuota.importePagado}"/></td>
                                                                <td><c:out value="${cuota.strFechaVenc}"/></td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${cuota.estado == 'P'}">
                                                                            Pendiente de pago
                                                                        </c:when>
                                                                        <c:when test="${cuota.estado == 'C'}">
                                                                            Cancelado
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:out value="${cuota.estado}"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <c:if test="${usuario.residente != null}">
                                                                    <td class="center">
                                                                        <c:if test="${cuota.estado == 'P'}">
                                                                            <a href="Pago?idv=<c:out value="${cuota.vivienda.idVivienda}"/>&idp=<c:out value="${cuota.periodo.idPeriodo}"/>">Pagar</a>
                                                                        </c:if>
                                                                    </td>
                                                                </c:if>
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
