import React, { Component } from "react";
import axios from "axios";
import _ from "lodash";

export class Notifications extends Component {
	state = {
		notifications: [],
		title: "",
		content: "",
		priority: "LOW",
		notificationId: "",
		reply: "",
		displaytitle: "",
		displaymessage: "",
		displaypriority: 0
	};

	componentDidMount() {
		this.getNotifications();
	}

	getNotifications = async () => {
		const res = await axios.get(
			`${process.env.REACT_APP_ENDPOINT}/notification/all`
		);
		const { data } = res;
		this.setState({ notifications: data });
	};

	handleChange = event => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	handleSubmit = async event => {
		event.preventDefault();
		axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/notification/send_security_notification`,
				{
					title: this.state.title,
					content: this.state.content,
					priority: this.state.priority
				}
			)
			.then(resp => {
				console.log(resp);
			})
			.catch(err => {
				console.log(err);
			});
	};

	handleOtherSubmit = async event => {
		event.preventDefault();
		axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/notification/reply_to_security_notfication/${this.state.notificationId}`,
				{
					reply: this.state.reply
				}
			)
			.then(resp => {
				console.log(resp);
			})
			.catch(err => {
				console.log(err);
			});
	};

	handleDisplaySubmit = async event => {
		event.preventDefault();
		axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/notification/send_display_message`,
				{
					title: this.state.displaytitle,
					message: this.state.displaymessage,
					priority: this.state.displaypriority
				}
			)
			.then(resp => {
				console.log(resp);
			})
			.catch(err => {
				console.log(err);
			});
	};

	render() {
		return (
			<div>
				<h1>Notifications</h1>
				<h2>Send display message</h2>
				<form onSubmit={this.handleDisplaySubmit}>
					<label>
						Title:{" "}
						<input
							name="displaytitle"
							type="text"
							value={this.state.displaytitle}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Message:{" "}
						<textarea
							name="displaymessage"
							value={this.state.displaymessage}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Priority:{" "}
						<input
							name="displaypriority"
							type="number"
							value={this.state.displaypriority}
							onChange={this.handleChange}
						/>
					</label>
					---------------------------------------------------------------------------
					<br />
					<input type="submit" value="send message" />
				</form>
				<h2>Send security Notification</h2>
				<form onSubmit={this.handleSubmit}>
					<label>
						Title:{" "}
						<input
							name="title"
							type="text"
							value={this.state.title}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Content:{" "}
						<textarea
							name="content"
							value={this.state.content}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Priority of notification:
						<select
							name="priority"
							value={this.state.priority}
							onChange={this.handleChange}
						>
							<option value="LOW">Low</option>
							<option value="MEDIUM">Medium</option>
							<option value="HIGH">High</option>
						</select>
					</label>
					---------------------------------------------------------------------------
					<br />
					<input type="submit" value="send notification" />
				</form>
				<h2>Reply to notification as security agent</h2>
				<form onSubmit={this.handleOtherSubmit}>
					<label>
						ID:{" "}
						<input
							name="notificationId"
							type="text"
							value={this.state.notificationId}
							onChange={this.handleChange}
						/>
					</label>
					<label>
						Reply:{" "}
						<textarea
							name="reply"
							value={this.state.reply}
							onChange={this.handleChange}
						/>
					</label>
					---------------------------------------------------------------------------
					<br />
					<input type="submit" value="reply to notification" />
				</form>
				<h1>All notifications:</h1>
				<ul>
					{_.map(this.state.notifications, item => {
						return (
							<li key={item.id} className="notification">
								<h3>{item.title}</h3>
								<p>
									<b>id:</b> {item.id}
								</p>
								<p>
									{" "}
									<b>content:</b>
									{item.content}
								</p>
								{item.reply !== null && (
									<p>
										<b>reply:</b>
										{item.reply}
									</p>
								)}
							</li>
						);
					})}
				</ul>
			</div>
		);
	}
}

export default Notifications;
