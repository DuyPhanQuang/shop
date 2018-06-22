<%@page import="model.ItemMaster"%>
<%@page import="dao.ItemMasterDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="menu.jsp"></jsp:include>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            List item
                        </h1>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-6">
                        <h2>List item master</h2>
                        
                        <div class="table-responsive">
                            <form action="ItemMasterServlet" method="get" >
                                <p>The data from servlet: ${data}</p>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Item project code</th>
                                        <th>Item code</th>
                                        <th>name</th>
                                        <th>description</th>
                                        <th>create at</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${item_master}">
                                    <tr>  
                                        <td>${item.imPC}</td>
                                        <td>${item.imItemCode}</td>
                                        <td>${item.imItemName}</td>
                                        <td>${item.imDesc}</td>
                                        <td>${item.imCreateAt}</td>
                                        
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
<!--                            </form>-->
                        </div>
                    </div>
                </div>
                <!-- /.row -->


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
