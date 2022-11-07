const member_id = document.querySelector("#member_id");
const password = document.querySelector("#passwd");
const password_confirm = document.querySelector("#user_passwd_confirm");
const name = document.querySelector("#name");
const mobile1 = document.querySelector("#mobile1");
const mobile2 = document.querySelector("#mobile2");
const mobile3 = document.querySelector("#mobile3");
const email = document.querySelector("#email");
let responseData = null;
const btn_submit = document.querySelector("#submit");

function update() {
    let mobile = mobile1.value + mobile2.value + mobile3.value;

    let user = {
        username: member_id.value,
        password: password.value,
        name: name.value,
        phone: mobile,
        email: email.value,
        role: 1
    }

    $.ajax({
        async: false,
        type: "post",
        url: "/api/member/modify",
        contentType: "application/json",
        data: JSON.stringify(user),
        dataType: "json",
        success: (response) => {
            alert("회원정보 수정 완료");
            location.replace("/logout");
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });
}

function initInputs() {
    member_id.disabled = true;
    member_id.value = responseData.data.username;
    name.disalbed = true;
    name.value = responseData.data.name;
    console.log(responseData.data);
    mobile1.value = responseData.data.phone.slice(0,3);
    if(responseData.data.phone.length < 11){
        mobile2.value = responseData.data.phone.slice(3,6);
        mobile3.value = responseData.data.phone.slice(6,10);
    }else{
        mobile2.value = responseData.data.phone.slice(3,7);
        mobile3.value = responseData.data.phone.slice(7,11);
    }
    email.value = responseData.data.email;

    btn_submit.addEventListener("click", function(){
        if(password.value != password_confirm.value) {
            alert("Passwords do not match");
            return;
        }else{
            update();
        }
    });
}

window.onload = () => {
    $.ajax({
        async: false,
        type: "get",
        url: "/api/session/getLogin",
        dataType: "json",
        success: (response) => {
            if(response.data == null){
                location.replace("/member/login");
            }else{
                responseData = response;
            }
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });

    initInputs();
}