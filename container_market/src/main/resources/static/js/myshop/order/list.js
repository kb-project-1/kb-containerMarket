const testbtn = document.querySelector(".testbtn");

testbtn.onclick = () => {
    alert("불러오기");

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
        center.innerHTML += `
            <tr>
                <td>${data.order_date}</td>
                <td>${data.image_src}</td>
                <td>${data.name}</td>
                <td>${data.count}</td>
                <td>${data.price}</td>
                <td>${data.status}</td>
                <td>${data.status2}</td>
            </tr>
        `;
    });
}
