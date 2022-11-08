class ImportApi {
    #IMP = null;

    constructor() {
        this.#IMP = window.IMP;
        this.#IMP.init("imp07012272");
        this.addPaymentEvent();
    }

    addPaymentEvent() {
        document.querySelector(".payment-button").onclick = () => {
            this.requestPay();
        }
    }

    requestPay() {
        const name = document.querySelector(".principal-name").value;
        const email = document.querySelector(".email1").value + "@" + document.querySelector(".email2").value;
        const pdtName = document.querySelector(".product-name").textContent;
        const pdtPrice = document.querySelector(".product-price").textContent;
        const zoneCode = document.querySelector(".address-zonecode").value;
        const addressAll = document.querySelector(".address-all").value;
        const addressDetail = document.querySelector(".address-detail").value;
        const address = addressAll + " " + addressDetail;
        const phone = document.querySelector(".phone_number1").value + document.querySelector(".phone_number2").value + document.querySelector(".phone_number3").value;

        // IMP.request_pay(param, callback) 결제창 호출
        IMP.request_pay({ // param
            pg: "kakaopay",
            pay_method: "card",
            merchant_uid: "PRODUCT-" + new Date().getTime(),
            name: pdtName,
            amount: pdtPrice,
            buyer_email: email,
            buyer_name: name,
            buyer_tel: phone,
            buyer_addr: address,
            buyer_postcode: zoneCode
        }, function (rsp) { // callback
            if (rsp.success) {
                // 결제 성공 시 로직,
            } else {
                // 결제 실패 시 로직,
            }
        });
    }
}

class AddressApi {
    static #instance = null;

    static getInstance() {
        if(this.#instance == null){
            this.#instance = new AddressApi();
        }
        return this.#instance;
    }

    #daumApi = null;
    constructor() {
        this.#daumApi = new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                const addressZonecode = document.querySelector(".address-zonecode");
                const addressAll = document.querySelector(".address-all");

                addressZonecode.value = data.zonecode;
                addressAll.value = data.address + `
                    ${data.buildingName != "" ?
                    "(" + data.buildingName + ")" : ""}`;
            }
        });
    }

    addAddressButtonEvent() {
        document.querySelector(".address-button").onclick = () => {
            this.#daumApi.open();
        }
    }
}

function insertData(){
    responseData = null;

    $.ajax({
        async: false,
        type: "get",
        url: "/api/session/getLogin",
        dataType: "json",
        success: (response) => {
            if(response.data == null){
                responseData = response;
            }else{
                responseData = response.data;
            }
        },
        error: (error) => {
            console.log(error.responseJSON);
        }
    });

    document.querySelector(".principal-name").value = responseData.username;
    document.querySelector(".phone_number1").value = responseData.phone.slice(0,3);
    if(responseData.phone.length < 11) {
        document.querySelector(".phone_number2").value = responseData.phone.slice(3, 6);
        document.querySelector(".phone_number3").value = responseData.phone.slice(6, 10);
    }else{
        document.querySelector(".phone_number2").value = responseData.phone.slice(3, 7);
        document.querySelector(".phone_number3").value = responseData.phone.slice(7, 11);
    }
}

function init() {
    insertData();
    AddressApi.getInstance().addAddressButtonEvent();
    new ImportApi();
}

window.addEventListener('load', (init));