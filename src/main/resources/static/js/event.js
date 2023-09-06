window.onload = function () {

    if (document.getElementById("join")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function() {
            location.href = "/client/join";
        }
    }



}