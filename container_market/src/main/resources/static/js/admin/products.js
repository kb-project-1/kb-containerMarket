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

            $.ajax({
                async: false,
                type: "get",
                url: "/api/admin/products",
                data: {
                    "page": page
                },
                dataType : "json",
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

class PageHandler {
    #page = 0;
    #maxPageNumber = 0;
    #pageNumberList = null;

    constructor(page,totalCount) {
        this.#page = page;
        this.#maxPageNumber = totalCount / 10 == 0 ? (totalCount < 10 ? 1: Math.floor(totalCount / 10)) : Math.floor(totalCount / 10)+1;
        this.#pageNumberList = document.querySelector(".page-number-list");
        this.#pageNumberList.innerHTML = "";
        this.loadPageNumbers();
    }

    loadPageNumbers() {
            this.createPreButton();
            this.createNumberButtons();
            this.createNextButton();
            this.addPageButtonEvent();
            this.setColorButton();
    }

    createPreButton() {
        const startIndex = this.#page % 10 ==0 ? (this.#page ==0 ? 1 : this.#page - 9) : this.#page - (this.#page % 10) + 1;
            if(startIndex !=1) {
                this.#pageNumberList.innerHTML +=`
                <a href="javascript:void(0)"><li>&#60;</li></a>
                `;
       }
    }

    createNumberButtons() {
        const startIndex = this.#page % 10 ==0 ? (this.#page ==0 ? 1 : this.#page - 9) : this.#page - (this.#page % 10) + 1;
        const endIndex = startIndex + 9 <= this.#maxPageNumber ? startIndex + 9 : this.#maxPageNumber;

            for(let i = startIndex; i <= endIndex; i++) {
                this.#pageNumberList.innerHTML += `
                <a href="javascript:void(0)"><li>${i}</li></a>
                `;
        }
    }
    createNextButton() {
        const startIndex = this.#page % 10 ==0 ? (this.#page ==0 ? 1 : this.#page - 9) : this.#page - (this.#page % 10) + 1;
        const endIndex = startIndex + 9 <= this.#maxPageNumber ? startIndex + 9 : this.#maxPageNumber;
            if(endIndex != (this.#maxPageNumber/10*10)) {
                this.#pageNumberList.innerHTML +=`
                <a href="javascript:void(0)"><li>&#62;</li></a>
                `;
       }
    }

    addPageButtonEvent() {
            const pageButtons = this.#pageNumberList.querySelectorAll("li");
            pageButtons.forEach(button => {
                button.onclick = () => {
                    if(button.textContent == "<") {
                        const nowPage = ProductsService.getInstance().pageHandler.page;
                        ProductsService.getInstance().pageHandler.page = (nowPage%10 == 0 ? Number(nowPage)-10 : (Math.floor(nowPage/10)*10));
                        ProductsService.getInstance().loadProducts();
                    } else if (button.textContent == ">") {
                        const nowPage = ProductsService.getInstance().pageHandler.page;
                        ProductsService.getInstance().pageHandler.page = (nowPage%10 == 0 ? Number(nowPage)+1 : (Math.floor(nowPage/10)*10+11));
                        ProductsService.getInstance().loadProducts();
                    } else {
                        const nowPage = ProductsService.getInstance().pageHandler.page;
                        if(button.textContent != nowPage) {
                            ProductsService.getInstance().pageHandler.page = button.textContent;
                            ProductsService.getInstance().loadProducts();
                        }
                    }
                }
            });
        }

    setColorButton() {
        const pageButtons = this.#pageNumberList.querySelectorAll("li");
        const nowPage = ProductsService.getInstance().pageHandler.page;
        pageButtons.forEach(button => {
            if(button.textContent == nowPage) {
            button.classList.add("page-button");
            }
        })

    }
}

class ProductsService {
    static #instance = null;

    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ProductsService();
        }
        return this.#instance;
    }

    pageHandler = {
        page: 1,
        totalCount: 0
    }

    loadProducts() {
        const responseData = ProductsApi.getInstance().getProducts(this.pageHandler.page);
        this.pageHandler.totalCount = responseData[0].productTotalCount;

        new PageHandler(this.pageHandler.page, this.pageHandler.totalCount);
        this.getProducts(responseData);
    }

    getProducts(responseData) {

        const products = document.querySelector(".products-body");
        products.innerHTML = ``;

        responseData.forEach(product => {
            products.innerHTML += `
                                <tr>
                                  <td>${product.productId}</td>
                                  <td>${product.categoryName}</td>
                                  <td>${product.productName}</td>
                                  <td>${product.productPrice}</td>
                                  <td><button type="button">보기</button></td>
                                  <td><button type="button">추가</button></td>
                                  <td><button type="button">수정</button></td>
                                  <td><button type="button">삭제</button></td>
                                </tr>
            `
        });
    }
}

window.onload = () => {
      ProductsService.getInstance().loadProducts();
}