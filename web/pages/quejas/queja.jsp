<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Queja</title>
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
                            <h4 class="page-header">Queja</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div id="panelHead" class="panel-heading">
                                    Nueva queja
                                </div>
                                <div class="panel-body">
                                    <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                        <jsp:setProperty name="resultado" property="mensaje" value="" />
                                    </jsp:useBean>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form role="form" action="Queja" method="post">
                                                <div class="form-group col-lg-12">
                                                    <div class="form-group col-lg-4">
                                                        <label>Tipo de queja</label>
                                                        <select class="form-control" name="tipoqueja">
                                                            <c:if test="${not empty lstTipoQueja}">
                                                                <c:forEach items="${lstTipoQueja}" var="tipo" varStatus="status">
                                                                    <option value="<c:out value="${tipo.idTipoQueja}"/>" >
                                                                        <c:out value="${tipo.tipoQueja}"/>
                                                                    </option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class="form-group col-lg-6">
                                                        <label>Motivo</label><br>
                                                        <textarea name="motivo" cols="60"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <div class="form-group col-lg-6">
                                                        <button type="submit" class="btn btn-primary">Agregar</button>
                                                        <button type="reset" class="btn btn-default">Limpiar</button>
                                                        <span><jsp:getProperty name="resultado" property="mensaje" /></span>
                                                    </div>
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
