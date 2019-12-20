<%@taglib  prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>product page</title>
	<link rel="stylesheet" type="text/css" href="aassets/css/product.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
    
      <div class="container">
      	
      	<!-- image container -->
      	<div class="left-container">
      	  <div class="prd-img">
      		<img  class="product-img" src="images/sukul.jpg" style="width:80%;">
      	   </div>
      	   <div  class="profile">
	      		<img class="img-profile" src="images/sukul1.jpeg">
	      		<img class="img-profile" src="images/sukul2.jpg">
	      		<img class="img-profile" src="images/sukul3.jpg">
      		</div>
      	</div>


          <!-- content container -->
      	<div class="right-container">
	      		<div class="con-title">
	      			<h1>Sukul</h1>
	      		</div>
	      		<div class="content-description">
	      			<h3>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
	      			tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	      			quis nostrud exercitation ullamco labori</h3>
	      		</div>
	      		<div class="rating">
	      			<h3>Star Rating</h3>
	      		</div>
	      		<div  class="user-detail">
	      			<img  class="user-img" src="images/user1.png">
	      		        
	      		        <div class="user-box">
	      		         <button>Top Seller</button>
	      		        <h3>Shankar Shrestha</h3>
	      				<h3>Newroad,Kathmandu</h3> 
	      				</div>
	      	    </div>
	      		<div class="pricing"><!-- pricing and qt.t -->
	      			<h2>Rs 150</h2>
	      			<h1>Quantity</h1>
					<form id='myform' method='POST' action='#'>
						    <input type='button' value='-' class='qtyminus' field='quantity' />
						    <input type='text' name='quantity' value='0' class='qty' />
						    <input type='button' value='+' class='qtyplus' field='quantity' />
					</form>
	      		</div>
	      		<div class="button">
	      			<button class="btn-cart"><h3>Add to cart</h3></button>
	      			<button class="btn-buy"><h3>Buy Now</h3></button>
	      		</div>
	      	</div>
        </div>
</body>
</html>