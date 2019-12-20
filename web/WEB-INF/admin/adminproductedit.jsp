<%-- 
    Document   : adminproductedit
    Created on : Nov 27, 2019, 9:31:04 PM
    Author     : shankar xtha
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="adminheader.jsp"/>
  



                      
            
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container">

                        <!-- Page-Title -->
                        <div class="row">
                            <div class="col-sm-12">
                               
                                <h4 class="page-title"> Product</h4>
                                <ol class="breadcrumb">
                                    <li>Home</li>
                                    <li>Add Product</li>
                                    
                                </ol>
                            </div>
                        </div>
                        <c:set var="product" value="${product}"/>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    <h4 class="m-t-0 header-title"><b>Add Product</b></h4><br>                             
                                    <div class="row">
                                        <div class="col-md-6">
                                            <form class="form-horizontal" action="addproduct" method="POST" enctype="multipart/form-data"  >     
                                                 
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Name </label>
                                                    <div class="col-md-10">
                                                        <input type="text" name="name" class="form-control" placeholder="Product Name" value="${product.name}">
                                                        
                                                       
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Price </label>
                                                    <div class="col-md-10">
                                                        <input type="text" name="price" class="form-control" placeholder="Price" value="${product.price}">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="example-email">offer</label>
                                                    <div class="col-md-10">
                                                        <input type="text"  name="offer" class="form-control" placeholder="offer" value="${product.offer}">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Description</label>
                                                    <div class="col-md-10">
                                                        <input type="text" name="description" class="form-control" placeholder="Description" value="description">
                                                    </div>
                                                </div>
                                                  <div class="form-group">
                                                    <label class="col-md-2 control-label">category</label>
                                                    <div class="col-md-10">
                                                        <select class="form-control" name="category">
                                                            <option value="${products.category}" > Electronic</option>
                                                            <option  value="${products.category}"> clothes</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                                         
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Quantity</label>
                                                    <div class="col-md-10">
                                                        <input type="text" name="quantity" class="form-control" placeholder="Quantity" value="quantity">
                                                    </div>
                                                </div> 

                                             
                                              <div class="form-group">
                                                    <label class="col-md-2 control-label">Display Image</label>
                                                    <div class="col-md-10">
                                                        <img class="img-thumbnail" src="uploads/image.jpg" alt="img" width="100">
                                                    </div>
                                                </div>  
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Image</label>
                                                    <div class="col-md-10">
                                                        <input type="file" accept="uploads/*" name="file" id="file" class="filestyle" onchange="previewFile()" />
                                                     <!--  <input class="form-control" name="file" type="file" onchange="previewFile()" > -->
                                                    </div>
                                                </div> 


                                             

                                               
                                                <div class="form-group">
                                                    <div class="col-sm-offset-3 col-sm-9 m-t-15">
                                                        <button type="submit" class="btn btn-primary" name="savebtn" >
                                                            Submit
                                                        </button>
                                                        <button type="reset" class="btn btn-default m-l-5">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </div>                    
                              
                                            </form> 
                                        </div>                                  
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                      
                        
                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer">
                    © 2016. All rights reserved.
                </footer>

            </div>
            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->

        </div>
        <!-- END wrapper -->


        <script type="text/javascript">
        var resizefunc = [];
        function previewFile()
        {
            var preview = document.querySelector("img.img-thumbnail");

            var file = document.querySelector('input[type=file]').files[0];

            var reader = new FileReader();

            reader.addEventListener("load", function () {

            preview.src = reader.result;

            }, false);

            if (file)

            {

            reader.readAsDataURL(file);

            }
        }
        </script>

        <!-- jQuery  -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/jquery.nicescroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>


        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/jquery.nicescroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/jquery.nicescroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>

        <script src="assets/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="assets/plugins/switchery/js/switchery.min.js"></script>
        <script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script>
        <script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script>
        <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-touchspin/js/jquery.bootstrap-touchspin.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>

        <script type="text/javascript" src="assets/plugins/autocomplete/jquery.mockjax.js"></script>
        <script type="text/javascript" src="assets/plugins/autocomplete/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="assets/plugins/autocomplete/countries.js"></script>
        <script type="text/javascript" src="assets/pages/autocomplete.js"></script>

        <script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>



        <script src="aassets/js/jquery.core.js"></script>
        <script src="aassets/js/jquery.app.js"></script>
    
  