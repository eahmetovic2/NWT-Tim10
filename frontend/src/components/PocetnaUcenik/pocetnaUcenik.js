import React, { Component } from "react";
import "./pocetnaUcenik.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";

class pocetnaUcenik extends Component {
	state = {
		ucenici: []
	};

	componentDidMount() {
		this.props = this.props.props;
		console.log("PocetnaNastavnik:", this.props);
		
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

	routeChange = (row) => {
		let path = "/ucenici/predmet/" + row.id;
		this.state.predmet = row;
		this.state.redirect = true;
		this.props = this.props.props;
		this.props.history.push(path);
		console.log(this.props)
	  };
	options = {
		onRowClick: 		
			this.routeChange
		
	};
	
	render() {
		return (
			<div className="container">
				<BootstrapTable data={ this.state.ucenici }>
					<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
					<TableHeaderColumn dataField='ime'>Ime</TableHeaderColumn>
					<TableHeaderColumn dataField='prezime'>Prezime</TableHeaderColumn>
				</BootstrapTable>
			</div>
		);
	}
}

export default pocetnaUcenik;
