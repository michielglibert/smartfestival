import React, { Component } from "react";
import axios from "axios";
import moment from "moment";
import _ from "lodash";

export class Lineup extends Component {
	state = {
		artist: "",
		stage: "",
		start: moment().format("YYYY-MM-DD"),
		end: moment().format("YYYY-MM-DD"),
		timetable: []
	};

	handleChange = event => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	handleSubmit = async event => {
		event.preventDefault();
		axios
			.post(`${process.env.REACT_APP_ENDPOINT}/lineup/create`, {
				artist: this.state.artist,
				stage: this.state.stage,
				startDateTime: moment(this.state.start),
				endDateTime: moment(this.state.end)
			})
			.then(resp => {
				axios
					.get(`${process.env.REACT_APP_ENDPOINT}/lineup/all`, {
						responseType: "json"
					})
					.then(response => {
						this.setState({ timetable: response.data });
					});
			})
			.catch(err => {
				console.log(err);
			});
	};

	componentDidMount() {
		axios
			.get(`${process.env.REACT_APP_ENDPOINT}/lineup/all`, {
				responseType: "json"
			})
			.then(response => {
				this.setState({ timetable: response.data });
			});
	}

	removeItem = item => {
		axios
			.delete(`${process.env.REACT_APP_ENDPOINT}/lineup/delete/${item.id}`)
			.then(resp => {
				let timetablearr = this.state.timetable;
				_.remove(timetablearr, r => r.id === item.id);
				this.setState({ timetable: timetablearr });
			})
			.catch(err => {
				console.log(err);
			});
	};

	render() {
		const tableData = this.state.timetable.map((item, index) => (
			<tr key={index}>
				<td>{item.artist}</td>
				<td>{item.stage}</td>
				<td>{item.startDateTime}</td>
				<td>{item.endDateTime}</td>
				<td>
					<button className="knop" onClick={() => this.removeItem(item)}>
						Delete
					</button>
				</td>
			</tr>
		));
		return (
			<div>
				<div>
					<h1>Create timeslot</h1>
					<form onSubmit={this.handleSubmit}>
						<label>
							Artist:{" "}
							<input
								name="artist"
								type="text"
								value={this.state.artist}
								onChange={this.handleChange}
							/>
						</label>
						<label>
							Stage:{" "}
							<input
								name="stage"
								type="text"
								value={this.state.stage}
								onChange={this.handleChange}
							/>
						</label>
						<label>
							Start:{" "}
							<input
								name="start"
								value={this.state.start}
								onChange={this.handleChange}
								type="date"
							/>
						</label>
						<label>
							End:{" "}
							<input
								name="end"
								value={this.state.end}
								onChange={this.handleChange}
								type="date"
							/>
						</label>
						<br />
						<input type="submit" value="submit" />
					</form>
				</div>
				<div>
					<h1>Timetable</h1>
					<table border="1">
						<thead>
							<tr>
								<th>Artist</th>
								<th>Stage</th>
								<th>Start</th>
								<th>End</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>{tableData}</tbody>
					</table>
				</div>
			</div>
		);
	}
}

export default Lineup;
