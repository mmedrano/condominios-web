<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visitas</title>
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
                            <h4 class="page-header">Visitas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Nueva visita
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Visita" method="post">
                                                <div class="form-group col-lg-12">
                                                    <label>Residente</label>
                                                    <select class="form-control" name="residente">
                                                        <c:if test="${not empty lstResidentes}">
                                                            <c:forEach items="${lstResidentes}" var="resi" varStatus="status">
                                                                <option value="<c:out value="${resi.idResidente}"/>"><c:out value="${resi.nombreCompleto}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Tipo Documento</label>
                                                    <select class="form-control" name="tipodoc">
                                                        <c:if test="${not empty lstTipoDoc}">
                                                            <c:forEach items="${lstTipoDoc}" var="tipo" varStatus="status">
                                                                <option value="<c:out value="${tipo.idtipodocumento}"/>"><c:out value="${tipo.nombreDocumento}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Documento</label>
                                                    <input class="form-control" name="documento" required="true">
                                                </div>
                                                <div class="form-group col-lg-8">
                                                    <label>Nombre visitante</label>
                                                    <input class="form-control" name="nombre" required="true">
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Fecha de visita</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='datetime-local' class="form-control" required="true" name="fecha" value="<c:out value="${residente.cadFecNac}"/>" />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                        <br>
                                                <div class="form-group col-lg-6">
                                                    <button type="submit" class="btn btn-primary">Aceptar</button>
                                                    <button type="reset" class="btn btn-default">Limpiar</button>
                                                    <span><jsp:getProperty name="resultado" property="mensaje" /></span>
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
