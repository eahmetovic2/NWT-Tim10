import React, { Component } from "react";
import "./pocetnaUcenik.css";
import axios from "axios";
import jwt from 'jsonwebtoken';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";

class PocetnaUcenik extends Component {
	state = {
		predmeti: [],
		redirect: false,
		ucenik: {}
	};

	componentDidMount() {
		this.props = this.props.props;
		const { auth } = this.props;
		var token = auth;
		token = token.replace('Bearer ','');
		var decoded = jwt.decode(token);
		axios
			.get("/nwtOcjena/ucenik-predmeta/ucenik/1", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(
				(response) => {
					var pr=[];
					response.data.forEach((element) => {
						pr.push(element.predmet);
					});
					this.setState({predmeti: pr});
				}
			)
			.catch(err => console.log(err));
	}

	routeChange = (row) => {
		let path = "/ucenik/" + 1 + "/predmet/" + row.id;
		this.props = this.props.props;
		this.props.history.push(path);
		console.log(this.props)
		
	  };
	options = {
		onRowClick: 		
			this.routeChange
		
	}
	
	render() {
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

export default PocetnaUcenik;
