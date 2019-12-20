
<%@page session="false" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
	<title>Signup Form</title>   
</head>
 <link rel="stylesheet" type="text/css" href="aassets/css/login.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<body>
<form action="dashboard" method="post">
	 <div class="wrapper" style="height:auto;">
    <div class="left">
      <h3>Dookan</h3>
    </div>

   <div class="right" >
      <div class="tabs">
        <ul>
          <li class="login_li" ><b>login</b></li>
        </ul>
    </div>
    <hr>
    
    <c:set var="emsg" scope="page" value="${errorMsg}"></c:set>
    <c:if test ="${emsg!=null}"><br>
        <span class="btn1 btn-danger" style="color:red; margin-left:120px;">${errorMsg}</span>
    </c:if>

    <div class="login" >
          <div class="input_field" >
            <input type="text" name="email" placeholder="Email" class="input">
          </div>

          <div class="input_field">
            <input type="password" placeholder="Password" name="password" class="input">
          </div>
          
        <div > <button  class="btn">Login</button></a></div>
        <div class="link"> <p>New Here?<a href="signup.jsp"> Sign up</a> here</p>
        </div>
    </div>
</div>
</div>
</form>
</body>
</html>
