import React, { Component } from "react";
import "./ListaUcenikaPredmeta.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import ZadacePredmeta from '../../components/zadacePredmeta/ZadacePredmeta';
import { Redirect } from "react-router-dom";

class ListaUcenikaPredmeta extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
		this.dodajZadacu = this.dodajZadacu.bind(this);
	  }
	state = {
		uceniciPredmeta: [],
		ucenici: [],
		predmet: {},
		redirect: false
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
	dodajZadacu(e) {
		this.setState({redirect: true});
	}
	render() {		
		const { redirect, predmet } = this.state;
		if (redirect) {
			return <Redirect to={"/zadaca/dodaj/"+predmet.id} />;
		}
		return (
			<div className="container">
				<div className="row">
					<div className="col-12">						
						<h1>{this.state.predmet.naziv}</h1>
						<div className="card border">
							<div className="card-header">Učenici predmeta</div>
							<div className="card-body text-left">								
								<BootstrapTable data={ this.state.ucenici } options={ this.options }>
									<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
									<TableHeaderColumn dataField='ime'>Ime</TableHeaderColumn>
									<TableHeaderColumn dataField='prezime'>Prezime</TableHeaderColumn>
								</BootstrapTable>
							</div>
						</div>
						<div className="razmak"></div>
						<div className="card border">
							<div className="card-header">
								Zadaće
								<button className="btn btn-primary right" onClick={e => this.dodajZadacu(e)}>
									Dodaj
								</button>
							</div>
							<div className="card-body text-left">
								<ZadacePredmeta auth={this.props.auth} predmet={this.props.match.params.predmetID}/>								
							</div>
						</div>						
						<div className="razmak"></div>
					</div>
				</div>
      		</div>
		);
	}
}

export default ListaUcenikaPredmeta;
