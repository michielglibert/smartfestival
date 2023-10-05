import React from "react";
import "./App.css";
import _ from "lodash";
import { BrowserRouter as Router } from "react-router-dom";
import { Switch, Route } from "react-router-dom";
import routes from "./routes/routes";
import Nav from "./components/Nav";

class App extends React.Component {
	render() {
		return (
			<div className="App">
				<Router>
					<Nav />
					<Switch>
						{_.map(routes, (route, index) => {
							return <Route key={index} {...route} />;
						})}
					</Switch>
				</Router>
			</div>
		);
	}
}

export default App;
