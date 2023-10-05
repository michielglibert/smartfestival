import React, { Component } from "react";
import axios from "axios";
import moment from "moment";

export class Display extends Component {
	state = {
		artist: "",
		stage: "",
		start: moment().format("YYYY-MM-DD"),
		end: moment().format("YYYY-MM-DD"),
		timetable: [],
		messages: []
	};

	handleChange = event => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	componentDidMount() {
		axios
			.get(`${process.env.REACT_APP_ENDPOINT}/display/timetable/all`, {
				responseType: "json"
			})
			.then(response => {
				this.setState({ timetable: response.data });
			});
		axios
			.get(`${process.env.REACT_APP_ENDPOINT}/display/messages/all`, {
				responseType: "json"
			})
			.then(response => {
				this.setState({ messages: response.data });
			});
	}

	render() {
		const timeTableData = this.state.timetable.map((item, index) => (
			<tr key={index}>
				<td>{item.artist}</td>
				<td>{item.stage}</td>
				<td>{moment(item.startDateTime).format("YYYY-MM-DD, H:mm:ss")}</td>
				<td>{moment(item.endDateTime).format("YYYY-MM-DD, H:mm:ss")}</td>
			</tr>
		));

		const messageTableData = this.state.messages.map((item, index) => (
			<tr key={index}>
				<td>{item.title}</td>
				<td>{item.message}</td>
				<td>{moment(item.createdDateTime).format("YYYY-MM-DD, H:mm:ss")}</td>
			</tr>
		));
		return (
			<div>
				<div>
					<h1>Timetable</h1>
					<table border="1">
						<thead>
							<tr>
								<th>Artist</th>
								<th>Stage</th>
								<th>Start</th>
								<th>End</th>
							</tr>
						</thead>
						<tbody>{timeTableData}</tbody>
					</table>
					<h1>Messages</h1>
					<table border="1">
						<thead>
							<tr>
								<th>Title</th>
								<th>Message</th>
								<th>Created</th>
							</tr>
						</thead>
						<tbody>{messageTableData}</tbody>
					</table>
				</div>
			</div>
		);
	}
}

export default Display;
