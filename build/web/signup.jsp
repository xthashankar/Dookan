<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Signup Form</title>
    </head>
    <link rel="stylesheet" type="text/css" href="aassets/css/signup.css" />
    <body> 
        <div class="wrapper">
            <div class="left">
                <h3>Dookan</h3>  
            </div>
            <div class="right">
                <div class="tabs">
                    <ul>
                        <li class="login_li"><b>Sign Up</b></li>
                    </ul>
                </div>
                <hr>

                <form  method="post" action="register" enctype="multipart/form-data">
                    <div class="login">
                        <div class="input_field">
                            <input type="text" placeholder="First Name" name="first_name" class="input1">
                            <input type="text" placeholder="Last Name" name="last_name" class="input2">
                        </div>
                        <div class="input_field">
                            <input type="text" placeholder="Email address" name="email" class="input">
                        </div>
                        <div class="input_field">
                            <input type="text" placeholder="UserName" name="username" class="input">
                        </div>
                        <div class="input_field">
                            <input type="password" placeholder="Password" name="password" class="input">
                        </div>
                        <div class="input_field">
                            <input type="text" placeholder="Contact Number" name="contact" class="input">
                        </div>
                        <div class="input_field">
                            <input type="text" placeholder="Address" name="address" class="input">
                        </div>
                        <div class="input_field">
                        <select name="role" >
                             <option value="admin" >Admin</option>
                             <option value="seller">Seller</option>
                             <option value="buyer">Buyer</option>
                        </select></div>
                        <div class="input_field">
                            <input type="file" id="photoUpload" name="photoUpload" class="input">
                        </div>
                        <!--      <div class="input_field">
                                <input type="file" placeholder="upload image" name="image" class="input">
                              </div>-->
                        <div > <button  class="btn">Sign up</button></div>
                        <div class="link"> <p>Already have account ?<a href="login.jsp">Login Here</a></p> </div>
                    </div>
                </form>
            </div>
    </body>
</html>