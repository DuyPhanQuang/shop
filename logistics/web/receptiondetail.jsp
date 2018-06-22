<%@page import="model.Reception"%>
<%@page import="dao.ReceptionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            table#t01 {
                width: 100%;    
                background-color: #f1f1c1;
            }
            .dropbtn {
                background-color: #4CAF50;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1}

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
            }
        </style>

        <script>
            function showInFoByReCode(str) {
                var xhttp;
                if (str == "") {
                    document.getElementById("txtHint").innerHTML = " ";
                    return;
                }
                if (window.XMLHttpRequest) {
                    xhttp = new XMLHttpRequest();
                } else {
                    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status === 200) {
                        document.getElementById("txtHint").innerHTML = this.responseText;
                    }
                };
                xhttp.open("GET", "receptiondetail.jsp?q=" + str, true);
                xhttp.send();
            }
        </script>
    </head>

    <body>

        <%
            ReceptionDAO reDAO = new ReceptionDAO();
            Reception re = new Reception();
            if (request.getParameter("q")!= null) {
                re = reDAO.getReceptionByCode(request.getParameter("q"));
            }
        %>

        <div id="wrapper">

            <!-- Navigation -->
            <jsp:include page="menu.jsp"></jsp:include>

                <div id="page-wrapper">

                    <div class="container-fluid">


                        <div class="row">
                            <div class="col-lg-3">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <form action="ReceptionDetailServlet" method="POST">
                                    <div class="form-group">
                                        <select id="choose_recode" name="choose_recode" onchange="showInFoByReCode(this.value)">
                                        <c:forEach var="item" items="${chooseReCode}">
                                            <option value="${item.reCode}"> ${item.reCode}                                                
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <div id="txtHint">Data will be display here...</div>
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>                                              
                                                <th>Reception project code</th>
                                                <th>Status</th>
                                                <th>Note</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                                <tr>                                                  
                                                    <td><input type="text" readonly name="io_code" value="<%=re.getReProjectCode()%>"></input></td>
                                                    <td><input type="text" readonly name="io_name" value="<%=re.getReStatus()%>"></input></td>
                                                    <td><input type="text" readonly name="io_desc" value="<%=re. getReNote()%>"></input></td>
                                                </tr>
                                            
                                        </tbody>
                                    </table>
                                </div>
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Item code</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${item_master}" varStatus="status">
                                            <tr>
                                                <td><input hidden type="number" readonly name="io_id" value="${item.imID}"></input></td>
                                                <td><input type="text" readonly name="io_code" value="${item.imItemCode}"></input></td>
                                                <td><input type="text" readonly name="io_name" value="${item.imItemName}"></input></td>
                                                <td><input type="text" readonly name="io_desc" value="${item.imDesc}"></input></td>
                                                <td><input type="number" name="item_qty" value="" /></td>
                                                <td><input type="checkbox" name="selectedItems" value="${status.count}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <input type="hidden" value="insert" name="command">
                                <button type="submit" class="btn btn-default">ADD</button>
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
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
