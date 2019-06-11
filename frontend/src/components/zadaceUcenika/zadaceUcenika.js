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
					var zad_temp=[];
					response.data.forEach(element => {
						console.log("ELEMENT:",element);
						if(element.predmetId==this.state.predmet.predmetId)
						{
							element.zadaca.upload = "Upload";
							zad_temp.push(element.zadaca);
						}
							
					});
					this.setState({zadace:zad_temp});
				})
			.catch(err => console.log(err));

			
	};

	routeChange = (row) => {
		console.log(row.datumIsteka);
		if (new Date(row.datumIsteka) > new Date()) {
			/*
			console.log(this);
			let path ="zadaca/dodaj/ucenik/"+this.state.ucenik.id+"/predmet/" + this.state.predmet.id;
			this.state.predmet = row;
			this.state.redirect = true;
			this.props = this.props.items;
			this.props.history.push(path);*/
		}
	};
	options = {
		onRowClick: 		
			this.routeChange
		
	}

	buttonFormatter(){
		return '<BootstrapButton type="submit"></BootstrapButton>';
	}
	//dodajZadacu(e) {
	//	this.setState({redirect: true});
	//}
	render() {		
		//const { redirect, predmet } = this.state;
		//if (redirect) {
		//	return <Redirect to={"/zadaca/dodaj/"+predmet.id} />;
		//}
		return (
			<div>
				<h2>Zadace</h2>
			<BootstrapTable data={ this.state.zadace } options={ this.options } >
				<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
				<TableHeaderColumn dataField='naziv' >Naziv</TableHeaderColumn>
				<TableHeaderColumn dataField='datumIsteka'>Datum isteka</TableHeaderColumn>
				<TableHeaderColumn dataField='upload'>Upload</TableHeaderColumn>
			</BootstrapTable>
			</div>
		);
	}
}

export default zadaceUcenika;
