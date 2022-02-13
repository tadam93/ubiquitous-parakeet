import {Component} from "react";
import {Bill} from "./Bill";

export class BillList extends Component {
    render() {
        const bills = this.props.bills.map((bill) =>
            <Bill key={bill.companyName + bill.cost + bill.payableDate}
                  companyName={bill.companyName}
                  cost={bill.cost}
                  payableDate={bill.payableDate}
                  payBill={this.props.payBill}
            />);
        return (
            <div>
                <h3>Current Bills</h3>
                <table>
                    <thead>
                        <tr><td>Company Name</td><td>Payable Date</td><td>Amount Due</td><td>Pay Off</td></tr>
                    </thead>
                    <tbody>
                        {bills}
                    </tbody>
                </table>
            </div>
        );
    }
}