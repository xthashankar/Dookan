<%-- 
    Document   : cart
    Created on : Nov 30, 2019, 11:01:52 PM
    Author     : shankar xtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>cart page</title>
    <link rel="stylesheet" href="aassets/css/cart.css">
     <link rel="stylesheet" href="aassets/css/index.css">
  </head>
  <body>
      
      
     

<div class="container-wrapper">

  <div class="cart-wrapper">
       
    <!-- product 01 -->
    <div class="product-wrapper">
            
    
<c:forEach var="carts" items="${carts}">
   
      <div class="product-container">
        <div class="product-image">
          <img src="aassets/images/ghee.jpg" alt="cart-image">
        </div>
        <div class="product-detail">
            <h3> ${carts.addedtocartitem.productid.name}</h3>
          <p>${carts.addedtocartitem.productid.description}</p>
          
        </div>
      </div>
      <div class="product-response">
        <div class="product-quantity">
          <div class="left-btn btn">-</div>
          <input type="text" name="quantity" id="quantity" class="quantity" value="1">
          <div class="right-btn btn">+</div>
        </div>
        <div class="price">
          <span> ${carts.addedtocartitem.productid.price}</span>
        </div>
      </div>
      </c:forEach>
    </div>
      
        
   

  <!-- right side checkout part -->
  <div class="checkout-wrapper">
    <div class="checkout-header">
      Total
    </div>
<div class="order-detail">
  <p>Total Quanity: <span>2</span></p>
  <p>Total Price: <span> Rs 200</span></p>
</div>
<div class="checkout-btn-wrapper">
  <button type="button" name="button">Checkout></button>
</div>
  </div>
</div>

  </body>
</html>
