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

    deleteProduct(productId) {
            let responseData = null;
            $.ajax({
                async: false,
                type: "delete",
                url: "/api/admin/product/"+productId,
                dataType : "json",
                success: (response) => {
                    alert("성공적으로 삭제되었습니다.");
                    location.reload();
                },
                error: (error) => {
                     alert("오류가 발생했습니다.");
                }
            });
        }
        submitProduct(productId) {
        location.href="/admin/product/update/"+productId;
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
        ProductsService.getInstance().setDeleteButton();
        ProductsService.getInstance().setUpdateButton();
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
                                  <td><button type="button"><a href="/product/${product.productId}">보기</a></button></td>
                                  <td><button type="button" class="update-button" value="${product.productId}">수정</button></td>
                                  <td><button type="button" class="delete-button" value="${product.productId}">삭제</button></td>
                                </tr>
            `
        });
    }

    setDeleteButton() {
        const deleteButtons = document.querySelectorAll(".delete-button");
        deleteButtons.forEach((button) => {
            button.onclick = () => {
                const productId = button.value;
                if(confirm("정말로 삭제 하시겠습니까?")) {
                    ProductsApi.getInstance().deleteProduct(productId);
                }
            }
        })
    }
    setUpdateButton() {
        const deleteButtons = document.querySelectorAll(".update-button");
        deleteButtons.forEach((button) => {
            button.onclick = () => {
                const productId = button.value;
                ProductsApi.getInstance().submitProduct(productId);
            }
        })
    }
}

window.addEventListener('load', () => {
    ProductsService.getInstance().loadProducts();
    ProductsService.getInstance().setDeleteButton();
    ProductsService.getInstance().setUpdateButton();
});

