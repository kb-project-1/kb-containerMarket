class ProductsApi {
    static #instance = null;

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ProductsApi();
        }
        return this.#instance;
    }

    getProducts(page) {
        let responseData = null;

        const url = location.href;
        let category = url.substring(url.lastIndexOf("/") + 1);
        if(category == "") {
            category = "all";
        }
        console.log(category)

        $.ajax({
            async: false,
            type: "get",
            url: "/api/category/" + category,
            data: {
                "page": page
            },
            dataType: "json",
            success: (response) => {
                responseData = response.data;
            },
            error: (error) => {
                console.log(error);
            }
        });

        return responseData;

    }
}

class pageScroll {

    constructor() {
        this.addScrollPagingEvent();
    }

    addScrollPagingEvent() {
        const html = document.querySelector("html");
        const body = document.querySelector("body");

        window.onscroll = () => {

            let scrollStatus = html.offsetHeight - html.clientHeight - html.scrollTop;
            console.log("현재 스크롤 상태:" + scrollStatus);
            if(scrollStatus > -10 && scrollStatus < 10 ) {
                const nowPage = ProductsService.getInstance().productsEntity.page;
                ProductsService.getInstance().productsEntity.page = Number(nowPage) + 1;
                ProductsService.getInstance().loadProducts();
            }
        }
    }
}

class ProductsService{

    static #instance = null;

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ProductsService();
        }
        return this.#instance;
    }

    productsEntity = {
        page: 1,
        totalCount: 0,
        maxPage: 0
    }

    loadProducts() {
            if(this.productsEntity.page == 1 || this.productsEntity.page < Number(this.productsEntity.maxPage) + 1) {
                const responseData = ProductsApi.getInstance().getProducts(this.productsEntity.page);
                if(responseData.length > 0) {
                    this.productsEntity.totalCount = responseData[0].productTotalCount;
                    this.productsEntity.maxPage = responseData[0].productTotalCount % 9 ==0
                                                        ? responseData[0].productTotalCount / 9
                                                        : Math.floor(responseData[0].productTotalCount / 9) + 1;
                    this.getProducts(responseData);
                } else {
                    alert("해당 카테고리에 등록된 상품 정보가 없습니다.");
                    location.href = "/";
                }
            }
        }


    getProducts(responseData) {
        const product = document.querySelector(".product");

        responseData.forEach(productList => {
            product.innerHTML += `
            <li>
                <div class="product-list">
                    <div class="product-img"><img src="/static/images/product_img.png" alt="상품이미지"></div>
                    <div class="icon"><img src="/static/images/info_best.jpg" alt="상품이미지"></div>
                    <div class="product-info">
                        <ul>
                            <li class="product-name">${productList.productName}</li>
                            <li class="product-price">${productList.productPrice}</li>
                        </ul>
                    </div>
                </div>
            </li>
            `;
        })
        this.addProductListEvent(responseData);
    }

    addProductListEvent(responseData) {
            const collectionProducts = document.querySelectorAll(".product-list");

            collectionProducts.forEach((product, index) => {
                product.onclick = () => {
                    location.href = "/product/" + responseData[index].productId;
                }
            });

        }
}

function getLoginSession() {
    $.ajax({
        async: false,
        type: "get",
        url: "/api/session/getLogin",
        dataType: "json",
        success: (response) => {
            console.log(response.data);
            const hrefLogin = document.querySelector(".session-login");
            const hrefJoin = document.querySelector(".session-join");

            if(response.data == null){
                hrefLogin.innerHTML = `<a href="/member/login">LOGIN</a>`;
                hrefJoin.innerHTML = `<a href="/member/join">JOIN</a>`;
            }else{
                hrefLogin.innerHTML = `<a href="/logout">LOGOUT</a>`;
                if(response.data.role_id == 3){
                    hrefJoin.innerHTML = `<a href="/admin/products">ADMIN PAGE</a>`;
                }else {
                    hrefJoin.innerHTML = `<a href="/member/modify">MODIFY</a>`;
                }
            }
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });
}

window.addEventListener('load', () => {
        getLoginSession();
        ProductsService.getInstance().loadProducts();
        new pageScroll;
});

