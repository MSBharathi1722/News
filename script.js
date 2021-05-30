var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}
function _(x) { 
  return document.querySelector(x);
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function funct(name){ 
    
    let method = 'GET';
    let ayn = true; 
    let xhr = new XMLHttpRequest();
    var url;
    if(name == "in" || name == "us"){
      url = 'https://newsapi.org/v2/top-headlines?country='+name+'&apiKey=746a4b4d360f49b18c7fe147586e4e51';
    }else{
      url = 'https://newsapi.org/v2/everything?q='+name+'&language=en&apiKey=746a4b4d360f49b18c7fe147586e4e51';
    }
        
    var body = document.querySelector("#container");
    removeAllChildNodes(body);
        
    xhr.onreadystatechange = function(){
      if (this.readyState == 4 && this.status == 200){
          try{
            let response = JSON.parse(this.responseText);     
            _('#show').style.display = 'block';           
           
            for(let i = 6; i < 12; i++){
              var div = document.createElement("div");
              div.setAttribute("id","box");
              body.appendChild(div);
              var img = document.createElement("img");
              img.setAttribute("class","img");
              img.setAttribute("src",response.articles[i].urlToImage)
              var span = document.createElement("span");
              span.setAttribute("id","span");
              span.textContent = response.articles[i].title
              var breake = document.createElement("br");
              div.append(img);
              div.append(breake);
              div.append(span);
            }

              _('#title-1').innerHTML = response.articles[0].title;
              _('#title-2').innerHTML = response.articles[1].title;
              _('#title-3').innerHTML = response.articles[2].title;
              _('#title-4').innerHTML = response.articles[3].title;
        
              _('#img1').src = response.articles[0].urlToImage;
              _('#img2').src = response.articles[1].urlToImage;
              _('#img3').src = response.articles[2].urlToImage;
              _('#img4').src = response.articles[3].urlToImage;

        }
          catch (err){
          } 
        } 
    } 
    xhr.open(method, url, ayn);
    xhr.send(); 
}
funct('in');


