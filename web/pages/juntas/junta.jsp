<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Juntas</title>
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
        <script>
            var nrotemas = 0;
            $(document).ready(function(){
                $("#btnAgregarTema").click(function(){
                    nrotemas++;
                    var temahtml = '';
                    temahtml+='<div class="form-group col-lg-5 divtema'+nrotemas+'" id="divtema'+nrotemas+'">';
                    temahtml+=' <label>Tema</label>';
                    temahtml+=' <input class="form-control" name="tema" required="true">';
                    temahtml+='</div>';
                    temahtml+='<div class="form-group col-lg-6" id="divacuerdo'+nrotemas+'">';
                    temahtml+=' <label>Acuerdo</label>';
                    temahtml+=' <input class="form-control" name="acuerdo" required="true">';
                    temahtml+='</div>';
                    temahtml+='<div class="form-group col-lg-1" id="divaccion'+nrotemas+'">';
                    temahtml+=' <label>Borrar</label>';
                    temahtml+=' <button onclick="borrarTema('+nrotemas+')" type="button" class="btn btn-circle">-</button>';
                    temahtml+='</div>';
                    $("#temas").append(temahtml);
                });
            });
            
            function borrarTema(nrotema){
                var divt = '.divtema' + nrotema;
                var diva = '#divacuerdo' + nrotema;
                var divc = '#divaccion' + nrotema;
                $("div").remove(divt);
                $("div").remove(diva);
                $("div").remove(divc);
            }
        </script>
        <div id="wrapper">
            <!-- Navegación -->
            <%@include file="../../templates/navegacion.html" %>
            <!-- Contenido -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h4 class="page-header">Juntas</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Nueva junta
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Junta" method="post">
                                                <div class="form-group col-lg-4">
                                                    <label>Junta</label>
                                                    <input class="form-control" name="junta" required="true">
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Fecha de inicio</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='datetime-local' class="form-control" required="true" name="fechainicio"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <label>Fecha de fin</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='datetime-local' class="form-control" required="true" name="fechafin"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <label>Residentes</label>
                                                    <div class="form-group col-lg-12">
                                                        <c:if test="${not empty lstResidentes}">
                                                            <c:forEach items="${lstResidentes}" var="resi" varStatus="status">
                                                                <div class="checkbox col-lg-4" style="<c:if test="${!status.first}">margin-top: 10px;</c:if>">
                                                                    <label>
                                                                        <input type="checkbox" name="residente" value="<c:out value="${resi.idResidente}"/>">
                                                                        <c:out value="${resi.nombreCompleto}"/>
                                                                    </label>
                                                                </div>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <label>Temas</label>
                                                    <button id="btnAgregarTema" type="button" class="btn btn-circle">+</button>
                                                    <div id="temas" class="form-group col-lg-12">
                                                        <div class="form-group col-lg-5">
                                                            <label>Tema</label>
                                                            <input class="form-control" name="tema" required="true">
                                                        </div>
                                                        <div class="form-group col-lg-6">
                                                            <label>Acuerdo</label>
                                                            <input class="form-control" name="acuerdo" required="true">
                                                        </div>
                                                        <div class="form-group col-lg-1">
                                                        </div>
                                                    </div>
                                                </div>
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
