class ProductMst {
    #category;
    #name;
    #price;
    #simpleInfo;
    #detailInfo;

    constructor(category, name, price, simpleInfo, detailInfo, optionInfo, managementInfo, shippingInfo) {
        this.#category = category;
        this.#name = name;
        this.#price = price;
        this.#simpleInfo = simpleInfo;
        this.#detailInfo = detailInfo;
    }

    getCategory() {return this.#category;}
    setCategory(category) {this.#category = category;}

    getName() {return this.#name;}
    setName(name) {this.#name = name;}

    getPrice() {return this.#price;}
    setPrice(price) {this.#price = price;}

    getSimpleInfo() {return this.#simpleInfo;}
    setSimpleInfo(simpleInfo) {this.#simpleInfo = simpleInfo;}

    getDetailInfo() {return this.#detailInfo;}
    setDetailInfo(detailInfo) {this.#detailInfo = detailInfo;}

    getObject() {
        const obj = {
            category: this.#category,
            name: this.#name,
            price: this.#price,
            simpleInfo: this.#simpleInfo,
            detailInfo: this.#detailInfo,
        }
        return obj;
    }
}

class CommonApi {
    getCategoryList() {
        let responseResult = null;

        $.ajax({
            async: false,
            type: "get",
            url: "/api/admin/product/category",
            dataType : "json",
            success: (response) => {
                responseResult = response.data;
            },
            error: (error) => {
                 console.log(error);
            }
        });
        return responseResult;
    }
}


class RegisterApi {
    createProductRequest(productMst) {
        let responseResult = null;

        $.ajax({
            async: false,
            type: "post",
            url: "/api/admin/product",
            contentType: "application/json",
            data: JSON.stringify(productMst),
            dataType: "json",
            success: (response) => {
                responseResult = response.data;
            },
            error: (error) => {
                alert("상품등록 실패");
            }
        });

        return responseResult;
    }
}

class UpdateApi {

    static #instance = null;

    constructor() {

    }

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new UpdateApi();
        }
        return this.#instance;
    }

    getProductInfo(params) {
        let responseData = null;
        $.ajax({
                async: false,
                type: "get",
                url: "/api/admin/product/"+params,
                dataType : "json",
                success: (response) => {
                    responseData = response.data;
                    UpdateService.getInstance().setProductInfo(responseData);
                },
                error: (error) => {
                     alert("오류가 발생했습니다.");
                }
            });
    }
}

class UpdateService {

    static #instance = null;

    constructor() {

    }

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new UpdateService();
        }
        return this.#instance;
    }

    getProductInfo() {
        const param = new Array();

            // 현재 페이지의 url
        const url = decodeURIComponent(location.href);
        let params = url.substring( url.lastIndexOf('/')+1, url.length );
        UpdateApi.getInstance().getProductInfo(params);
    }

    setProductInfo(productInfo) {
        console.log(productInfo);
        const productInputs = document.querySelectorAll('.product-inputs');
        productInputs[0].options[productInfo.categoryId].selected = true;
        productInputs[1].value = productInfo.productName;
        productInputs[2].value = productInfo.productPrice;
        productInputs[3].value = productInfo.simpleInfo;
        productInputs[4].value = productInfo.detailInfo;

    }


}

class RegisterService {
    static #instance = null;

    constructor() {
    }

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new RegisterService();
        }
        return this.#instance;
    }

    loadRegister() {

    }

    getCategoryList() {
        const commonApi = new CommonApi();
        const productCategoryList = commonApi.getCategoryList();

        const productCategory = document.querySelector(".product-category")
        productCategory.innerHTML = `<option value="none">상품 종류</option>`;

        productCategoryList.forEach(category => {
            productCategory.innerHTML += `
            <option value="${category.id}">${category.name}</option>
            `
        })
    }

}
window.addEventListener('load', () => {
    RegisterService.getInstance().getCategoryList();
    UpdateService.getInstance().getProductInfo();
});