<%@page import="dao.LocationDAO"%>
<%@page import="model.ItemObject"%>
<%@page import="dao.ReceptionDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Location"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>


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
            function showTableRD(str) {
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
                xhttp.open("GET", "settinglocation.jsp?q=" + str, true);
                xhttp.send();
            }

        </script>
        
        <script>
            
        </script>
    </head>

    <body>
        <%
            ReceptionDAO reDAO = new ReceptionDAO();
            LocationDAO loDAO = new LocationDAO();
            Location locByCode = (Location) request.getAttribute("getLocationByCode");
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
                                <form action="SettingLocationServlet" method="POST">
                                    <div class="form-group">
                                        <select id="choose_recode" name="choose_recode" onchange="showTableRD(this.value)">
                                        <c:forEach var="item" items="${chooseReCode}">
                                            <option value="${item.reCode}"> ${item.reCode}                                                
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <div id="txtHint">Data will be display here...</div>

                                </div>

                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Item Code</th>
                                            <th>Location Name</th>
                                            <th>Location code</th>
                                            <th>Row of Location</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (ItemObject io : reDAO.getReceptionDetail(request.getParameter("q"))) {
                                        %>
                                        <tr>
                                            <td><input type="text" readonly name="item_id" value="<%=io.getItemId()%>"></input></td>
                                            <td><input type="text" readonly name="item_code" value="<%=io.getItemCode()%>"></input></td>
                                            <td><input type="text" readonly name="item_name" value="<%=io.getItemName()%>"></input></td>
                                            <td>
                                                <select id="choose_location_code" name="choose_location_code">
                                                    <%
                                                        for (Location lo : loDAO.getAllLocation()) {
                                                    %>
                                                    <option value="<%=lo.getLocationCode()%>"><%=lo.getLocationCode()%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="number" name="lo_row_bycode" value="" ></input>
                                            </td>
                                            <td><input type="checkbox" name="selectedItems" value=""/></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                                <input type="hidden" value="insert" name="command">
                                <button type="submit" class="btn btn-default">ADD</button>
                            </form>
                            </div>
                        </div>
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
