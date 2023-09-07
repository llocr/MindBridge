window.onload = function () {
    if (document.getElementById("majorDup")) {
        const $majorDup = document.getElementById("majorDup");
        const $majorDupCheck1 = document.getElementById("major_dup_check_1");
        const $majorDupCheck2 = document.getElementById("major_dup_check_2");
        const $registerBtn = document.getElementById("registerBtn");

        $majorDup.onclick = function (event) {
            event.preventDefault();

            let majorName = document.getElementById("name").value.trim();

            fetch("/major/nameDup", {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json;charset-UTF-8'
                },
                body: JSON.stringify({name:majorName})
            })
                .then(result => result.json())
                .then(result => {
                    if (!result) {
                        $majorDupCheck1.style.display = "inline";
                        $majorDupCheck2.style.display = "none";
                        $registerBtn.removeAttribute("disabled");
                    } else {
                        $majorDupCheck1.style.display = "none";
                        $majorDupCheck2.style.display = "inline";
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }
}