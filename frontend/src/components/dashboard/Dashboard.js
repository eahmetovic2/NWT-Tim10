import React, { Component } from "react";
import "./Dashboard.css";
import axios from "axios";

class Dashboard extends Component {
	state = {
		ucenici: []
	};
	componentDidMount() {
		const { auth } = this.props;
		axios
			.get("/nwtUpload/ucenici", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ ucenici: response.data }))
			.catch(err => console.log(err));
	}
	render() {
		return (
			<div>
				<pre>
					<code>{JSON.stringify(this.state.ucenici, null, 2)}</code>
				</pre>
			</div>
		);
	}
}

export default Dashboard;
