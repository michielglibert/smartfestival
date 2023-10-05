import React, {Component} from 'react';
import axios from 'axios';
import _ from "lodash";

export const VerifyStatus ={
    NOT_VERIFIED: "NOT_VERIFIED",
    VERIFIED: "VERIFIED",
    REJECTED: "REJECTED",
    ERROR: "ERROR"
}

class Visitor extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: "",
            validationMode: "ticket",
            verifyStatus: VerifyStatus.NOT_VERIFIED,
            message: "",
            visitor: {},
            allVisitors: []
        }
    }

    componentDidMount() {
        this.getAllVisitors();
    }

    onChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    onSubmit = async event => {
        event.preventDefault();

        if(this.state.id === "" ) {
            return;
        }

        switch(this.state.validationMode) {
            case "ticket": {
                try {
                    const response = await axios.post(
                         `${process.env.REACT_APP_ENDPOINT}/visitor/verify_ticket`,
                         //`http://localhost:2226/visitor/verify_ticket`,
                        {
                            ticketId: this.state.id
                        }
                    );

                    if(response.data.verified) {
                        this.setState({
                            visitor: response.data.visitor,
                            verifyStatus: VerifyStatus.VERIFIED,
                            message: "Your ticket was successfully verified!"
                        });
                    }
                    else {
                        this.setState({
                            message: "Your ticket is not valid",
                            verifyStatus: VerifyStatus.REJECTED
                        });
                    }
                }
                catch(error){
                    this.setState({
                        verifyStatus: VerifyStatus.ERROR,
                        message: "An error occurred"
                    });
                    console.log(error);
                }
            } break;
            case "visitor": {
                try {
                    const response = await axios.post(
                        `${process.env.REACT_APP_ENDPOINT}/visitor/verify_visitor`,
                        //`http://localhost:2226/visitor/verify_visitor`,
                        {
                            visitorId: this.state.id
                        }
                    )

                    if(response.data.verified) {
                        this.setState({
                            visitor: response.data.visitor,
                            verifyStatus: VerifyStatus.VERIFIED,
                            message: "VERIFIED: Valid visitor ID!"
                        });
                    }
                    else {
                        this.setState({
                            verifyStatus: VerifyStatus.REJECTED,
                            message: "REJECTED: Not a valid visitor ID!"
                        });
                    }
                }
                catch(error){
                    this.setState({
                        verifyStatus: VerifyStatus.ERROR,
                        message: "An error occurred"
                    });
                    console.log(error);
                }
            } break;
            default: return;
        }
    }

    getAllVisitors = async () => {
        try {
            const response = await axios.get(
                `${process.env.REACT_APP_ENDPOINT}/visitor/all`
                //`http://localhost:2226/visitor/all`
            )


            if(response.data.visitors) {
                this.setState({
                    allVisitors: response.data.visitors,
                });
            }
        }
        catch(error){
            this.setState({
                verifyStatus: VerifyStatus.ERROR,
                message: "An error occurred"
            });
            console.log(error);
        }
    }

    render() {
        return (
            <div>
                {this.state.verifyStatus === VerifyStatus.NOT_VERIFIED ? (
                    <React.Fragment>
                        <h1>Verify a Ticket or Visitor</h1>
                        <form onSubmit={event => this.onSubmit(event)}>
                            <select name="validationMode" onChange={event => this.onChange(event)}>
                                <option defaultValue disabled={true}>Select a verification mode</option>
                                <option value="ticket">Ticket</option>
                                <option value="visitor">Visitor</option>
                            </select>
                            <label>{this.state.validationMode === "ticket"? "Ticket ID: " : "Visitor ID: "}
                                <input
                                    name="id"
                                    value={this.state.id}
                                    type="text"
                                    onChange={event => this.onChange(event)}/>
                            </label>
                            <input type="submit" value={this.state.validationMode === "ticket"? "VERIFY TICKET" : "VERIFY VISITOR"} />
                        </form>
                    </React.Fragment>
                ) : null }

                {this.state.verifyStatus === VerifyStatus.VERIFIED ? (
                    <React.Fragment>
                        <h1>{this.state.message}</h1>
                        <li className="notification">
                            <h3>Visitor Details</h3>
                            <p>
                                <b>Visitor ID:</b> {this.state.visitor.id}
                            </p>
                            <p>
                                <b>First Name:</b> {this.state.visitor.firstName}
                            </p>
                            <p>
                                <b>Last Name:</b> {this.state.visitor.lastName}
                            </p>
                            <p>
                                <b>Date of Birth:</b> {this.state.visitor.dateOfBirth}
                            </p>
                        </li>
                    </React.Fragment>
                ) : null }

                {this.state.verifyStatus === VerifyStatus.REJECTED || this.state.verifyStatus === VerifyStatus.ERROR ? (
                    <React.Fragment>
                        <h1>{this.state.message}</h1>
                    </React.Fragment>
                ) : null }

                <br/>
                <p>---------------------------------------------------------------------------</p>
                <br/>
                <React.Fragment>
                    <h1>All Visitors ({this.state.allVisitors.length})</h1>
                    {_.map(this.state.allVisitors, (visitor, index) => {
                        return (
                            <li key={index} className="notification">
                                <h3>Visitor {visitor.id}</h3>
                                <p>
                                    <b>Visitor ID:</b> {visitor.id}
                                </p>
                                <p>
                                    <b>First Name:</b> {visitor.firstName}
                                </p>
                                <p>
                                    <b>Last Name:</b> {visitor.lastName}
                                </p>
                                <p>
                                    <b>Date of Birth:</b> {visitor.dateOfBirth}
                                </p>
                                <p>
                                    <b>Banned:</b> {visitor.banned ? "Yes" : "No"}
                                </p>
                            </li>
                        )
                    })}
                </React.Fragment>

            </div>
        );
    }
}

export default Visitor;