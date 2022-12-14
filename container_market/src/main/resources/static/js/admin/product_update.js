class ProductMst {
    #id;
    #name;
    #category;
    #price;
    #simpleInfo;
    #detailInfo;

    constructor(id,category, name, price, simpleInfo, detailInfo, optionInfo, managementInfo, shippingInfo) {
        this.#id = id;
        this.#category = category;
        this.#name = name;
        this.#price = price;
        this.#simpleInfo = simpleInfo;
        this.#detailInfo = detailInfo;
    }

    getId() {return this.#id;}
    setId(id) {this.#id = id;}

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
            id: this.#id,
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
    updateProduct(productMst) {
        let responseResult = null;

        console.log(productMst);
        $.ajax({
            async: false,
            type: "patch",
            url: "/api/admin/product",
            contentType: "application/json",
            data: JSON.stringify(productMst),
            dataType: "json",
            success: (response) => {
                responseResult = response.data;
            },
            error: (error) => {
                alert("???????????? ??????");
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
                     alert("????????? ??????????????????.");
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

            // ?????? ???????????? url
        const url = decodeURIComponent(location.href);
        let params = url.substring( url.lastIndexOf('/')+1, url.length );
        UpdateApi.getInstance().getProductInfo(params);
        RegisterService.getInstance().setUpdateButton(Number(params));
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
        productCategory.innerHTML = `<option value="none">?????? ??????</option>`;

        productCategoryList.forEach(category => {
            productCategory.innerHTML += `
            <option value="${category.id}">${category.name}</option>
            `
        })
    }

    setUpdateButton(id) {
        const productInputs = document.querySelectorAll('.product-inputs');
        const updateButton = document.querySelector(".regist-button");
        updateButton.onclick = () => {
                    const category = Number(productInputs[0].value);
                    const name = productInputs[1].value;
                    const price = Number(productInputs[2].value);
                    const simpleInfo = productInputs[3].value;
                    const detailInfo = productInputs[4].value;

                    const productMst = new ProductMst(
                        id,category, name, price, simpleInfo, detailInfo);

                    const registerApi = new RegisterApi();
                    console.log(productMst.getObject());
                    if(registerApi.updateProduct(productMst.getObject())) {
                         alert("?????? ?????? ??????");
                         location.href = "/admin/products";
                   }
                };
    }
}
window.addEventListener('load', () => {
    RegisterService.getInstance().getCategoryList();

    UpdateService.getInstance().getProductInfo();
});