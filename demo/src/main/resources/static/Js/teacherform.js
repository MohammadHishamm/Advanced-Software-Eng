function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $(".image-upload-wrap").hide();

      $(".file-upload-image").attr("src", e.target.result);
      $(".file-upload-content").show();

      $(".image-title").html(input.files[0].name);
    };

    reader.readAsDataURL(input.files[0]);
  } else {
    removeUpload();
  }
}

function removeUpload() {
  $(".file-upload-input").replaceWith($(".file-upload-input").clone());
  $(".file-upload-content").hide();
  $(".image-upload-wrap").show();
}
$(".image-upload-wrap").bind("dragover", function () {
  $(".image-upload-wrap").addClass("image-dropping");
});
$(".image-upload-wrap").bind("dragleave", function () {
  $(".image-upload-wrap").removeClass("image-dropping");
});

const myForm = document.getElementById("email-list");

function addEmailField() {
  // Create elements
  const nef_wrapper = document.createElement("div");
  const nef = document.createElement("input");
  const btnAdd = document.createElement("button");
  const btnDel = document.createElement("button");

  // Add Class to main wrapper
  nef_wrapper.classList.add("email-input__w");

  // set button ADD
  btnAdd.type = "button";
  btnAdd.classList.add("btn-add-input");
  btnAdd.innerText = "+";
  btnAdd.setAttribute("onclick", "addEmailField()");

  // set button DEL
  btnDel.type = "button";
  btnDel.classList.add("btn-del-input");
  btnDel.innerText = "-";

  // set Input field
  nef.type = "text";
  nef.name = "field";
  nef.setAttribute("required", "");
  nef.classList.add("input-field");

  //append elements to main wrapper
  nef_wrapper.appendChild(nef);
  nef_wrapper.appendChild(btnAdd);
  nef_wrapper.appendChild(btnDel);

  // append element to DOM
  myForm.appendChild(nef_wrapper);
  btnDel.addEventListener("click", removeEmailField);
}

//remove element from DOM
function removeEmailField(el) {
  const field = el.target.parentElement;
  field.remove();
}

var inputs = document.getElementsByTagName("input");

for (var i = 0; i < inputs.length; i++) {
  if (inputs[i].type == "checkbox") {
    inputs[i].checked = false;
  }
}
