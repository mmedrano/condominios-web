<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/sb-admin-2.css" rel="stylesheet">
        
        <script src="resources/js/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/metisMenu/metisMenu.js" type="text/javascript"></script>
        <script src="resources/js/sb-admin-2.js" type="text/javascript"></script>
        
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Iniciar Sesión</h3>
                        </div>
                        <div class="panel-body">
                            <jsp:useBean id="resultado" scope="request" class="com.condominiosweb.util.CWResultado">
                                <jsp:setProperty name="resultado" property="mensaje" value="" />
                            </jsp:useBean>
                            
                            <form role="form" action="Login" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Usuario" id="usuario" name="usuario" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" id="contrasena" name="contrasena" type="password" value="">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="remember" type="checkbox" value="Remember Me">Recordarme
                                        </label>
                                    </div>
                                    
                                    <button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                                    <br/>
                                    <jsp:getProperty name="resultado" property="mensaje" />
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
