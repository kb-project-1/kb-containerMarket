window.addEventListener('load', init());

function init() {
    $.ajax({
        async: false,
        type: 'get',
        url: "/api/product/cart",
        dataType: "json",
        success: (response) => {
            console.log(response);
            loadCarts(response);
        },
        error: (error) => {
            console.log(error);
        }
    })
}

function loadCarts(response) {
    const tbody = document.querySelector(".cart_tbody");

    tbody.innerHTML = '';
    response.data.forEach(item => {
        console.log(item);
        let totalPrice = item.price * item.amount;

        tbody.innerHTML += `
            <tr class="cart__list__detail">
                <td><input type="checkbox"></td>
                <td><img src="/static/upload/product/${item.img}" style="width: 100px;" alt="후드티"></td>
                <td>
                <p>${item.name}</p>
                  <p>옵션 : 그레이 / L </p>
                  <span class="price">${item.price}</span>
                </td>
                <td class="cart__list__option">
                  <p>${item.amount}</p>
                </td>
                <td>
                    <span class="price">${totalPrice}</span><br>
                </td>
                <td>무료</td>
              </tr>
        `;


    });
}