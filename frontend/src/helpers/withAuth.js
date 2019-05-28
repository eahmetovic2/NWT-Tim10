import React, { Component } from "react";
import { Redirect } from "react-router-dom";

export default function withAuth(ProtectedComponent) {
	return class extends Component {
		constructor() {
			super();
			this.state = {
				loading: true,
				redirect: false,
				token: ""
			};
		}

		componentDidMount() {
			const token = localStorage.getItem("token");
			this.setState({ token });
			token
				? this.setState({ loading: false })
				: this.setState({ loading: false, redirect: true });
		}

		render() {
			const { loading, redirect, token } = this.state;
			if (loading) {
				return null;
			}
			if (redirect) {
				return <Redirect to="/login" />;
			}
			return <ProtectedComponent {...this.props} auth={token} />;
		}
	};
}
