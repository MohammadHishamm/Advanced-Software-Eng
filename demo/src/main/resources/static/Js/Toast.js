
function showToast() {

    x = document.getElementById("Audio");

    x.play();

    document.getElementById('toast').classList.add('show');

    setTimeout(function() {
        document.getElementById('toast').classList.remove('show');
    }, 5000);

}
