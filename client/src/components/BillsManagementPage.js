import {Component} from "react";
import {CreateBillView} from "./CreateBillView";
import {BillList} from "./BillList";
import {BillsAccessor} from "../network/BillsAccessor";

export class BillsManagementPage extends Component {
    billsAccessor = new BillsAccessor();

    constructor(props) {
        super(props);
        this.state = {
            bills: []
        }

        this.handleCreate = this.handleCreate.bind(this);
        this.refreshBills = this.refreshBills.bind(this);
        this.payBill = this.payBill.bind(this);
    }

    componentDidMount() {
        this.refreshBills();
    }

    refreshBills() {
        let self = this;
        this.billsAccessor.getBills(function (billsResponse) {
            self.setState({bills: billsResponse})
        });
    }

    handleCreate(event) {
        event.preventDefault();
        let bill = {
            companyName: event.target[0].value,
            payableDate: event.target[1].value,
            cost: event.target[2].value
        }

        this.billsAccessor.createBill(bill, this.refreshBills);
    }

    payBill(companyName, payableDate) {
        this.billsAccessor.payBill(companyName, payableDate, this.refreshBills);
    }

    render() {
        return (
            <div>
                <h1>Welcome to Bill Manager</h1>
                <CreateBillView onCreate={this.handleCreate}/>
                <BillList bills={this.state.bills} payBill={this.payBill}/>
            </div>
        );
    }
}