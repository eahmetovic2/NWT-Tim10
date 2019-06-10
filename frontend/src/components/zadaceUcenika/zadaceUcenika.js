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
		predmet: {},
		ucenik:{},
		redirect: false,
		predmet: {},
		zadace:[]
	};

	componentDidMount() {
		//this.props = this.props.props;
		const { auth } = this.props;
		var token = auth;
		token = token.replace('Bearer ','');
		var decoded = jwt.decode(token);
		axios
			.get("/nwtOcjena/predmet/"+ this.props.match.params.predmetId, {
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
				.get("/nwtOcjena/ucenik/"+ this.props.match.params.ucenikId, {
					headers: {
						Authorization: "Bearer " + auth
					}
				})
				.then(response => 
					{
						var tmp = [];
						this.setState({ ucenik: response.data });
					})
				.catch(err => console.log(err));

		axios
			.get("/nwtUpload/bodoviZadace", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => 
				{
					
					//this.setState({ zadace: response.data });
					var zad_temp=[];
					response.data.forEach(element => {
						console.log("ELEMENT:",element);
						if(element.predmetId==this.state.predmet.predmetId)
							zad_temp.push(element.zadaca);
					});
					this.setState({zadace:zad_temp});
				})
			.catch(err => console.log(err));

			
	};
	
	render() {
		console.log("DRUGI:",this);
		return (
			<div>
				<h2>Zadace</h2>
			<BootstrapTable data={ this.state.zadace } >
				<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
				<TableHeaderColumn dataField='naziv' >Naziv</TableHeaderColumn>
				<TableHeaderColumn dataField='datumIsteka'>Datum isteka</TableHeaderColumn>
			</BootstrapTable>
			</div>
		);
	}
}

export default zadaceUcenika;
