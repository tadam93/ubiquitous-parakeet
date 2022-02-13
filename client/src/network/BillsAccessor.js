import axios from "axios";

export class BillsAccessor {
    BASE_API = "http://localhost:8080/api/v1/bills";

    getBills(successCallback) {
        return axios.get(this.BASE_API)
            .then((response) => {
                if (response.status === 200) {
                    successCallback(response.data)
                }
            });
    }

    createBill(bill, successCallback) {
        return axios.post(this.BASE_API, bill)
            .then((response) => {
                if (response.status === 200) {
                    successCallback(response.data)
                }
            });
    }

    payBill(companyName, payableDate, successCallback) {
        return axios.delete(this.BASE_API + `/${companyName}/${payableDate}`)
            .then((response) => {
                if (response.status === 200) {
                    successCallback(response.data)
                }
            });
    }
}