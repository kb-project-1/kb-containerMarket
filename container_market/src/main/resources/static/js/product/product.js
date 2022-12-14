class ProductApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ProductApi();
        }
        return this.#instance;
    }
    getProductData() {
        let responseData = null;
        const url = location.href;
        const pdtId = url.substring(url.lastIndexOf("/") + 1);

        $.ajax({
            async: false,
            type: "get",
            url: "/api/product/" + pdtId,
            dataType: "json",
            success: response => {
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });
        console.log(responseData)
        return responseData;
    }
}

class ProductDetail{
    constructor(){
        const responseData = ProductApi.getInstance().getProductData();
        this.loadProductDetail(responseData);
        this.loadProductColors(responseData);
        this.loadProductSizes(responseData);
        this.loadProductImages(responseData);
    }

    loadProductDetail(responseData){
        document.querySelector(".p-name").textContent = responseData.pdtName;
        document.querySelector(".simple-info").textContent = responseData.simpleInfo;
        document.querySelector(".p-price").textContent = responseData.pdtPrice;
        document.querySelector(".content-img").textContent = responseData.detailInfo;
        document.querySelector(".size-box").textContent = responseData.pdtSize;
    }

    loadProductColors(responseData){
        const productColors = document.querySelector(".color-box");
        productColors.innerHTML = ``;

        Object.keys(responseData.pdtColors).forEach(color => {
            productColors.innerHTML += `<option value="${color}">${color}</option>`
        })
    }

//     loadProductPrice(responseData){
//         const productPrice = document.querySelector(".p-price")
//         Object.entries(responseData.pdtColors).foreach(entry => {
//             if(productColors.value == entry[0]){
//                 entry[1].forEach(value => {
//                     productsizes.innerHTML =+ `
//                     <td class="p-price">${pdtStock}</td>
//                     `;
//                 })
//
//             }
//         })
//     }
     loadProductImages(responseData){
        const img = document.querySelector(".img");
        const contentImg = document.querySelector(".content-img");
        img.innerHTML = "";
        contentImg.innerHTML = "";
        img.innerHTML = `<img src="/static/upload/product/${responseData.pdtImg}">`;
        contentImg.innerHTML = `<img src="/static/upload/product/${responseData.pdtImg}">`;
        }
    // ?????? , ?????? ????????????

    loadProductSizes(responseData){
        const productColors = document.querySelector(".color-box");
        const productSizes = document.querySelector(".size-box");
        productSizes.innerHTML = "";
        Object.entries(responseData.pdtColors).forEach(entry => {
            if(productColors.value == entry[0]) {
                entry[1].forEach(value => {
                    productSizes.innerHTML += `
                            <option value="S">${value.sizeName}</option>
                    `;
                })

            }
        });


//         Object.entries(responseData.pdtColors).foreach(entry => {
//             if(productColors.value == entry[0]){
//                 entry[1].forEach(value => {
//                     productSizes.innerHTML =+ `
//                     <option type="hidden" id="pdtDtlId" value="${value.pdtDtlId}">
//                     <option id="product-size-${value.sizeName}"selected>${value.sizeName}</option>
//                     `;
//                 })
//
//             }
//         });
    }
}

function addButtonEvent() {
    const button_cart = document.querySelector(".btn-cart");
    const button_buy = document.querySelector(".btn-b");

    button_cart.onclick = () => {
        const url = location.href;
        const pdtId = url.substring(url.lastIndexOf("/") + 1);
        $.ajax({
            async: false,
            type: "post",
            url: "/api/product/addcart/"+pdtId,
            success: (response) => {
                console.log(response);
                window.alert("????????? ?????????????????????.");
            },
            error: (error) => {
                console.log(error);
                window.alert("?????? ????????? ?????????????????????.");
            }
        });
    }

    button_buy.onclick = () => {
        init_buyButton();
    }

}

function init_buyButton() {
    $.ajax({
        async: false,
        type: "get",
        url: "/api/session/getLogin",
        dataType: "json",
        success: (responseSession) => {
            if(responseSession.data == null){
                alert("???????????? ???????????????.");
                location.href="/member/login";
            }else{
                const url = location.href;
                const pdtId = url.substring(url.lastIndexOf("/") + 1);

                let addOrderRespDto = {
                    user_id: responseSession.data.username,
                    pdtId: pdtId,
                    amount: "1"
                }

                console.log(pdtId);
                    $.ajax({
                        async: false,
                        type: "post",
                        url: "/api/product/addOrder",
                        contentType: "application/json",
                        data: JSON.stringify(addOrderRespDto),
                        dataType: "json",
                        success: (response) => {
                            alert("?????? ????????? ???????????????.");
                        },
                        error: (error) => {
                            console.log(error);
                            window.alert("?????? ?????? ????????? ?????????????????????.");
                        }
                    });
            }
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });


}


window.addEventListener('load', () => {
        new ProductDetail();
        new addButtonEvent();
})