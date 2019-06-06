import React, { Component } from "react";
import "./pocetnaUcenik.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

class pocetnaUcenik extends Component {
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
