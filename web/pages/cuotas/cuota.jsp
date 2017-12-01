<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuota</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.numeric.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/utils.js" type="text/javascript"></script>
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
                                <div id="panelHead" class="panel-heading">
                                    Nueva cuota
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Cuota" method="post">
                                                
                                                <div class="form-group col-lg-12">
                                                    <label>Vivienda</label>
                                                    <select class="form-control" name="vivienda">
                                                        <c:if test="${not empty lstViviendas}">
                                                            <c:forEach items="${lstViviendas}" var="viv" varStatus="status">
                                                                <option value="<c:out value="${viv.idVivienda}"/>" ${cuota.vivienda.idVivienda == viv.idVivienda ? 'selected' : ''} >
                                                                    <c:out value="Torre ${viv.torre}, Número: ${viv.numero} - Residente: ${viv.residente.nombreCompleto}"/>
                                                                </option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Periodo</label>
                                                    <select class="form-control" name="periodo">
                                                        <c:if test="${not empty lstPeriodos}">
                                                            <c:forEach items="${lstPeriodos}" var="per" varStatus="status">
                                                                <option value="<c:out value="${per.idPeriodo}"/>" ${cuota.periodo.idPeriodo == per.idPeriodo ? 'selected' : ''} ><c:out value="${per.periodo}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Importe</label>
                                                    <div class='input-group' id="divimporte">
                                                        <input type="text" id="torre" class="form-control" name="importe" required="true" value="<c:out value="${cuota.importe}"/>">
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-usd"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Fecha vencimiento</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='date' class="form-control" required="true" name="fecvenc" value="<c:out value="${cuota.cadFecNac}"/>" />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <button type="submit" class="btn btn-primary">Agregar</button>
                                                    <button type="reset" class="btn btn-default">Limpiar</button>
                                                    <span><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    <input type="hidden" name="idresidente" value="<c:out value="${cuota.idCuota}"/>"/>
                                                </div>
                                            </form>
                                        </div>
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
