import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import "./Login.css";
import axios from "axios";

class Login extends Component {
	state = {
		username: "",
		password: "",
		redirect: false
	};
	componentDidMount() {
		if (localStorage.getItem("token")) {
			this.setState({ redirect: true });
		}
	}

	handleClick(e) {
		const { username, password } = this.state;
		console.log(username);
		console.log(password);
		axios
			.post("/auth", {
				username,
				password
			})
			.then(response => {
				if (response.status === 200) {
					localStorage.setItem(
						"token",
						response.headers.authorization
					);
					this.setState({ redirect: true });
				} else {
					alert("error: Bad credentials!");
				}
			})
			.catch(error => alert("error: Server error!"));
	}

	handleInput(e) {
		this.setState({
			[e.target.name]: e.target.value
		});
	}
	render() {
		const { username, password, redirect } = this.state;
		if (redirect) {
			return <Redirect to="/" />;
		}
		return (
			<div className="container">
				<div className="row">
					<div className="col-12">
						<div className="card border-primary">
							<div className="card-header">Login</div>
							<div className="card-body text-left">
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Username:</label>
									<input
										type="text"
										name="username"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Username"
									/>
								</div>
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Password:</label>
									<input
										type="password"
										name="password"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Password"
									/>
								</div>
								<button className="btn btn-primary" onClick={e => this.handleClick(e)}>
									LOGIN
								</button>
							</div>
						</div>
					</div>
				</div>
      		</div>
		);
	}
}

export default Login;
