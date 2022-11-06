
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

        return responseData;

    }

}

class ProductDetail{
    constructor(){
        const responseData = ProductApi.getInstance().getProductData();
    }

    loadProductDetail(responseData){
        document.querySelector(".p-name").textContent = responseData.pdtName;
        document.querySelector(".simple-info").textContent = responseData.simpleInfo;
        document.querySelector(".p-price").textContent = responseData.pdtPrice;
        document.querySelector(".content-img").textContent = responseData.detailInfo;
    }    

    loadProductColors(responseData){
        const productColors = document.querySelector(".color-box");
        productColors.innerHTML = ``;

        Object.keys(responseData.pdtColor).forEach(color => {
            productColors.innerHTML += `<option value="${color}">${color}</option>`
        })
    }
}

