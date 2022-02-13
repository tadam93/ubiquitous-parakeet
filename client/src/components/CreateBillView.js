import { Component } from "react";

export class CreateBillView extends Component {
    render() {
        return (
            <div>
                <h3>Create Bill</h3>
                <form onSubmit={this.props.onCreate}>
                    <label>
                        Company
                        <input type="text" name="companyName" />
                    </label>
                    <label>
                        Payable Date
                        <input type="datetime-local" name="payableDate" />
                    </label>
                    <label>
                        Payable Amount
                        <input type="number" name="payableAmount" min="0.01" step="0.01"  />
                    </label>
                    <input type="submit" value="Create" />
                </form>
            </div>
        )
    }
}