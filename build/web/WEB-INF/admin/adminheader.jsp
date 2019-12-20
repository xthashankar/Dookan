<%-- 
    Document   : adminheader
    Created on : Nov 25, 2019, 6:08:57 PM
    Author     : shankar xtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">

        <link rel="shortcut icon" href="aassets/images/favicon_1.ico">

        <title>Ubold - Responsive Admin Dashboard Template</title>

        <!--Morris Chart CSS -->
        <link rel="stylesheet" href="aassets/plugins/morris/morris.css">

        <link href="aassets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="aassets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="aassets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="aassets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="aassets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="aassets/css/responsive.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script src="aassets/js/modernizr.min.js"></script>


    </head>


    <body class="fixed-left">
        <c:set var="role" value="${sessionScope.loggedInUser.role}" scope="page"></c:set>
            <!-- Begin page -->
            <div id="wrapper">

                <!-- Top Bar Start -->
                <div class="topbar">

                    <!-- LOGO -->
                    <div class="topbar-left">
                        <div class="text-center">
                            <a href="dashboard" class="logo"><i class="icon-magnet icon-c-logo"></i><span>Ub<i class="md md-album"></i>ld</span></a>
                            <!-- Image Logo here -->
                            <!--<a href="index.html" class="logo">-->
                            <!--<i class="icon-c-logo"> <img src="assets/images/logo_sm.png" height="42"/> </i>-->
                            <!--<span><img src="assets/images/logo_light.png" height="20"/></span>-->
                            <!--</a>-->
                        </div>
                    </div>

                    <!-- Button mobile view to collapse sidebar menu -->
                    <div class="navbar navbar-default" role="navigation">
                        <div class="container">
                            <div class="">
                                <div class="pull-left">
                                    <button class="button-menu-mobile open-left waves-effect waves-light">
                                        <i class="md md-menu"></i>
                                    </button>
                                    <span class="clearfix"></span>
                                </div>

                                <ul class="nav navbar-nav hidden-xs">
                                    <li><a href="#" class="waves-effect waves-light">Files</a></li>
                                        <li class="dropdown">
                                        <a href="#" class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown"
                                           role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span
                                                class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Action</a></li>
                                            <li><a href="#">Another action</a></li>
                                            <li><a href="#">Something else here</a></li>
                                            <li><a href="#">Separated link</a></li>
                                        </ul>
                                    </li>
                                <c:if test="${role=='admin'}">
                                        <a href="Userdetail" class="waves-effect"><i class="ti-menu-alt"></i><span>UserDetail</span> </span></a>
                                </c:if>
                            </ul>

                            <form role="search" class="navbar-left app-search pull-left hidden-xs">
                                <input type="text" placeholder="Search..." class="form-control">
                                <a href=""><i class="fa fa-search"></i></a>
                            </form>


                            <ul class="nav navbar-nav navbar-right pull-right">
                                <li class="dropdown top-menu-item-xs">
                                    <a href="#" data-target="#" class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" aria-expanded="true">
                                        <i class="icon-bell"></i> <span class="badge badge-xs badge-danger">3</span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-lg">
                                        <li class="notifi-title"><span class="label label-default pull-right">New 3</span>Notification</li>
                                        <li class="list-group slimscroll-noti notification-list">
                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-diamond noti-primary"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">A new order has been placed A new order has been placed</h5>
                                                        <p class="m-0">
                                                            <small>There are new settings available</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>

                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-cog noti-warning"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New settings</h5>
                                                        <p class="m-0">
                                                            <small>There are new settings available</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>

                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-bell-o noti-custom"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">Updates</h5>
                                                        <p class="m-0">
                                                            <small>There are <span class="text-primary font-600">2</span> new updates available</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>

                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-user-plus noti-pink"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New user registered</h5>
                                                        <p class="m-0">
                                                            <small>You have 10 unread messages</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>

                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-diamond noti-primary"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">A new order has been placed A new order has been placed</h5>
                                                        <p class="m-0">
                                                            <small>There are new settings available</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>

                                            <!-- list item-->
                                            <a href="javascript:void(0);" class="list-group-item">
                                                <div class="media">
                                                    <div class="pull-left p-r-10">
                                                        <em class="fa fa-cog noti-warning"></em>
                                                    </div>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New settings</h5>
                                                        <p class="m-0">
                                                            <small>There are new settings available</small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0);" class="list-group-item text-right">
                                                <small class="font-600">See all notifications</small>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="hidden-xs">
                                    <a href="#" id="btn-fullscreen" class="waves-effect waves-light"><i class="icon-size-fullscreen"></i></a>
                                </li>
                                <li class="hidden-xs">
                                    <a href="#" class="right-bar-toggle waves-effect waves-light"><i class="icon-settings"></i></a>
                                </li>
                                <li class="dropdown top-menu-item-xs">
                                    <a href="" class="dropdown-toggle profile waves-effect waves-light" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle">${sessionScope.loggedInUser.username}</a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)"><i class="ti-user m-r-10 text-custom"></i> Profile</a></li>
                                        <li><a href="javascript:void(0)"><i class="ti-settings m-r-10 text-custom"></i> Settings</a></li>
                                        <li><a href="javascript:void(0)"><i class="ti-lock m-r-10 text-custom"></i> Lock screen</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)"><i class="ti-power-off m-r-10 text-danger"></i> Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <!-- Top Bar End -->


            <!-- ========== Left Sidebar Start ========== -->

            <div class="left side-menu">
                <div class="sidebar-inner slimscrollleft">
                    <!--- Divider -->
                    <div id="sidebar-menu">
                        <ul>

                            <li class="text-muted menu-title">Navigation</li>

                            <li class="has_sub">
                                <a href="admindashboard" class="waves-effect" ><i class="ti-shopping-cart"></i> <span> Dashboard </span></a>

                            </li>

                            <li class="has_sub">
                                <a href="Aproduct" class="waves-effect"><i class="ti-paint-bucket"></i> <span> Products </span> </a>
                            </li>
                            <li class="has_sub">
                                <a href="Aproductdetail" class="waves-effect"><i class="ti-light-bulb"></i><span>Product Detail </span> </a>
                            </li>

<!--                            <li class="has_sub">
                                <a href="Aproductedit" class="waves-effect"><i class="ti-spray"></i> <span> Product Edit</span>  </a>
                            </li>-->

                            <li class="has_sub">
                                <a href="Aorder" class="waves-effect"><i class="ti-pencil-alt"></i><span> Orders</span></a>
                            </li>

                            <li class="has_sub">
                                <c:if test="${role=='admin'}">
                                        <a href="Userdetail" class="waves-effect"><i class="ti-menu-alt"></i><span>UserDetail</span> </span></a>
                                </c:if>

                            </li>



                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <!-- Left Sidebar End -->




        </div>
        <!-- END wrapper -->





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

        <script src="aassets/plugins/peity/jquery.peity.min.js"></script>

        <!-- jQuery  -->
        <script src="aassets/plugins/waypoints/lib/jquery.waypoints.js"></script>
        <script src="aassets/plugins/counterup/jquery.counterup.min.js"></script>



        <script src="assets/plugins/morris/morris.min.js"></script>
        <script src="assets/plugins/raphael/raphael-min.js"></script>

        <script src="assets/plugins/jquery-knob/jquery.knob.js"></script>

        <script src="assets/pages/jquery.dashboard.js"></script>

        <script src="assets/js/jquery.core.js"></script>
        <script src="assets/js/jquery.app.js"></script>

        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $('.counter').counterUp({
                    delay: 100,
                    time: 1200
                });


                $(".knob").knob();


            });
        </script>


    </body>
</html>