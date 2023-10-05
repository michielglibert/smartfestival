import React, {Component} from 'react';
import axios from 'axios';
import _ from 'lodash';


export const TicketOrderStatus ={
    NOT_RESERVED: "NOT_RESERVED",
    RESERVED: "RESERVED",
    PAID: "PAID",
    CANCELED: "CANCELED",
    ERROR: "ERROR"
}

class Ticket extends Component {

    constructor(props) {
        super(props);
        this.state = {
            numberOfTickets: "",
            email: "",
            ticketInfo: [],
            tickets: [],
            orderId: "",
            price: "",
            message: "",
            ticketOrderStatus: TicketOrderStatus.NOT_RESERVED,
            allTickets: []
        }

    }

    componentDidMount() {
        this.getAllTickets()
    }

    onChange = event => {

        switch(event.target.name) {
        case "numberOfTickets": {
                let ticketInfo = [];
                for(let i = 0; i < event.target.value; i++) {
                    ticketInfo.push({
                        firstName: "",
                        lastName: "",
                        dateOfBirth: new Date().toLocaleDateString()
                    });
                }
                this.setState({
                    [event.target.name]: event.target.value,
                    ticketInfo: ticketInfo
                });
            } break;

        default: {
                this.setState({
                    [event.target.name]: event.target.value
                });
            }
        }

    }

    onTicketInfoChange = (event, index) => {

        let ticketInfo = this.state.ticketInfo;
        ticketInfo[index][event.target.name] = event.target.value;

        this.setState({
            ticketInfo: ticketInfo
        })
    }


    onSubmitReserveTicketForm = async event => {
        event.preventDefault();

        if(this.state.numberOfTickets === "" || this.state.email === "") {
            return
        }

        for(let i = 0; i < this.state.ticketInfo.length; i++) {
            const ticketInfo = this.state.ticketInfo[i];
            if(ticketInfo.firstName === "" || ticketInfo.lastName === "" || ticketInfo.dateOfBirth === new Date().toLocaleDateString()) {
                return
            }
        }

        try {
            const response = await axios.post(
                `${process.env.REACT_APP_ENDPOINT}/ticket/create_ticket_order`,
                {
                    numberOfTickets: this.state.numberOfTickets,
                    email: this.state.email,
                    ticketInfo: this.state.ticketInfo
                }
            )

            if(response.data.success) {
                this.setState({
                    orderId: response.data.orderId,
                    tickets: response.data.tickets,
                    message: response.data.message,
                    price: response.data.price,
                    ticketOrderStatus: TicketOrderStatus.RESERVED
                });
            }
            else {
                this.setState({
                    message: response.data.message,
                    ticketOrderStatus: TicketOrderStatus.ERROR
                });
            }
        }
        catch(error){
            this.setState({
                ticketOrderStatus: TicketOrderStatus.ERROR,
                message: "An error occurred"
            });
            console.log(error);
        }
    }

    onSubmitPayTicketForm = async (event) => {
        if(event) {
            event.preventDefault()
        }
        try {
            const response = await axios.post(
                `${process.env.REACT_APP_ENDPOINT}/ticket/pay_ticket_order`,
                {
                    canceled: false,
                    orderId: this.state.orderId
                }
            )

            if(response.data.success) {
                this.setState({
                    orderId: response.data.orderId,
                    tickets: response.data.tickets,
                    message: response.data.message,
                    price: response.data.price,
                    ticketOrderStatus: TicketOrderStatus.PAID,
                });
            }
            else {
                this.setState({
                    message: response.data.message,
                    ticketOrderStatus: TicketOrderStatus.ERROR
                });
            }
        }
        catch(error){
            this.setState({
                ticketOrderStatus: TicketOrderStatus.ERROR,
                message: "An error occurred"
            });
            console.log(error);
        }

    }

    onCancelPayTicketForm = async () => {
        try {
            const response = await axios.post(
                `${process.env.REACT_APP_ENDPOINT}/ticket/pay_ticket_order`,
                {
                    canceled: true,
                    orderId: this.state.orderId
                }
            )

            if(response.data.success) {
                this.setState({
                    orderId: "",
                    tickets: [],
                    message: response.data.message,
                    price: "",
                    ticketOrderStatus: TicketOrderStatus.CANCELED,
                });
            }
            else {
                this.setState({
                    message: response.data.message,
                    ticketOrderStatus: TicketOrderStatus.ERROR
                });
            }
        }
        catch(error){
            this.setState({
                ticketOrderStatus: TicketOrderStatus.ERROR,
                message: "An error occurred"
            });
            console.log(error);
        }
    }

    getAllTickets = async () => {
        try {
            const response = await axios.get(
                `${process.env.REACT_APP_ENDPOINT}/ticket/all`,
                //`http://localhost:2225/ticket/all`
            )

            if(response.data.tickets) {
                this.setState({
                    allTickets: response.data.tickets
                });
            }
        }
        catch(error){
            this.setState({
                ticketOrderStatus: TicketOrderStatus.ERROR,
                message: "An error occurred"
            });
            console.log(error);
        }
    }

    render() {
        return (
            <div>
                {this.state.ticketOrderStatus === TicketOrderStatus.NOT_RESERVED?
                    (
                       <React.Fragment>
                           <h1>Reserve Your Tickets</h1>
                           <form onSubmit={event => this.onSubmitReserveTicketForm(event)}>
                               <label>Number of Tickets:{" "}
                                   <input
                                       name="numberOfTickets"
                                       type="text"
                                       value={this.state.numberOfTickets}
                                       onChange={event => this.onChange(event)}
                                   />
                               </label>
                               <br/>
                               {this.state.numberOfTickets !== "" ? (
                                   <label>Email:{" "}
                                       <input
                                           name="email"
                                           type="text"
                                           value={this.state.email}
                                           onChange={event => this.onChange(event)}
                                       />
                                   </label>
                                   ) : null }
                               {
                                   _.times(this.state.numberOfTickets, index => {
                                       return (
                                           <React.Fragment key={index}>
                                               <h3>Info for ticket {index + 1}</h3>
                                               <label>First Name: {" "}
                                                   <input
                                                       name="firstName"
                                                       value={this.state.ticketInfo[index].firstName}
                                                       type="text"
                                                       onChange={event => this.onTicketInfoChange(event, index)}/>
                                               </label>
                                               <label>Last Name: {" "}
                                                   <input
                                                       name="lastName"
                                                       value={this.state.ticketInfo[index].lastName}
                                                       type="text"
                                                       onChange={event => this.onTicketInfoChange(event, index)}/>
                                               </label>
                                               <label>Date of Birth: {" "}
                                                   <input
                                                       name="dateOfBirth"
                                                       value={this.state.ticketInfo[index].dateOfBirth}
                                                       type="date"
                                                       onChange={event => this.onTicketInfoChange(event, index)}/>
                                               </label>
                                           </React.Fragment>
                                       )
                                   })
                               }
                               <input type="submit" value="RESERVE TICKETS" />
                           </form>

                           <br/>
                           <p>---------------------------------------------------------------------------</p>
                           <br/>

                           <h1>Pay Your Reserved Tickets</h1>
                           <form onSubmit={event => this.onSubmitPayTicketForm(event)}>
                               <label>Order Id:{" "}
                                   <input
                                       name="orderId"
                                       type="text"
                                       value={this.state.orderId}
                                       onChange={event => this.onChange(event)}
                                   />
                               </label>
                               <input type="submit" value="PAY TICKETS" />
                           </form>
                       </React.Fragment>
                    ) : null
                }
                {this.state.ticketOrderStatus === TicketOrderStatus.RESERVED ?
                    (
                        <React.Fragment>
                            <h1>{this.state.message}</h1>
                            {_.map(this.state.tickets, (ticket, index) => {
                                return (
                                    <li key={index} className="notification">
                                        <h3>Ticket {index + 1}</h3>
                                        <p>
                                            <b>Ticket ID:</b> {ticket.id}
                                        </p>
                                        <p>
                                            <b>First Name:</b> {ticket.firstName}
                                        </p>
                                        <p>
                                            <b>Last Name:</b> {ticket.lastName}
                                        </p>
                                        <p>
                                            <b>Date of Birth:</b> {ticket.dateOfBirth}
                                        </p>
                                        <p>
                                            <b>Email:</b> {ticket.email}
                                        </p>
                                        <p>
                                            <b>Price:</b> {ticket.price}
                                        </p>
                                        <p>
                                            <b>Status:</b> {ticket.status}
                                        </p>
                                        <p>
                                            <b>Activated:</b> {ticket.activated? "Yes" : "No"}
                                        </p>
                                        <p>
                                            <b>Order ID:</b> {ticket.orderId}
                                        </p>
                                    </li>
                                )
                            })}

                            <form>
                                <label>Total Price: {this.state.price}</label>
                                <button type="button" onClick={(event) => this.onSubmitPayTicketForm(event)}>PAY TICKETS</button>
                                <button type="button" onClick={(event) => this.onCancelPayTicketForm(event)}>CANCEL TICKETS</button>
                            </form>
                        </React.Fragment>
                    ) : null
                }
                {this.state.ticketOrderStatus === TicketOrderStatus.PAID ?
                    (
                        <React.Fragment>
                            <h1>{this.state.message}</h1>
                            <h3>Total Price: {this.state.price}</h3>
                            {_.map(this.state.tickets, (ticket, index) => {
                                return (
                                    <li key={index} className="notification">
                                        <h3>Ticket {index + 1}</h3>
                                        <p>
                                            <b>Ticket ID:</b> {ticket.id}
                                        </p>
                                        <p>
                                            <b>First Name:</b> {ticket.firstName}
                                        </p>
                                        <p>
                                            <b>Last Name:</b> {ticket.lastName}
                                        </p>
                                        <p>
                                            <b>Date of Birth:</b> {ticket.dateOfBirth}
                                        </p>
                                        <p>
                                            <b>Email:</b> {ticket.email}
                                        </p>
                                        <p>
                                            <b>Price:</b> {ticket.price}
                                        </p>
                                        <p>
                                            <b>Status:</b> {ticket.status}
                                        </p>
                                        <p>
                                            <b>Activated:</b> {ticket.activated? "Yes" : "No"}
                                        </p>
                                        <p>
                                            <b>Order ID:</b> {ticket.orderId}
                                        </p>
                                    </li>
                                )
                            })}
                        </React.Fragment>
                    ) : null
                }
                {this.state.ticketOrderStatus === TicketOrderStatus.CANCELED ?
                    (
                        <React.Fragment>
                            <h1>{this.state.message}</h1>
                        </React.Fragment>
                    ) : null
                }
                {this.state.ticketOrderStatus === TicketOrderStatus.ERROR ?
                    (
                        <React.Fragment>
                            <h1>{this.state.message}</h1>
                        </React.Fragment>
                    ) : null
                }
                <br/>
                <p>---------------------------------------------------------------------------</p>
                <br/>
                <React.Fragment>
                    <h1>All Sold and Reserved Tickets ({this.state.allTickets.length}/20)</h1>
                    {_.map(this.state.allTickets, (ticket, index) => {
                        return (
                            <li key={index} className="notification">
                                <h3>Ticket {ticket.id}</h3>
                                <p>
                                    <b>Ticket ID:</b> {ticket.id}
                                </p>
                                <p>
                                    <b>First Name:</b> {ticket.firstName}
                                </p>
                                <p>
                                    <b>Last Name:</b> {ticket.lastName}
                                </p>
                                <p>
                                    <b>Date of Birth:</b> {ticket.dateOfBirth}
                                </p>
                                <p>
                                    <b>Email:</b> {ticket.email}
                                </p>
                                <p>
                                    <b>Price:</b> {ticket.price}
                                </p>
                                <p>
                                    <b>Status:</b> {ticket.status}
                                </p>
                                <p>
                                    <b>Activated:</b> {ticket.activated? "Yes" : "No"}
                                </p>
                                <p>
                                    <b>Order ID:</b> {ticket.orderId}
                                </p>
                            </li>
                        )
                    })}
                </React.Fragment>
            </div>
        );
    }
}

export default Ticket;