<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vivienda</title>
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
        $(document).ready(function(){
            var idv = $("#idvivienda").val();
            $("#btnEnviar").text(idv ? "Actualizar" : "Agregar");
            $("#panelHead").text(idv ? "Actualizar vivienda" : "Nueva vivienda");
            
            $("#btnEnviar").click(function(){
                var mensaje = '';
                var torre = $("#torre").val();
                var numero = $("#numero").val();
                var metraje = $("#metraje").val();
                
                if(!torre){
                    mensaje = 'El campo torre no puede estar vacío.';
                }else if(!torre.trim()){
                    mensaje = 'El campo torre no puede estar vacío.';
                }else if(!$.isNumeric(numero)){
                    mensaje = 'Campo número: Ingrese un valor válido.';
                }else if(numero <= 0){
                    mensaje = 'El valor del campo número debe ser mayor a 0.';
                }else if(!$.isNumeric(metraje)){
                    mensaje = 'Campo metraje: Ingrese un valor válido.';
                }else if(metraje <= 0){
                    mensaje = 'El valor del campo metraje debe ser mayor a 0.';
                }
                
                if(mensaje){
                    $("#mensaje").text(mensaje);
                    return false;
                }else{
                    $('form').submit();
                }
            });
        });
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
                            <h4 class="page-header">Viviendas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div id="panelHead" class="panel-heading">
                                    vivienda
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Vivienda" method="post">
                                                <div class="form-group col-lg-6">
                                                    <label>Residente</label>
                                                    <select class="form-control" name="residente">
                                                        <c:if test="${not empty lstResidentes}">
                                                            <c:forEach items="${lstResidentes}" var="resid" varStatus="status">
                                                                <option value="<c:out value="${resid.idResidente}"/>" ${vivienda.residente.idResidente == resid.idResidente ? 'selected' : ''} ><c:out value="${resid.nombreCompleto}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Condominio</label>
                                                    <select class="form-control" name="condominio">
                                                        <c:if test="${not empty lstCondominios}">
                                                            <c:forEach items="${lstCondominios}" var="cond" varStatus="status">
                                                                <option value="<c:out value="${cond.idCondominio}"/>" ${vivienda.condominio.idCondominio == cond.idCondominio ? 'selected' : ''} ><c:out value="${cond.nombre}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Torre</label>
                                                    <input id="torre" class="form-control" name="torre" required="true" value="<c:out value="${vivienda.torre}"/>">
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>N&uacute;mero</label>
                                                    <input id="numero" class="form-control" name="numero" required="true" value="<c:out value="${vivienda.numero}"/>" onkeypress="return justNumbers(event);">
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Metraje</label>
                                                    <input id="metraje" class="form-control" name="metraje" required="true" value="<c:out value="${vivienda.metraje}"/>">
                                                </div>
                                                <div class="form-group col-lg-8">
                                                    <button id="btnEnviar" type="submit" class="btn btn-primary">Agregar</button>
                                                    <button type="reset" class="btn btn-default">Limpiar</button>
                                                    <span id="mensaje"><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    <input id="idvivienda" type="hidden" name="idvivienda" value="<c:out value="${vivienda.idVivienda}"/>"/>
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
