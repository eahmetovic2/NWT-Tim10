import React, { Component } from "react";
import "./Dashboard.css";
import PocetnaNastavnik from '../../components/pocetnaNastavnik/pocetnaNastavnik';
import PocetnaUcenik from '../../components/pocetnaUcenik/pocetnaUcenik';
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

class Dashboard extends Component {
	state = {
		ucenici: []
	};
	componentDidMount() {
		console.log("DASHBOARD:", this.props);
		const { auth, username, role, natavnikId, ucenikId } = this.props;
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
			<div className="container">
				<PocetnaNastavnik props={this.props}/>
				<div class="razmak"></div>
				<h2>Učenici</h2>
				<PocetnaUcenik props={this.props}/>
			</div>
		);
	}
}

export default Dashboard;
