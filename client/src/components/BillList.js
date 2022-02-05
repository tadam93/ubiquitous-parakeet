import {Component} from "react";
import {Bill} from "./Bill";
import {BillsAccessor} from "../network/BillsAccessor";

export class BillList extends Component {
    billsAccessor = new BillsAccessor();

    constructor(props) {
        super(props);
        this.state = {
            bills: []
        }
    }

    componentDidMount() {
        let self = this;
        this.billsAccessor.getBills(function (billsResponse) {
            self.setState({bills: billsResponse})
        });
    }

    render() {
        const bills = this.state.bills.map((bill) =>
            <Bill companyName={bill.companyName} cost={bill.cost} payableDate={bill.payableDate} />);
        return (
            <table>
                {bills}
            </table>
        );
    }
}