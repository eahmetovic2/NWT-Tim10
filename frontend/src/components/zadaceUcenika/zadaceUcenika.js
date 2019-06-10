import React, { Component } from "react";
import "./zadaceUcenika.css";
import axios from "axios";
import jwt from 'jsonwebtoken'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";

class zadaceUcenika extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
	  }
	state = {
		predmeti: [],
		redirect: false,
		predmet: {}
	};
	componentDidMount() {
		this.props = this.props.props;
		
		const { auth } = this.props;
		var token = auth;
		token = token.replace('Bearer ','');
		var decoded = jwt.decode(token);
		console.log(decoded);
		axios
			.get("/nwtOcjena/predmeti/nastavnik/1", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ predmeti: response.data }))
			.catch(err => console.log(err));

	};
	
	render() {
		return (
			<div>
				<h2>Zadace</h2>
			<BootstrapTable data={ this.state.predmeti } >
				<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
				<TableHeaderColumn dataField='naziv'>Naziv</TableHeaderColumn>
			</BootstrapTable>
			</div>
		);
	}
}

export default zadaceUcenika;
