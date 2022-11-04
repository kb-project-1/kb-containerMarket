//const testbtn = document.querySelector(".testbtn");


window.addEventListener('onload',init());


function init() {
    $.ajax({
        async: false,
        type: "get",
        url: "/api/myshop/order/getList",
        dataType: "json",
        success: (response) => {
            console.log(response);
            loadList(response.data);
        },
        error: (error) => {
            console.log(error);
        }
    });
}

function loadList(list) {
    if(list.length <= 0 || list == null){
        return;
    }

    const center = document.querySelector(".center");
    center.classList.remove("displaynone");

    center.innerHTML = "";

    list.forEach(data => {
        let pDate = data.order_date.replace("T"," ");
        let pPrice = data.price.toLocaleString('en-US');

        center.innerHTML += `
            <tr>
                <td>${pDate}</td>
                <td>${data.image_src}</td>
                <td>${data.name}</td>
                <td>${data.count}</td>
                <td>${pPrice}</td>
                <td>${data.status}</td>
                <td>불가능</td>
            </tr>
        `;
    });
}
