import React, { Component } from "react";
import "./ListaUcenikaPredmeta.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

class ListaUcenikaPredmeta extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
	  }
	state = {
		uceniciPredmeta: [],
		ucenici: [],
		predmet: {}
	};
	componentDidMount() {
		const { auth } = this.props;
		axios
			.get("/nwtOcjena/predmet/"+ this.props.match.params.predmetID, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => 
				{
					var tmp = [];
					this.setState({ predmet: response.data });
				})
			.catch(err => console.log(err));
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
	routeChange = (row) => {
		let path = "/predmet/" + this.props.match.params.predmetID + "/ucenik/" + row.id;
		//this.props = this.props.props;
		this.props.history.push(path);
		console.log(this.props)
		
	  };
	options = {
		onRowClick: 		
			this.routeChange
		
	}
	render() {
		return (
			<div className="container">
				<h1>{this.state.predmet.naziv}</h1>
				<h3>Učenici predmeta</h3>
				<BootstrapTable data={ this.state.ucenici } options={ this.options }>
					<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
					<TableHeaderColumn dataField='ime'>Ime</TableHeaderColumn>
					<TableHeaderColumn dataField='prezime'>Prezime</TableHeaderColumn>
				</BootstrapTable>
			</div>
		);
	}
}

export default ListaUcenikaPredmeta;
