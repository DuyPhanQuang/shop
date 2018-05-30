<%-- 
    Document   : register
    Created on : Apr 23, 2018, 9:11:10 AM
    Author     : QuangDuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var x_timer;
                $("#email").keyup(function (e) {
                    clearTimeout(x_timer);
                    var user_name = $(this).val();
                    x_timer = setTimeout(function () {
                        check_username_ajax(user_name);
                    }, 1000);
                });

                function check_username_ajax(username) {
                    $("#user-result").html('<img src="img/ajax-loader.gif" />');
                    $.post('CheckEmailServlet', {'username': username}, function (data) {
                        $("#user-result").html(data);
                    });
                }
            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="single_top">
                <div class="container"> 
                    <div class="register"> 
                            <div class="register-top-grid">
                                <h3>PERSONAL INFORMATION</h3>
                                <form action="UsersServlet" method="POST">
                                    <div>
                                        <span class="word">Username *</span>
                                        <input type="text" name="email" id="email">
                                        <span id="user-result"></span>
                                    </div> 	
                                    <div> 
                                        <span class="word">Password *</span>
                                        <input type="password" name="pass">
                                        <span></span>
                                    </div>			
                                    <input type="hidden" value="insert" name="command">
                                    <input type="submit" value="Register"> 
                                </form>
                                <form action="ProductServlet" method="POST">
                                    <div>
                                        <span class="word">Ten hang *</span>
                                        <input type="text" name="name" id="name">
                                        <span id="user-result"></span>
                                    </div> 	
                                    <div> 
                                        <span class="word">Mo ta *</span>
                                        <input type="text" name="description">
                                        <span></span>
                                    </div>
                                    <div> 
                                        <span class="word">gia nhap *</span>
                                        <input type="text" name="importPrice">
                                        <span></span>
                                    </div>
                                    <div> 
                                        <span class="word">gia ban *</span>
                                        <input type="text" name="salePrice">
                                        <span></span>
                                    </div>
                                    <input type="hidden" value="insert" name="command">
                                    <input type="submit" value="Register"> 
                                </form>
                                <div class="clearfix"></div>                             
                            </div>
                    </div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
