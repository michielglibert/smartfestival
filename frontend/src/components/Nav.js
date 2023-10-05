import React, { Component } from "react";
import { Link } from "react-router-dom";

export class Nav extends Component {
	render() {
		return (
			<nav>
				<ul>
					<li>
						<Link to="/">Home</Link>
					</li>
					<li>
						<Link to="/security">Security</Link>
					</li>
					<li>
						<Link to="/products">Products</Link>
					</li>
					<li>
						<Link to="/ticket">Tickets</Link>
					</li>
					<li>
						<Link to="/visitor">Visitor</Link>
					</li>
					<li>
						<Link to="/display">Display</Link>
					</li>
					<li>
						<Link to="/lineup">Lineup</Link>
					</li>
					<li>
						<Link to="/notifications">Notifications</Link>
					</li>
					<li>
						<Link to="/test">test page</Link>
					</li>
				</ul>
			</nav>
		);
	}
}

export default Nav;
