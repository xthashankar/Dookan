<%-- 
    Document   : adminproductdetail
    Created on : Nov 27, 2019, 6:02:46 PM
    Author     : shankar xtha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="adminheader.jsp"/>
  


                        

       


   

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
								<h4 class="page-title">Products</h4>
								<ol class="breadcrumb">
                               									<li>
										<a href="#">Dookan</a>
									</li>
									<li class="active">
										Products
									</li>
								</ol>
							</div>
						</div>

                       
                        <div class="row">
                            <div class="m-b-15">
                <!--pulling data from product table of dookan database>  -->                              
                <c:forEach var="product" items="${products}">
                                <div class="col-sm-6 col-lg-3 col-md-4 other">
                                    <div class="product-list-box thumb">
                                        <a href="javascript:void(0);" class="image-popup" title="Screenshot-1">
                                            <img src="${product.photo}" class="thumb-img" alt="work-thumbnail">
                                        </a>

                                        <div class="product-action">
                                            <a href="Aproductedit?pid=${product.id}" class="btn btn-success btn-sm"><i class="md md-mode-edit"></i></a>
                                            <a href="#" class="btn btn-danger btn-sm"><i class="md md-close"></i></a>
                                        </div>

                                        <div class="price-tag">
                                            ${product.price}
                                        </div>
                                        <div class="detail">
                                            <h4 class="m-t-0"><a href="" class="text-dark">${product.name}</a> </h4>
                                            <div class="rating">
                                                <ul class="list-inline">
                                                    <li><a class="fa fa-star" href=""></a></li>
                                                    <li><a class="fa fa-star" href=""></a></li>
                                                    <li><a class="fa fa-star" href=""></a></li>
                                                    <li><a class="fa fa-star" href=""></a></li>
                                                    <li><a class="fa fa-star-o" href=""></a></li>
                                                </ul>
                                            </div>
                                            <h5 class="m-0"> <span class="text-muted"> Stock :${product.quantity}</span></h5>
                                        </div>
                                    </div>
                                </div>

                                           </c:forEach>
                                            

                            </div>
                        </div> <!-- End row -->




                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer text-right">
                    Â© 2016. All rights reserved.
                </footer>

            </div>
            
            
            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


         

        </div>
        <!-- END wrapper -->


    
        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <script src="aassets/js/jquery.min.js"></script>
        <script src="aassets/js/bootstrap.min.js"></script>
        <script src="aassets/js/detect.js"></script>
        <script src="aassets/js/fastclick.js"></script>
        <script src="aassets/js/jquery.slimscroll.js"></script>
        <script src="aassets/js/jquery.blockUI.js"></script>
        <script src="aassets/js/waves.js"></script>
        <script src="aassets/js/wow.min.js"></script>
        <script src="aassets/js/jquery.nicescroll.js"></script>
        <script src="aassets/js/jquery.scrollTo.min.js"></script>

        <script src="aassets/js/jquery.core.js"></script>
        <script src="aassets/js/jquery.app.js"></script>

