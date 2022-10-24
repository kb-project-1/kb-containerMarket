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
                    console.log(responseData[0].productTotalCount)
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
        this.#maxPageNumber = totalCount % 10 == 0 ? Math.floor(totalCount % 10) : Math.floor(totalCount % 10)+1;
        this.#pageNumberList = document.querySelector(".page-number-list");
        this.#pageNumberList.innerHTML = "";
        this.loadPageNumbers();
    }

    loadPageNumbers() {
            this.createPreButton();
            this.createNumberButtons();
            this.createNextButton();
    }

    createPreButton() {
        const startIndex = this.#page % 10 ==0 ? this.#page - 9 : this.#page - (this.#page % 10) + 1;
            if(startIndex !=1) {
                this.#pageNumberList.innerHTML +=`
                <a href="javascript:void(0)"><li>&#60;</li></a>
                `;
       }
    }

    createNumberButtons() {
        const startIndex = this.#page % 10 ==0 ? (this.#page ==0 ? 0 : this.#page - 9) : this.#page - (this.#page % 10) + 1;
        const endIndex = startIndex + 9 <= this.#maxPageNumber ? startIndex + 9 : this.#maxPageNumber;

            for(let i = startIndex; i <= endIndex; i++) {
                this.#pageNumberList.innerHTML += `
                <a href="javascript:void(0)"><li>${i}</li></a>
                `;
        }
    }
    createNextButton() {
        const startIndex = this.#page % 10 ==0 ? this.#page - 9 : this.#page - (this.#page % 10) + 1;
        const endIndex = startIndex + 9 <= this.#maxPageNumber ? startIndex + 9 : this.#maxPageNumber;
            if(this.#page != endIndex) {
                this.#pageNumberList.innerHTML +=`
                <a href="javascript:void(0)"><li>&#62;</li></a>
                `;
       }
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
        page: 0,
        totalCount: 0
    }

    getProducts(page) {
        const responseData = ProductsApi.getInstance().getProducts(page);
        this.pageHandler.totalCount = responseData[0].productTotalCount;
        new PageHandler(this.pageHandler.page, this.pageHandler.totalCount)
    }
}

window.onload = () => {
    ProductsService.getInstance().getProducts(0);
}