window.onload = function () {
    // 아이디 중복 확인 결과
    let isIdValid = false;

    // 학번 중복 확인 결과
    let isStudentNoValid = false;

    const $registerBtn = document.getElementById("registerBtn");
    $registerBtn.setAttribute("disabled", "disabled");


    //아이디 중복 검사
    if (document.getElementById("memberIdDup")) {
        const $memberIdDup = document.getElementById("memberIdDup");
        const $idDupCheck1 = document.getElementById("id_dup_check_1");
        const $idDupCheck2 = document.getElementById("id_dup_check_2");

        $memberIdDup.onclick = function (event) {
            event.preventDefault();

            let memberId = document.getElementById("id").value.trim();

            fetch("/memberIdDup", {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json;charset-UTF-8'
                },
                body: JSON.stringify({id:memberId})
            })
                .then(result => result.json())
                .then(result => {
                    if (!result) {
                        $idDupCheck1.style.display = "inline"; // 아이디 사용 가능한 경우 보이기
                        $idDupCheck2.style.display = "none";    // 아이디 중복 메시지 숨기기
                        isIdValid = true;
                    } else {
                        $idDupCheck1.style.display = "none";    // 아이디 사용 가능 메시지 숨기기
                        $idDupCheck2.style.display = "inline";  // 아이디 중복인 경우 보이기
                    }

                    if (isIdValid && isStudentNoValid) {
                        $registerBtn.removeAttribute("disabled");
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }

    //학번 중복 검사
    if (document.getElementById("memberNoDup")) {
        const $memberNoDup = document.getElementById("memberNoDup");
        const $noDupCheck1 = document.getElementById("no_dup_check_1");
        const $noDupCheck2 = document.getElementById("no_dup_check_2");

        $memberNoDup.onclick = function (event) {
            event.preventDefault();

            let memberNo = document.getElementById("studentNo").value.trim();

            fetch("/memberNoDup", {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json;charset-UTF-8'
                },
                body: JSON.stringify({studentNo:memberNo})
            })
                .then(result => result.json())
                .then(result => {
                    if (!result) {
                        $noDupCheck1.style.display = "inline"; // 아이디 사용 가능한 경우 보이기
                        $noDupCheck2.style.display = "none";    // 아이디 중복 메시지 숨기기
                        isStudentNoValid = true;
                    } else {
                        $noDupCheck1.style.display = "none";    // 아이디 사용 가능 메시지 숨기기
                        $noDupCheck2.style.display = "inline";  // 아이디 중복인 경우 보이기
                    }

                    if (isIdValid && isStudentNoValid) {
                        $registerBtn.removeAttribute("disabled");
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }
}