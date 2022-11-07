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


    }

    loadProductDetail(responseData){
        document.querySelector(".product-name").textContent = responseData.pdtName;
        document.querySelector(".p-price").textContent = responseData.pdtPrice;
    }

    loadProductColors(responseData){
        
    }


}
window.onload = () => {

    new ProductDetail();
}
