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
<<<<<<< HEAD
        this.loadProductImages(responseData);
=======
>>>>>>> 38c3427d61cd7b49db874a73094fdbaf9519f234
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

<<<<<<< HEAD
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
=======
     loadProductPrice(responseData){
         const productPrice = document.querySelector(".p-price")
         Object.entries(responseData.pdtColors).foreach(entry => {
             if(productColors.value == entry[0]){
                 entry[1].forEach(value => {
                     productsizes.innerHTML =+ `
                     <td class="p-price">${pdtStock}</td>
                     `;
                 })

             }
         })
     }
>>>>>>> 38c3427d61cd7b49db874a73094fdbaf9519f234
    // 수량 , 금액 넣어야함

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


<<<<<<< HEAD
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


=======

         Object.entries(responseData.pdtColors).foreach(entry => {
             if(productColors.value == entry[0]){
                 entry[1].forEach(value => {
                     productSizes.innerHTML =+ `
                     <option type="hidden" id="pdtDtlId" value="${value.pdtDtlId}">
                     <option id="product-size-${value.sizeName}"selected>${value.sizeName}</option>
                     `;
                 })

             }
         })
    }
>>>>>>> 38c3427d61cd7b49db874a73094fdbaf9519f234
}


window.addEventListener('load', () => {
        new ProductDetail();
<<<<<<< HEAD
})
=======
});
>>>>>>> 38c3427d61cd7b49db874a73094fdbaf9519f234
