import React, { Component } from "react";
import "./ListaUcenikaPredmeta.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

class ListaUcenikaPredmeta extends Component {
	state = {
		uceniciPredmeta: [],
		ucenici: []
	};
	componentDidMount() {
		const { auth } = this.props;
		console.log(this.props.match.params.predmetID);
		axios
			.get("/nwtOcjena/ucenik-predmeta/predmet/"+ this.props.match.params.predmetID, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => 
				{
					var tmp = [];
					this.setState({ uceniciPredmeta: response.data });
					
					this.state.uceniciPredmeta.forEach((element) => {
						tmp.push(element.ucenik);
					  });
					this.setState({ ucenici: tmp });
				})
			.catch(err => console.log(err));
	}
	render() {
		console.log("RENDER",this.state)
		return (
			<div className="container">

				<h3>Učenici predmeta</h3>
				<BootstrapTable data={ this.state.ucenici }>
					<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
					<TableHeaderColumn dataField='ime'>Ime</TableHeaderColumn>
					<TableHeaderColumn dataField='prezime'>Prezime</TableHeaderColumn>
				</BootstrapTable>
			</div>
		);
	}
}

export default ListaUcenikaPredmeta;
