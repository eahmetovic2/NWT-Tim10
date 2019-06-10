import React, { Component } from "react";
import "./UcenikPredmeta.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment'; 

class UcenikPredmeta extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
		
		Moment.locale('en');
		momentLocalizer();
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
		console.log(" UCENIK PREDMETA ENIS");
		const { auth } = this.props;
		console.log(this.props.match.params)
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
		const { ocjena, datum, predmet, ucenik } = this.state;
		const { auth } = this.props;
		const ucenik_id = predmet.id;
		const predmet_id = ucenik.id;
		var headers = {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + auth
		}
		var data = {
			'ocjena':  ocjena,
			'datum':  datum,
			'ucenik_id':  ucenik_id,
			'predmet_id':  predmet_id
	   }
		console.log(ocjena, datum, ucenik_id, predmet_id)
		console.log(auth);
		axios
			.post("/nwtOcjena/ocjena/create", data, {headers: headers})
			.then(response => {
				console.log("OCJENA:",response);
			})
			.catch(error => alert("error: Server error!"));
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
							</BootstrapTable>
						</div>
					</div>
					<div class="razmak"></div>

					<div className="card border">
						<div className="card-header">Dodavanje ocjene</div>
						<div className="card-body text-left">
							<div className="form-group">
								<label htmlFor="exampleInputEmail1">Datum:</label>
								
								<DateTimePicker
									value={this.state.datum}
									onChange={value => this.setState({ datum : value })}
									time={false}
								/>
							</div>
							<div className="form-group">
								<label htmlFor="exampleInputEmail1">Ocjena:</label>
								<input
									type="number"
									name="ocjena"
									onChange={e => this.setState({ ocjena : e.target.value })}
									className="form-control"
									placeholder="Ocjena"
									min = "1"
									max = "10"
								/>
							</div>
							<button className="btn btn-primary" onClick={e => this.handleClick(e)}>
								Dodaj ocjenu
							</button>
						</div>
						
					</div>
					
					<div class="razmak"></div>
				</div>
			</div>
		  </div>
		);
	}
}

export default UcenikPredmeta;
