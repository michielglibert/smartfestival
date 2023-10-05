import React, { Component } from "react";
import axios from "axios";
import _ from "lodash";

export class Products extends Component {
	state = {
		balances: [],
		orders: [],
		products: [],
		amount: "",
		visitorIdAddBalance: "",
		orderId: "",
		visitorIdPayOrder: "",
		order: [],
		orderTotal: 0,
		visitorIdOrder: "",
		productIdToAdd: "",
		orderIdToAdd: "",
		productIdToDelete: "",
		orderIdToDeleteFrom: "",
		orderIdToDelete: ""
	};

	componentDidMount() {
		this.getBalances();
		this.getOrders();
		this.getProducts();
	}

	getBalances = async () => {
		const res = await axios.get(
			`${process.env.REACT_APP_ENDPOINT}/balance/all`
		);
		const { data } = res;
		this.setState({ balances: data });
	};

	getOrders = async () => {
		const res = await axios.get(
			`${process.env.REACT_APP_ENDPOINT}/product/allOrders`
		);
		const { data } = res;
		this.setState({ orders: data });
	};

	getProducts = async () => {
		const res = await axios.get(
			`${process.env.REACT_APP_ENDPOINT}/product/allProducts`
		);
		const { data } = res;
		this.setState({ products: data });
	};

	handleChange = event => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	addBalance = async event => {
		event.preventDefault();
		await axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/balance/addCreditToBalance/${this.state.amount}/${this.state.visitorIdAddBalance}`
			)
			.then(response => {
				console.log(response);
				this.getBalances();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	payOrder = async event => {
		event.preventDefault();
		await axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/product/payOrder/${this.state.orderId}/${this.state.visitorIdPayOrder}`
			)
			.then(response => {
				console.log(response);
				this.getBalances();
				this.getOrders();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	addProduct(product) {
		this.state.order.push(product);
		const orderTotal = _.round(this.state.orderTotal + product.price, 2);
		this.setState({ orderTotal: orderTotal });
		console.log(this.state.order);
	}

	deleteProduct(product, index) {
		// this.setState({
		// 	order: _.without(
		// 		this.state.order,
		// 		_.find(this.state.order, { name: product.name })
		// 	)
		// });

		const orderTotal = _.round(this.state.orderTotal - product.price, 2);
		this.state.order.splice(index, 1);
		this.setState({ orderTotal: orderTotal });
		console.log(this.state.order);
	}

	addProductToOrder = async event => {
		event.preventDefault();
		await axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/product/addProduct/${this.state.productIdToAdd}/${this.state.orderIdToAdd}`
			)
			.then(response => {
				console.log(response);
				this.getOrders();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	deleteProductFromOrder = async event => {
		event.preventDefault();
		await axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/product/removeProduct/${this.state.productIdToDelete}/${this.state.orderIdToDeleteFrom}`
			)
			.then(response => {
				console.log(response);
				this.getOrders();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	order = async event => {
		event.preventDefault();
		await axios
			.post(`${process.env.REACT_APP_ENDPOINT}/product/createProductOrder`, {
				products: this.state.order,
				visitorId: this.state.visitorIdOrder,
				total: this.state.orderTotal,
				festivalStand: {
					id: "1",
					name: "Joe's burgers"
				}
			})
			.then(response => {
				console.log(response);
				this.setState({ order: [] });
				this.getBalances();
				this.getOrders();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	deleteOrder = async event => {
		event.preventDefault();
		await axios
			.post(
				`${process.env.REACT_APP_ENDPOINT}/product/cancelProductOrder/${this.state.orderIdToDelete}`
			)
			.then(response => {
				console.log(response);
				this.getBalances();
				this.getOrders();
			})
			.catch(err => {
				console.log("yikes" + err);
			});
	};

	render() {
		const balanceData = this.state.balances
			.sort((a, b) => (a.visitorId > b.visitorId ? 1 : -1))
			.map((balance, index) => (
				<tr key={index}>
					<td>{balance.visitorId}</td>
					<td>{balance.balance}</td>
				</tr>
			));
		const orderData = this.state.orders.map((order, index) => (
			<tr key={index}>
				<td>{order.id}</td>
				<td>{order.visitorId}</td>
				<td>
					{order.products.map((product, index) => {
						return (
							<span key={index}>
								{product.name} (€ {product.price}), <br />
							</span>
						);
					})}
				</td>
				<td>€ {order.total}</td>
				<td>{order.status}</td>
			</tr>
		));
		const productData = this.state.products.map((product, index) => (
			<tr key={index}>
				<td>{product.id}</td>
				<td>{product.name}</td>
				<td>{product.price}</td>
				<td>
					<button onClick={() => this.addProduct(product)}>+</button>
				</td>
			</tr>
		));
		return (
			<div className="prodbal-wrapper">
				<div className="balance">
					<h2>Balances</h2>
					<table border="1">
						<thead>
							<tr>
								<th>VisitorId</th>
								<th>AvailableBalance</th>
							</tr>
						</thead>
						<tbody>{balanceData}</tbody>
					</table>
					<h2>BalanceOperations</h2>
					<table border="1">
						<thead>
							<tr>
								<th>AddBalance (amount, visitorId)</th>
								<th>PayOrder (orderId)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<form onSubmit={this.addBalance}>
										€
										<input
											placeholder="50"
											name="amount"
											type="number"
											value={this.state.amount}
											onChange={this.handleChange}
										/>
										<input
											placeholder="visitorId"
											name="visitorIdAddBalance"
											type="text"
											value={this.state.visitorIdAddBalance}
											onChange={this.handleChange}
										/>
										<input type="submit" value="Add" />
									</form>
								</td>
								<td>
									<form onSubmit={this.payOrder}>
										<input
											placeholder="orderId"
											name="orderId"
											type="text"
											value={this.state.orderId}
											onChange={this.handleChange}
										/>
										<input
											placeholder="visitorId"
											name="visitorIdPayOrder"
											type="text"
											value={this.state.visitorIdPayOrder}
											onChange={this.handleChange}
										/>
										<input type="submit" value="Pay" />
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div className="products">
					<hr />
					<h2>Orders</h2>
					<table border="1">
						<thead>
							<tr>
								<th>OrderID</th>
								<th>VisitorID</th>
								<th>Products</th>
								<th>Total</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>{orderData}</tbody>
					</table>
					<form onSubmit={this.deleteOrder}>
						<label>Delete Order</label>
						<input
							placeholder="orderId"
							name="orderIdToDelete"
							type="text"
							value={this.state.prderIdToDelete}
							onChange={this.handleChange}
						/>
						<input type="submit" value="Delete" />
					</form>
					<h2>Products</h2>
					<table border="1">
						<thead>
							<tr>
								<th>ProductID</th>
								<th>Name</th>
								<th>Price (€)</th>
								<th>Add</th>
							</tr>
						</thead>
						<tbody>{productData}</tbody>
					</table>
					<span>
						<form onSubmit={this.order}>
							<input
								placeholder="visitorId"
								name="visitorIdOrder"
								type="text"
								value={this.state.visitorIdOrder}
								onChange={this.handleChange}
							/>
							<input type="submit" value="Order" />
						</form>
					</span>
					<h2>Products in current order</h2>
					{_.isEmpty(this.state.order) ? (
						<div></div>
					) : (
						<table className="tabelleke" border="1">
							<thead>
								<tr>
									<th>Product</th>
									<th>Remove</th>
								</tr>
							</thead>
							{_.map(this.state.order, (item, index) => {
								return (
									<tr>
										<td>{item.name}</td>
										<td>
											<button onClick={() => this.deleteProduct(item, index)}>
												-
											</button>
										</td>
									</tr>
								);
							})}
						</table>
					)}
					<p>Total: €{this.state.orderTotal}</p>
					<h2>Product-Order Operations</h2>
					<span>
						<label>Add Product To Order</label>
						<form onSubmit={this.addProductToOrder}>
							<input
								placeholder="productId to add"
								name="productIdToAdd"
								type="text"
								value={this.state.productIdForAdd}
								onChange={this.handleChange}
							/>
							<input
								placeholder="orderId to add to"
								name="orderIdToAdd"
								type="text"
								value={this.state.orderIdForAdd}
								onChange={this.handleChange}
							/>
							<input type="submit" value="Add" />
						</form>
						<label>Delete Product From Order</label>
						<form onSubmit={this.deleteProductFromOrder}>
							<input
								placeholder="productId to remove"
								name="productIdToDelete"
								type="text"
								value={this.state.productIdToDelete}
								onChange={this.handleChange}
							/>
							<input
								placeholder="orderId to delete from"
								name="orderIdToDeleteFrom"
								type="text"
								value={this.state.orderIdToDeleteFrom}
								onChange={this.handleChange}
							/>
							<input type="submit" value="Delete" />
						</form>
					</span>
				</div>
			</div>
		);
	}
}

export default Products;
