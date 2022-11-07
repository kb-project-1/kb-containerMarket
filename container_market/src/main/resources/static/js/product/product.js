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

    // loadProductPrice(responseData){
    //     const productPrice = document.querySelector(".p-price")
    //     Object.entries(responseData.pdtColors).foreach(entry => {
    //         if(productColors.value == entry[0]){
    //             entry[1].forEach(value => {
    //                 productsizes.innerHTML =+ `
    //                 <td class="p-price">${pdtStock}</td>
    //                 `;
    //             })
                
    //         }
    //     })
    // }
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
    }
}

window.onload = () => {

    new ProductDetail();
}