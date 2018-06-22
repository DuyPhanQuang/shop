<%-- 
    Document   : login
    Created on : Apr 23, 2018, 9:01:18 AM
    Author     : QuangDuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login JSP</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="Buy_shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Custom Theme files -->
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <script src="js/simpleCart.min.js"></script>
        <!-- Custom Theme files -->
        <!--webfont-->
        <link href='http://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <!-- start menu -->
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
                $(".megamenu").megamenu();
            });</script>
    </head>
    <body>
       
        
            <div class="single_top">
                <div class="container"> 
                    <div class="register">
                        <div class="col-md-6 login-right">
                            <h3>LOGIN CUSTOMERS</h3>
                            <p>If you have an account with us, please log in.</p>
                            <form action="UserServlet" method="POST">
                                <div>
                                    <span>Project code<label>*</label></span>
                                    <input type="text" name="project_code"> 
                                </div>
                                <div>
                                    <span>Email Address<label>*</label></span>
                                    <input type="text" name="email"> 
                                </div>
                                <div>
                                    <span>Password<label>*</label></span>
                                    <input type="password" name="pass"> 
                                </div>
                                <input type="hidden" value="login" name="command">
                                <input type="submit" value="Login">
                            </form>
                        </div>
                       
                    </div>
                </div>
            </div> 
    </body>
</html>

