import React, { Component } from "react";
import axios from "axios";
import _ from "lodash";

export class Test extends Component {
	state = {
		posts: []
	};

	componentDidMount() {
		this.getPosts();
	}

	getPosts = async () => {
		const res = await axios.get("https://jsonplaceholder.typicode.com/posts");
		const { data } = res;
		this.setState({ posts: data });
	};
	render() {
		const { posts } = this.state;
		return (
			<div>
				<h1>Het volgende werd opgehaald met een API call:</h1>
				<ul>
					{_.map(posts, obj => {
						return (
							<li>
								<b>{obj.id}</b>
								<h3>{obj.title}</h3>
								<p>{obj.body}</p>
							</li>
						);
					})}
				</ul>
			</div>
		);
	}
}

export default Test;
