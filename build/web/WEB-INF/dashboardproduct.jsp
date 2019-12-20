

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>product page</title>
	<link rel="stylesheet" type="text/css" href="aassets/css/product.css">
        <link rel="stylesheet" href="aassets/css/index.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
    
      <div class="container">
      	
      	<!-- image container -->
      	<div class="left-container">
      	  <div class="prd-img">
      		<img  class="product-img" src="${product.photo}" style="width:80%;">
      	   </div>
      	   <div  class="profile">
	      		<img class="img-profile" src="${product.photo}">
	      		<img class="img-profile" src="${product.photo}">
	      		<img class="img-profile" src="${product.photo}">
      		</div>
      	</div>


          <!-- content container -->
      	<div class="right-container">
	      		<div class="con-title">
	      			<h1>${product.name}</h1>
	      		</div>
	      		<div class="content-description">
	      			<h3>${product.description}</h3>
	      		</div>
	      		<div class="productRating">
	      			<h3>Star Rating</h3>
                                <img src="aassets/images/main-page/star.png" alt="">
	      		</div>
	      		<div  class="user-detail">
	      			<img  class="user-img" src="aassets/images/user1.png">
	      		        
	      		        <div class="user-box">
	      		         <button>Top Seller</button>
	      		        <h3>Shankar Shrestha</h3>
	      				<h3>Newroad,Kathmandu</h3> 
	      				</div>
	      	    </div>
	      		<div class="pricing"><!-- pricing and qt.t -->
	      			<h2>Rs 150</h2>
	      			<h1>Quantity</h1>
				 <input type='text' name='quantity' value=' ${product.quantity}' class='qty' />
                                                   
					
	      		</div>
	      		<div class="button">
                            <button class="btn-cart"><a href="cart"><h3>Add to cart</h3></a></button>
	      			
	      		</div>
	      	</div>
        </div>
</body>
</html>
         
