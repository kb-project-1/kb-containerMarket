class PaymentApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new PaymentApi();
        }
        return this.#instance;
    }
    getPaymentData() {
        let responseData = null;
        const url = location.href;
        const pdtId = url.substring(url.lastIndexOf("/") + 1);

        $.ajax({
            async: false,
            type: "get",
            url: "/api/order/" + pdtId,
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