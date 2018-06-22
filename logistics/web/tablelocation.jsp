<%-- 
    Document   : TableLocation
    Created on : Jun 21, 2018, 4:34:23 PM
    Author     : QuangDuy
--%>

<%@page import="dao.LocationDAO"%>
<%@page import="model.Location"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table Location</title>
    </head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
    <body>
        
        <%
            LocationDAO loDAO = new LocationDAO();
        %>
        <!-- Navigation -->
        <jsp:include page="menu.jsp"></jsp:include>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            LIST LOCATION:
                        </h1>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-6">
                        <h2>List item master</h2>
                        
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>CODE:</th>
                    <th>NAME:</th>
                    <th>ROW OF LOCATION:</th>
                    <th>TIME CREATE:</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Location lo : loDAO.getAllLocation()) {
                %>
                <tr>
                    <td><input type="text" readonly name="item_id" value="<%=lo.getLocationCode()%>"></input></td>
                    <td><input type="text" readonly name="item_code" value="<%=lo.getLocationName()%>"></input></td>
                    <td><input type="text" readonly name="item_name" value="<%=lo.getRowOfLocation()%>"></input></td>
                    <td><input type="text" readonly name="item_name" value="<%=lo.getCreateAt()%>"></input></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                        </div>
                    </div>
                </div>
                <!-- /.row -->


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
        
</html>
