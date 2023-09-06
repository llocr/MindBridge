window.onload = function () {

    if (document.getElementById("join")) {
        const $join = document.getElementById("join");
        $join.onclick = function() {
            location.href = "/client/join";
        }
    }
}