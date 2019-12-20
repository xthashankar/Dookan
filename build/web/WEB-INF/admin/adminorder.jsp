<%-- 
    Document   : adminorder
    Created on : Nov 28, 2019, 9:16:05 PM
    Author     : shankar xtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="adminheader.jsp"/>
  <body class="fixed-left">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Top Bar Start -->
            <div class="topbar">

                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="text-center">
                        <a href="#" class="logo"><i class="icon-magnet icon-c-logo"></i><span>Ub<i class="md md-album"></i>ld</span></a>
                        <!-- Image Logo here -->
                        <!--<a href="index.html" class="logo">-->
                            <!--<i class="icon-c-logo"> <img src="assets/images/logo_sm.png" height="42"/> </i>-->
                            <!--<span><img src="assets/images/logo_light.png" height="20"/></span>-->
                        <!--</a>-->
                    </div>
                </div>


            <!-- ========== Left Sidebar Start ========== -->

           


            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->                      
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container">

                        <!-- Page-Title -->
                        <div class="row">
                            <div class="col-sm-12">
                                

                                <h4 class="page-title">Orders</h4>
                                <ol class="breadcrumb">
                                    <li><a href="#">Dookan</a></li>
                                    <li class="active">Orders</li>
                                </ol>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-lg-12">
                        		<div class="card-box">
                        			<div class="row m-t-10 m-b-10">
			                        	<div class="col-sm-6 col-lg-8">
			                        	
			                        	</div>
			                        </div>

                        			<div class="table-responsive">
                                        <table class="table table-actions-bar">
                                            <thead>
                                                <tr>
                                                    <th>Buyer first Name</th>
                                                    <th>Buyer Last Name</th>
                                                    <th>Email</th>
                                                    <th>Phone number</th>
                                                    <th>Quantity</th>
                                                  
                                                  
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach var="orders" items="${orders}">
                                                <tr>
<!--                                                    <td><img src="assets/images/products/iphone.jpg" class="thumb-sm" alt=""> </td>-->
                                                    <td>${orders.cartid.buyerid.firstName}</td>
                                                     <td>${orders.cartid.buyerid.lastName}</td>
                                                    
                                                    <td>${orders.cartid.buyerid.email} </td>
                                                    <td>${orders.cartid.buyerid.phoneNum} </td>
                                                    <td>${orders.cartid.totalqantity}</td>
                                                    
                                                    
                                                   
                                                </tr>

                                               
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                        		</div>
                                
                            </div> <!-- end col -->

                            
                        </div>

                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer text-right">
                    © 2016. All rights reserved.
                </footer>

            </div>