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
			.catch(error => alert("error: Bad credentials!"));
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
			<div>
				<input
					type="text"
					name="username"
					placeholder="Username"
					onChange={e => this.handleInput(e)}
				/>

				<input
					type="password"
					name="password"
					placeholder="Password"
					onChange={e => this.handleInput(e)}
				/>

				<button onClick={e => this.handleClick(e)}>LOGIN</button>
			</div>
		);
	}
}

export default Login;
