
window.onload = () => {

  const anchors = document.querySelectorAll('.link-anchor');
  const transition =  document.querySelector(".transition");
 
setTimeout(function () {
    transition.classList.remove('is-active');
    document.body.style.overflow = "auto";
}, 2000);

for (let i = 0; i < anchors.length; i++) {
  const anchor = anchors[i];

  anchor.addEventListener('click', e => {
    e.preventDefault();
    let target = e.target.href;

    console.log(target);

    transition.classList.add('is-active');

    setInterval(() => {
      window.location.href = target;
    }, 500);
  })
  
}

}



// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
  scrollFunction();
};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("btn-back-to-top").style.display = "block";
    document.getElementById("btn-back-to-top").style.transform =
      "translate(0px,-10px)";
  } else {
    document.getElementById("btn-back-to-top").style.transform =
      "translate(0px,60px)";
  }
}

function backToTop() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}

document.getElementById("addtocart").addEventListener("click", function () {
  var Course_ID = document.getElementById("addtocart").value;
  var scope = "add";

  function showHint(Course_ID, scope) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
      }
    };
    xmlhttp.open(
      "GET",
      "wishlist.php?Course_ID=" + Course_ID + "&scope=" + scope,
      true
    );
    xmlhttp.send();
  }
  showHint(Course_ID, scope);
});

