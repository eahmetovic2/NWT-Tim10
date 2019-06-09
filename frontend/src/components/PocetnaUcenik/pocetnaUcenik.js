import React, { Component } from "react";
import "./pocetnaUcenik.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";

class pocetnaUcenik extends Component {
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
		axios
			.get("/ucenik-predmeta/ucenik/1", {
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
	  };
	options = {
		onRowClick: 		
			this.routeChange
		
	};
	
	render() {
		const { redirect, predmet } = this.state;
		if (redirect) {
			return <Redirect to={"/ucenici/predmet/" + predmet.id} />;
		}
		return (
			<div>
				<h2>Predmeti</h2>
			<BootstrapTable data={ this.state.predmeti } options={ this.options }>
				<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
				<TableHeaderColumn dataField='naziv'>Naziv</TableHeaderColumn>
			</BootstrapTable>
			</div>
		);
	}
}

export default pocetnaUcenik;
