import React, { Component } from "react";
import axios from "axios";
import _ from "lodash";
import moment from "moment";

export class Security extends Component {
	state = {
		name: "",
		description: "",
		time: moment().format("YYYY-MM-DD"),
		location: {
			name: "loc",
			xlat: "1.2",
			xlong: "1.4"
		},
		severity: "MINOR",
		users: [],
		userid: "",
		incidents: []
	};

	componentDidMount() {
		this.getIncidents();
	}

	getIncidents = async () => {
		const res = await axios.get(
			`${process.env.REACT_APP_ENDPOINT}/security/all`
		);
		const { data } = res;
		this.setState({ incidents: data });
	};

	handleChange = event => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	handleSubmit = async event => {
		event.preventDefault();
		axios
			.post(`${process.env.REACT_APP_ENDPOINT}/security/create_incident`, {
				name: this.state.name,
				description: this.state.description,
				time: moment(this.state.time),
				location: this.state.location,
				severity: this.state.severity,
				users: this.state.users
			})
			.then(resp => {
				this.setState({ users: [] });
				console.log(resp);
			})
			.catch(err => {
				console.log(err);
			});
	};

	adduser = event => {
		event.preventDefault();
		if (this.state.userid !== "") {
			this.state.users.push(this.state.userid);
		}

		this.setState({
			userid: ""
		});
	};

	render() {
		return (
			<div>
				<h1>Create incident</h1>
				<form onSubmit={this.handleSubmit}>
					<label>
						Name of incident:{" "}
						<input
							name="name"
							type="text"
							value={this.state.name}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Description:{" "}
						<textarea
							name="description"
							value={this.state.description}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Date:{" "}
						<input
							name="time"
							value={this.state.time}
							onChange={this.handleChange}
							type="date"
						/>
					</label>
					<label>Location: NOT AVAILABLE</label>
					<label>
						Severity of incident:
						<select
							name="severity"
							value={this.state.severity}
							onChange={this.handleChange}
						>
							<option value="MINOR">Minor</option>
							<option value="MAJOR">Major</option>
						</select>
					</label>
					<label>
						Users:
						<input
							name="userid"
							type="text"
							value={this.state.userid}
							onChange={this.handleChange}
						/>
						<button onClick={this.adduser}>Add user to incident</button>
						{_.map(this.state.users, (user, index) => {
							return <div key={index}>- {user}</div>;
						})}
					</label>
					---------------------------------------------------------------------------
					<br />
					<input type="submit" value="submit" />
				</form>
				<div>
					<h1>Incidents</h1>
					{_.map(this.state.incidents, item => {
						const date = moment(item.time).format("YYYY-MM-DD");
						return (
							<div key={item.id} className="notification">
								<h2>{item.name}</h2>
								<p>
									<b>Id:</b> {item.id}
								</p>
								<p>
									<b>Description: </b> {item.description}
								</p>
								<p>
									<b>Date: </b>
									{date}
								</p>
								<p>
									<b>Location: </b>
								</p>
								<ul className="flex">
									<li>Name: {item.location.name}</li>
									<li>Latitude: {item.location.xlat}</li>
									<li>longitude: {item.location.xlat}</li>
								</ul>
								<p>
									<b>Severity: </b>
									{item.severity}
								</p>
								<div>
									<p>
										<b>Users:</b>
									</p>
									<ul className="flex">
										{_.map(item.users, (user, index) => {
											return <li key={index}>{user}</li>;
										})}
									</ul>
								</div>
							</div>
						);
					})}
				</div>
			</div>
		);
	}
}

export default Security;
