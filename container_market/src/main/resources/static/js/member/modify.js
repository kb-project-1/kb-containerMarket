const member_id = document.querySelector("#member_id");
const name = document.querySelector("#name");
const mobile1 = document.querySelector("#mobile1");
const mobile2 = document.querySelector("#mobile2");
const mobile3 = document.querySelector("#mobile3");
const email = document.querySelector("#email");
let responseData = null;

function initInputs() {
    member_id.disabled = true;
    member_id.value = responseData.data.username;
    name.disalbed = true;
    name.value = responseData.data.name;
    // console.log(responseData.data.phone.slice(0,3));
    mobile1.value = responseData.data.phone.slice(0,3);
    if(responseData.data.phone.length < 11){
        mobile2.value = responseData.data.phone.slice(3,6);
        mobile3.value = responseData.data.phone.slice(6,10);
    }else{
        mobile2.value = responseData.data.phone.slice(3,7);
        mobile3.value = responseData.data.phone.slice(7,11);
    }
    email.value = responseData.data.email;

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