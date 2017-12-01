<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Residentes</title>
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
    <script type="text/javascript">
        
    </script>
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
                                    Nuevo residente
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Residente" method="post">
                                                <div class="form-group col-lg-6">
                                                    <label>Tipo Documento</label>
                                                    <select class="form-control" name="tipodocumento">
                                                        <c:if test="${not empty lstTipoDocumento}">
                                                            <c:forEach items="${lstTipoDocumento}" var="tipoDoc" varStatus="status">
                                                                <option value="<c:out value="${tipoDoc.idtipodocumento}"/>" ${residente.tipoDocumento.idtipodocumento == tipoDoc.idtipodocumento ? 'selected' : ''} ><c:out value="${tipoDoc.nombreDocumento}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Documento</label>
                                                    <input class="form-control" name="nrodocumento" required="true" value="<c:out value="${residente.documento}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Fecha de nacimiento</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='date' class="form-control" required="true" name="fecnac" value="<c:out value="${residente.cadFecNac}"/>" />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Nombres</label>
                                                    <input class="form-control" name="nombres" required="true" value="<c:out value="${residente.nombres}"/>">
                                                </div>
                                                <div class="form-group col-lg-12"></div>
                                                <div class="form-group col-lg-6">
                                                    <label>Apellido paterno</label>
                                                    <input class="form-control" name="appaterno" required="true" value="<c:out value="${residente.apellidoPaterno}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Apellido materno</label>
                                                    <input class="form-control" name="apmaterno" required="true" value="<c:out value="${residente.apellidoMaterno}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Correo</label>
                                                    <input class="form-control" name="correo" value="<c:out value="${residente.correo}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Clave de acceso</label>
                                                    <input class="form-control" name="clave" required="true" value="<c:out value="${residente.clave}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <button type="submit" class="btn btn-primary">Aceptar</button>
                                                    <button type="reset" class="btn btn-default">Limpiar</button>
                                                    <span><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    <input type="hidden" name="idresidente" value="<c:out value="${residente.idResidente}"/>"/>
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
