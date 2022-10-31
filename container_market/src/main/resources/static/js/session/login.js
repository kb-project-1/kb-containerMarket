window.onload = () => {
    $.ajax({
        async: false,
        type: "get",
        url: "/api/session/getLogin",
        dataType: "json",
        success: (response) => {
            console.log(response.data);
            const hrefLogin = document.querySelector(".session-login");
            const hrefJoin = document.querySelector(".session-join");

            if(response.data == null){
                hrefLogin.innerHTML = `<a href="/member/login">LOGIN</a>`;
                hrefJoin.innerHTML = `<a href="/member/join">JOIN</a>`;
            }else{
                hrefLogin.innerHTML = `<a href="/logout">LOGOUT</a>`;
                if(response.data.role_id == 3){
                    hrefJoin.innerHTML = `<a href="/admin/index">ADMIN PAGE</a>`;
                }else {
                    hrefJoin.innerHTML = `<a href="/member/modify">MODIFY</a>`;
                }
            }
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });
}