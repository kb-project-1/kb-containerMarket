const joinButton = document.querySelector(".btnSubmit");
    joinButton.onclick = () => {

        const joinInputs = document.querySelectorAll(".member-input");
        let member = {
            username: joinInputs[0].value,
            password: joinInputs[1].value,
            passwordConfirm: joinInputs[2].value,
            name: joinInputs[3].value,
            phone : joinInputs[4].value+joinInputs[5].value+joinInputs[6].value,
            email: joinInputs[7].value
        }

        console.log(member);
        if(member.password!=member.passwordConfirm){
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }
        let ajaxOption = {
                async: false,                       //필수
                type: "post",                       //필수
                url: "/api/member/join",       //필수
                contentType: "application/json",    //전송할 데이터가 json인 경우
                data: JSON.stringify(member),         //전송할 데이터가 있으면
                dataType: "json",                   //json 외 text 등을 사용할 수 있지만 json 사용함
                success: (response, textStatus, request) => {            //성공시에 실행될 메소드
                    console.log(response);
                    console.log(textStatus);
                    console.log(request);
                    const successURI = request.getResponseHeader("Location");
                    location.replace(successURI + "?username=" +response.data);
                },
                error: (error) => {                 //실패시에 실행될 메소드
                    console.log(error.responseJSON);
                    alertErrorMessage(error.responseJSON.data);
                }
          }
         $.ajax(ajaxOption);
    }
function alertErrorMessage(errors) {

    const errorArray = Object.values(errors);

    errorArray.forEach(error => {
        alert(error);
    });
}