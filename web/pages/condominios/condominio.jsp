<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Condominio</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js" type="text/javascript"></script>
    </head>
    <script type="text/javascript">
        $(document).ready(function(){
            var idc = $("#idcondominio").val();
            $("#btnEnviar").text(idc ? "Actualizar" : "Agregar");
            $("#panelHead").text(idc ? "Actualizar condominio" : "Nuevo condominio");
            
            $("#btnEnviar").click(function(){
                var mensaje = '';
                var nombre = $("#nombre").val();
                var descripcion = $("#descripcion").val();
                
                if(!nombre){
                    mensaje = 'El campo condominio no puede estar vacío.';
                }else if(!nombre.trim()){
                    mensaje = 'El campo condominio no puede estar vacío.';
                }else if(!descripcion){
                    mensaje = 'El campo descripción no puede estar vacío.';
                }else if(!descripcion.trim()){
                    mensaje = 'El campo descripción no puede estar vacío.';
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
                            <h4 class="page-header">Condominios</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div id="panelHead" class="panel-heading">
                                    condominio
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Condominio" method="post">
                                                <div class="form-group col-lg-6">
                                                    <label>Condominio</label>
                                                    <input id="nombre" class="form-control" required="true" name="nombre" value="<c:out value="${condominio.nombre}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label>Descripci&oacute;n</label>
                                                    <input id="descripcion" class="form-control" name="descripcion" value="<c:out value="${condominio.descripcion}"/>">
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <button id="btnEnviar" type="submit" class="btn btn-primary">Aceptar</button>
                                                    <button type="reset" class="btn btn-default">Limpiar</button>
                                                    <span id="mensaje"><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    <input id="idcondominio" type="hidden" name="idcondominio" value="<c:out value="${condominio.idCondominio}"/>"/>
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
