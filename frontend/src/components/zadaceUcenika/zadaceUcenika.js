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
		Bodovi_zadace:[],
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
					var zad_tempb=[];
					response.data.forEach(element => {
						if(element.predmetId==this.state.predmet.predmetId)
						{
							//element.zadaca.upload = "Upload";
							zad_tempb.push(element);
							zad_tempb.push(element.zadaca);
						}
							
					});
					this.setState({Bodovi_zadace:zad_tempb});
					this.setState({zadace:zad_temp});
				})
			.catch(err => console.log(err));
		axios
			.get("/nwtUpload/predmetOcjena/"+this.props.match.params.predmetId, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => 
				{
					this.setState({ zadace : response.data });
					this.state.zadace.forEach(function(element) { element.bodovi = null; });
					axios
					.get("/nwtUpload/bodoviZadace/ucenik/"+this.props.match.params.ucenikId, {
						headers: {
							Authorization: "Bearer " + auth
						}
					})
					.then(response => {
						let novi = this.state.zadace;
						console.log(response.data)
						let zadacaIds = novi.map(zadaca => zadaca.id);
						let zadace = response.data.filter(el => ~zadacaIds.indexOf(el.zadaca.id))
						.reduce((zadace,el) => {
							zadace[el.zadaca.id] = el.bodovi
							return zadace;
						},{});
						novi.map(zadaca => {
							if(zadace[zadaca.id]){
								zadaca["bodovi"] = zadace[zadaca.id]
							}
							return zadaca;
						})
						this.setState({zadace: novi});
							
					});
				});
			}
		
	

	routeChange = (row) => {
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
		
	};

	prikaz(cell, row) {
		if(row.webContentLink != null) {
			return <a href={row.webContentLink} target="_blank">Download</a>;
		}
		return null; 
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
			<BootstrapTable data={ this.state.zadace } >
				<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
				<TableHeaderColumn dataField='naziv' >Naziv</TableHeaderColumn>
				<TableHeaderColumn dataField='datumIsteka'>Datum isteka</TableHeaderColumn>
				<TableHeaderColumn dataFormat={this.prikaz}>Download</TableHeaderColumn>
				<TableHeaderColumn dataField='bodovi'>Bodovi</TableHeaderColumn>
			</BootstrapTable>
			</div>
		);
	}
}

export default zadaceUcenika;
