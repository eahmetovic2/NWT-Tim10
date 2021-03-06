import React, { Component } from "react";
import "./ZadacePredmeta.css";
import axios from "axios";
import jwt from 'jsonwebtoken'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";

class ZadacePredmeta extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
	  }
	state = {
		zadace: [],
		predmet: {}
	};
	componentDidMount() {
		
		const { auth, predmet } = this.props;
		this.state.predmet = predmet;
		this.setState({predmet: predmet});
		console.log("ZADACE",this.state)
		axios
			.get("/nwtUpload/predmetOcjena/" + this.state.predmet, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ zadace: response.data }))
			.catch(err => console.log(err));

	};
	// webContentLink
	prikaz(cell, row) {
		if(row.webContentLink != null)
			return <a href={row.webContentLink} target="_blank">Download</a>;
		return null;
	}
	render() {
		return (
			<div>
				<BootstrapTable data={ this.state.zadace } >
					<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
					<TableHeaderColumn dataField='naziv'>Naziv</TableHeaderColumn>
					<TableHeaderColumn dataField='datumIsteka'>Datum isteka</TableHeaderColumn>
					<TableHeaderColumn dataFormat={this.prikaz}>Download</TableHeaderColumn>
				</BootstrapTable>
			</div>
		);
	}
}

export default ZadacePredmeta;
