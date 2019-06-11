import React, { Component } from "react";
import "./predmetUcenika.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment'; 

class predmetUcenika extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
		
	  }
	state = {
		predmet: {},
		ucenik: {},
		ocjene: [],
		izostanci: [],
		datum : new Date(),
		ocjena: null
	};
	
	componentDidMount() {



		const { auth } = this.props;
		console.log("PREDMETI UCENIKA HAMZA");
		//console.log(this.props.match.params)
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
			.get("/nwtOcjena/ocjena/ucenik/" + this.props.match.params.ucenikId + "/predmet/"+ this.props.match.params.predmetId, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => 
				{
					var tmp = [];
					this.setState({ ocjene: response.data });
				})
			.catch(err => console.log(err));
			
		axios
		.get("/nwtIzostanak/izostanak/ucenik/" + this.props.match.params.ucenikId + "/predmet/"+ this.props.match.params.predmetId, {
			headers: {
				Authorization: "Bearer " + auth
			}
		})
		.then(response => 
			{
				var tmp = [];
				this.setState({ izostanci: response.data });
			})
		.catch(err => console.log(err));
	}
	prisutanFormatter(cell, row) {
		console.log(row)
		if(row.prisutan == true)
			return `DA`;			
		else if(row.opravdan == true)
			return `OPRAVDAN`;	
		else 
			return `NEOPRAVDAN`;
	}

	handleClick(e) {
		console.log(this.props);
		let path = "/zadaceUcenika/" +this.state.ucenik.id+ "/predmet/" +  this.props.match.params.predmetId;
		//this.props = this.props.props;
		console.log(this.props);
		this.props.history.push(path);
		
	}

	handleClick2(e) {
		let path = "/materijaliPregled/" + this.props.predmet;
		this.props = this.props.props;
		this.props.history.push(path);
	}
	
	render() {
		
		Moment.locale('en');
		momentLocalizer();
		return (
			<div className="container">
			<div className="row">
				<div className="col-12">
				<div className="card border">
						<div className="card-header">Osnovne informacije</div>
						<div className="card-body text-left">
							<h1>{this.state.ucenik.ime + " " + this.state.ucenik.prezime}</h1>
							<h2>{this.state.predmet.naziv}</h2>
							
							<div class="razmak"></div>
							<h3>Prisustvo</h3>
							<BootstrapTable data={ this.state.izostanci } >
								<TableHeaderColumn dataField='datum' isKey>Datum</TableHeaderColumn>
								<TableHeaderColumn dataField='prisutan' dataFormat={this.prisutanFormatter}>Prisutan</TableHeaderColumn>
							</BootstrapTable>
							<div class="razmak"></div>
							<h3>Ocjene</h3>
							<BootstrapTable data={ this.state.ocjene } >
								<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
								<TableHeaderColumn dataField='datum'>Datum</TableHeaderColumn>
								<TableHeaderColumn dataField='ocjena'>Ocjena</TableHeaderColumn>
								<TableHeaderColumn>Upload</TableHeaderColumn>
							</BootstrapTable>
						</div>
					</div>
					<div class="razmak"></div>
					<button className="btn btn-primary" onClick={e => this.handleClick(e)}>
								Zadace
					</button>
					<div class="razmak"></div>
					<button className="btn btn-primary" onClick={e => this.handleClick2(e)}>
								Materijali
					</button>
				</div>
			</div>
		  </div>
		);
	}
}

export default predmetUcenika;
