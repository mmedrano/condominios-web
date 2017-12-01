<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago de Cuota</title>
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
            <%@include file="../../templates/navegacionresidente.html" %>
            <!-- Contenido -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h4 class="page-header">Pago</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="panel panel-default">
                                <div id="panelHead" class="panel-heading">
                                    Pago de cuota
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Pago" method="post">
                                                <div class="form-group col-lg-6 panel panel-green">
                                                    <label>Periodo</label><br>
                                                    <span><c:out value="${cuota.periodo.periodo}"/></span>
                                                    <br><br>
                                                    <label>Residente</label><br>
                                                    <span><c:out value="${cuota.vivienda.residente.nombreCompleto}"/></span>
                                                    <br><br>
                                                    <label>Vivienda</label><br>
                                                    <span>Torre: <c:out value="${cuota.vivienda.torre}"/> - Numero: <c:out value="${cuota.vivienda.numero}"/></span>
                                                    <br><br>
                                                    <label>Importe</label><br>
                                                    <label><c:out value="${cuota.importe}"/></label>
                                                    <br>
                                                </div>
                                                
                                                <div class="form-group col-lg-6">
                                                    <label>Forma de pago</label>
                                                    <select class="form-control" name="tipopago">
                                                        <c:if test="${not empty lstTipoPago}">
                                                            <c:forEach items="${lstTipoPago}" var="tipo" varStatus="status">
                                                                <option value="<c:out value="${tipo.idTipoPago}"/>"><c:out value="${tipo.tipoPago}"/></option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                    <br>
                                                    <label>Fecha de pago</label>
                                                    <div class='input-group date' id='datetimepicker1'>
                                                        <input type='date' class="form-control" required="true" name="fecpago" value="" />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                    <br>
                                                    <button type="submit" class="btn btn-primary">Pagar</button>
                                                    <span><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    <input type="hidden" name="idv" value="<c:out value="${cuota.vivienda.idVivienda}"/>"/>
                                                    <input type="hidden" name="idp" value="<c:out value="${cuota.periodo.idPeriodo}"/>"/>
                                                    <input type="hidden" name="importe" value="<c:out value="${cuota.importe}"/>"/>
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
