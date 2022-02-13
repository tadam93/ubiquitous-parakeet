import {Component} from "react";

export class Bill extends Component {
    render() {
        return (
            <tr>
                <td>{this.props.companyName}</td>
                <td>{this.props.payableDate}</td>
                <td>{this.props.cost}</td>
                <td><input value="Pay" type="button" onClick={() => this.props.payBill(this.props.companyName, this.props.payableDate)} /> </td>
            </tr>
        );
    }
}