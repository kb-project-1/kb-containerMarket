class CommonApi {
    static #instance = null;

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new CommonApi();
        }
        return this.#instance;
    }

    getProducts() {
    let responseData = null;
        $.ajax({
                async: false,
                type: "get",
                url: "/api/admin/option/products/mst",
                dataType: "json",
                success: (response) => {
                    responseData = response.data;
                    console.log(responseData);
                },
                error : (error) => {
                    console.log(error);
                }
            });

            return responseData;
    }

     getProductSizeList(productId) {
            let responseData = null;
            $.ajax({
                async: false,
                type: "get",
                url: "/api/admin/option/products/size/" + productId,
                dataType: "json",
                success: (response) => {
                    responseData = response.data;

                },
                error : (error) => {
                    console.log(error);

                }
            });

            return responseData;
        }
}

class Option {
    static #instance = null;

        static getInstance() {
            if(this.#instance == null) {
                this.#instance = new Option();
            }
            return this.#instance;
        }

        setProductsSelectOptions() {
            const productsSelect = document.querySelector(".product-select");
            CommonApi.getInstance().getProducts().forEach(product => {
                productsSelect.innerHTML += `
                    <option value="${product.pdtId}">(${product.category})${product.pdtName}</option>
                `
            });
            this.addProductSelectEvent();
        }

        addProductSelectEvent() {
            const productsSelect = document.querySelector(".product-select");
            productsSelect.onchange = () => {
                this.setSizeSelectOptions(productsSelect.value);
            }
        }

        setSizeSelectOptions(productId) {

                const productSizeSelect = document.querySelector(".product-size");
                productSizeSelect.innerHTML = ``;
                CommonApi.getInstance().getProductSizeList(productId).forEach(size => {
                    productSizeSelect.innerHTML += `
                    <option value="${size.sizeId}">${size.sizeName}</option>
                    `;
                })
            }
}

window.onload = () => {
    Option.getInstance().setProductsSelectOptions();
}