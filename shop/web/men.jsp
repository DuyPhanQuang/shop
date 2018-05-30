<%-- 
    Document   : men
    Created on : May 6, 2018, 8:55:55 AM
    Author     : QuangDuy
--%>

<%@page import="model.Cart"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!-- the jScrollPane script -->
        <script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
        <script type="text/javascript" id="sourcecode">
$(function ()
{
    $('.scroll-pane').jScrollPane();
});
        </script>
        <title>Danh sach san pham JSP Page</title>
    </head>
    <body>
        
        <%
    
            ProductDAO productDAO = new ProductDAO();
            String brand_id = "";
            if (request.getParameter("brand") != null) {
                brand_id = request.getParameter("brand");
            }
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>
        
        <jsp:include page="header.jsp"></jsp:include>>
        <div class="container">
            <div class="women_main">
                <div class="col-md-9 w_content">
                    <div class="women">
                        <a href="#"><h4>Enthecwear - <span>4449 itemms</span> </h4></a>
                        <ul class="w_nav">
                            <li>Sort : </li>
                            <li><a class="active" href="#">popular</a></li> |
                            <li><a href="#">new </a></li> |
                            <li><a href="#">discount</a></li> |
                            <li><a href="#">price: Low High </a></li> 
                            <div class="clear"></div>	
                        </ul>
                        <div class="clearfix"></div>	
                    </div>
                    <!-- grids_of_4 -->
                    <div class="grids_of_4">
                        <%
                            for (Product p : productDAO.getListProductByCategory(Integer.parseInt(brand_id))) {
                        %>
                        <div class="grid1_of_4 simpleCart_shelfItem">
                            <div class="content_box"><a href="single.jsp?productID=<%=p.getProductID()%>">
                                    <div class="view view-fifth">
                                        <img src="images/1.jpg" class="img-responsive" alt=""/>
                                        <div class="mask1">
                                            <div class="info"> </div>
                                        </div>
                                </a>
                            </div>
                            <h5><a href="single.jsp?productID=<%=p.getProductID()%>"><%=p.getProductName()%></a></h5>
                            <h6><%=p.getProductDescription()%></h6>
                            <div class="size_1">
                                <span class="item_price">$<%=p.getProductSalePrice()%></span>
                                <select class="item_Size">
                                    <option value="Small">L</option>
                                    <option value="Medium">S</option>
                                    <option value="Large">M</option>	
                                    <option value="Large">XL</option>	
                                </select>
                                <div class="clearfix"></div>
                            </div>
                            <div class="size_2">
<!--                                <div class="size_2-left"> 
                                    <input type="text" class="item_quantity quantity_1" value="1" />
                                </div>-->
                                <div class="size_2-right"><a href="CartServlet?command=plus&productID=<%=p.getProductID()%>" class="item_add item_add1 btn_5" value="" />Add to Cart </a></div>
                               
                                <div class="clearfix"> </div>
                                
                            </div>
                        </div>
                    </div>
                        <%
                            }
                        %>
        <div class="clearfix"></div>
    </div>
<!-- end grids_of_4 -->
</div>
<!-- start sidebar -->
<div class="col-md-3">
    <div class="w_sidebar">
        <div class="w_nav1">
            <h4>All</h4>
            <ul>
                <li><a href="women.html">women</a></li>
                <li><a href="#">new arrivals</a></li>
                <li><a href="#">trends</a></li>
                <li><a href="#">boys</a></li>
                <li><a href="#">girls</a></li>
                <li><a href="#">sale</a></li>
            </ul>	
        </div>
        <h3>filter by</h3>
        <section  class="sky-form">
            <h4>catogories</h4>
            <div class="row1 scroll-pane">
                <div class="col col-4">
                    <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>kurtas</label>
                </div>
                <div class="col col-4">
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>kutis</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>churidar kurta</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>salwar</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>printed sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>shree</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>fashion sari</label>	
                </div>
            </div>
        </section>
        <section  class="sky-form">
            <h4>brand</h4>
            <div class="row1 scroll-pane">
                <div class="col col-4">
                    <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>shree</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                </div>
                <div class="col col-4">
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>vishud</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>amari</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>shree</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>shree</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Anouk</label>
                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>biba</label>																								
                </div>
            </div>
        </section>
        <section class="sky-form">
            <h4>colour</h4>
            <ul class="w_nav2">
                <li><a class="color1" href="#"></a></li>
                <li><a class="color2" href="#"></a></li>
                <li><a class="color3" href="#"></a></li>
                <li><a class="color4" href="#"></a></li>
                <li><a class="color5" href="#"></a></li>
                <li><a class="color6" href="#"></a></li>
                <li><a class="color7" href="#"></a></li>
                <li><a class="color8" href="#"></a></li>
                <li><a class="color9" href="#"></a></li>
                <li><a class="color10" href="#"></a></li>
                <li><a class="color12" href="#"></a></li>
                <li><a class="color13" href="#"></a></li>
                <li><a class="color14" href="#"></a></li>
                <li><a class="color15" href="#"></a></li>
                <li><a class="color5" href="#"></a></li>
                <li><a class="color6" href="#"></a></li>
                <li><a class="color7" href="#"></a></li>
                <li><a class="color8" href="#"></a></li>
                <li><a class="color9" href="#"></a></li>
                <li><a class="color10" href="#"></a></li>
            </ul>
        </section>
        <section class="sky-form">
            <h4>discount</h4>
            <div class="row1 scroll-pane">
                <div class="col col-4">
                    <label class="radio"><input type="radio" name="radio" checked=""><i></i>60 % and above</label>
                    <label class="radio"><input type="radio" name="radio"><i></i>50 % and above</label>
                    <label class="radio"><input type="radio" name="radio"><i></i>40 % and above</label>
                </div>
                <div class="col col-4">
                    <label class="radio"><input type="radio" name="radio"><i></i>30 % and above</label>
                    <label class="radio"><input type="radio" name="radio"><i></i>20 % and above</label>
                    <label class="radio"><input type="radio" name="radio"><i></i>10 % and above</label>
                </div>
            </div>						
        </section>
    </div>
</div>
<!-- start content -->
<div class="clearfix"></div>
<!-- end content -->
</div>
</div>
<div class="footer">
    <div class="container">
        <div class="footer_top">
            <div class="col-md-4 box_3">
                <h3>Our Stores</h3>
                <address class="address">
                    <p>9870 St Vincent Place, <br>Glasgow, DC 45 Fr 45.</p>
                    <dl>
                        <dt></dt>
                        <dd>Freephone:<span> +1 800 254 2478</span></dd>
                        <dd>Telephone:<span> +1 800 547 5478</span></dd>
                        <dd>FAX: <span>+1 800 658 5784</span></dd>
                        <dd>E-mail:&nbsp; <a href="mailto@example.com">info(at)buyshop.com</a></dd>
                    </dl>
                </address>
                <ul class="footer_social">
                    <li><a href=""> <i class="fb"> </i> </a></li>
                    <li><a href=""><i class="tw"> </i> </a></li>
                    <li><a href=""><i class="google"> </i> </a></li>
                    <li><a href=""><i class="instagram"> </i> </a></li>
                </ul>
            </div>
            <div class="col-md-4 box_3">
                <h3>Blog Posts</h3>
                <h4><a href="#">Sed ut perspiciatis unde omnis</a></h4>
                <p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced</p>
                <h4><a href="#">Sed ut perspiciatis unde omnis</a></h4>
                <p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced</p>
                <h4><a href="#">Sed ut perspiciatis unde omnis</a></h4>
                <p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced</p>
            </div>
            <div class="col-md-4 box_3">
                <h3>Support</h3>
                <ul class="list_1">
                    <li><a href="#">Terms & Conditions</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Payment</a></li>
                    <li><a href="#">Refunds</a></li>
                    <li><a href="#">Track Order</a></li>
                    <li><a href="#">Services</a></li>
                </ul>
                <ul class="list_1">
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Press</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="footer_bottom">
            <div class="copy">
                <p>Copyright © 2015 Buy_shop. All Rights Reserved.<a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
