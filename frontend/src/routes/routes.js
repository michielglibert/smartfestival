import Home from "../components/Home";
import Security from "../components/Security";
import Test from "../components/Test";
import Notifications from "../components/Notifications";
import Lineup from "../components/Lineup";
import Display from "../components/Display";
import Products from "../components/Products";
import Ticket from "../components/Ticket";
import Visitor from "../components/Visitor";

const routes = [
	{
		exact: true,
		path: "/",
		component: Home
	},
	{
		exact: true,
		path: "/security",
		component: Security
	},
	{
		exact: true,
		path: "/test",
		component: Test
	},
	{
		exact: true,
		path: "/notifications",
		component: Notifications
	},
	{
		exact: true,
		path: "/lineup",
		component: Lineup
	},
	{
		exact: true,
		path: "/display",
		component: Display
	},
	{
		exact: true,
		path: "/products",
		component: Products
	},
	{
		path: "/ticket",
		component: Ticket
	},
	{
		exact: true,
		path: "/visitor",
		component: Visitor
	}
];

export default routes;
