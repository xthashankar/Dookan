
<c:forEach var="product" items="${product}">
            <h2>${product.id}</h2>
            <h2> ${product.price}</h2>
            <h2>  ${product.name}</h2>

        </c:forEach>

<!DOCTYPE html>
<html>
<head>
	<title>Dookan</title>
	<link rel="stylesheet" href="aassets/css/index.css">
</head>
<body>
	<div class="navigation">
		<div class="logo">
                    <img  class="logo-img" src="aassets/images/main-page/logo.png" alt="logo image">
		</div>
                <nav>               
		<div class="navi">
                    <ul>
			
			<li> <a href="login.jsp">Login</a></li>
			<li>  <a href="signup.jsp"><button>Sign up</button></a></li>
                    </ul>
                </div>
		</nav>
	</div>
     


	<div id="banner">
<!-- this is for the main hero image --> 

<img  class="banner-img" src="aassets/images/online.png" > 
<!--<div class="wrap">
    <div class="search">
        <input type="text" class="searchTerm" placeholder="What are you looking for?">
         <button type="submit" class="searchButton">Search </button>
     </button>
    </div>
</div>-->
   



	</div>
 
           
        
	<div class="gridContainer">
		
		<div class="gridElement grid2">
                   
			<div class="productHolder">
				<div class="productImage">
					<img src="aassets/images/main-page/achar1.png" alt="">
				</div>
				<div class="productName">
					<h4>${product.name}</h4>
				</div>
				<div class="productPrice">
					<h3>Rs ${product.price}</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div> 

			<div class="productHolder">
				<div class="productImage">
					<img src="aassets/images/main-page/software.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Mark Sheet Software</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 5000</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder">
				<div class="productImage">
					<img src="aassets/images/main-page/paint.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Wall Painting</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 700</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

		</div>



		




		<div class="gridElement grid4">
			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/diyo.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Pital Diyo</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 150</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/note.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Science XII note copy</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 500</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/achar2.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Mula ko Achar</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 200</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/till-ko-laddu.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Till ko laddu</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 200</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/homeProject.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Water alarm system (school project)</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 600</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/dhup.jpeg" alt="">
				</div>
				<div class="productName">
					<h4>Dhup Batii</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 2/unit</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/Sukul.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Sukul/Gundri</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 300</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/ghee.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Home made Ghewo</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 350</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/dreamCatcher.png" alt="">
				</div>
				<div class="productName">
					<h4>Dream Catcher</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 200</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/cucumber.jpg" alt="">
				</div>
				<div class="productName">
					<h4>cucumber</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 70/kg</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/tama-ko-bhada.jpg" alt="">
				</div>
				<div class="productName">
					<h4>Tama ko bhada</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 500</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>

			<div class="productHolder belowSection">
				<div class="productImage">
					<img src="aassets/images/main-page/sweater.jpeg" alt="">
				</div>
				<div class="productName">
					<h4>Home made sweater</h4>
				</div>
				<div class="productPrice">
					<h3>Rs 300</h2>
				</div>
				<div class="productRating">
					<img src="aassets/images/main-page/star.png" alt="">
				</div>
			</div>
			</div>
		</div>


		</div>
	</div>

</body>
</html>
