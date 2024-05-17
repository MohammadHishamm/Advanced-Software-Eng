function showToast() {
  x = document.getElementById("Audio");

  x.play();

  document.getElementById("toast").classList.add("show");

  setTimeout(function () {
    document.getElementById("toast").classList.remove("show");
  }, 5000);
}

function showToast1() {
  var audio = document.getElementById("Audio1");
  audio.play();

  var toast = document.getElementById("toast1");
  toast.classList.add("show");

  setTimeout(function () {
    toast.classList.remove("show");
  }, 5000);
}
