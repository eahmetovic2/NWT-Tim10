import React, { Component } from "react";
import "./dodajPredmet.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Badge from 'react-bootstrap/Badge';
import Figure from 'react-bootstrap/Figure';
import Button from 'react-bootstrap/Button';

import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment'; 


class DodajPredmet extends Component {
	constructor(props) {
		super(props);
	   
	  }
	  

	componentDidMount() {
		console.log("DODAJ PREDMET",this.props.match.params.nastavnikId);
		this.setState({nastavnikId: this.props.match.params.nastavnikId}); 
	}
	onChangeHandler = event => {

		console.log(event.target.files[0])
		this.setState({
			selectedFile: event.target.files[0],
			loaded: 0,
		  })
	}
	onClickHandler = () => {

		var data = {
			naziv: this.state.naziv,
			nastavnikId: this.state.nastavnikId
	   	}
		var headers = {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + this.props.auth 
        }
        axios.post("/nwtOcjena/predmet", data, {headers: headers})
			// Kreiranje zadace
            .then((response) => {
				console.log(response.data.id);
				alert("Uspješno dodan predmet.")
            })
            .catch((error) => {
                console.log(error);
            })
	}
	handleInput(e) {
		this.setState({naziv: e.target.value})
	}
	render() {
		return (
			<div className="container">
				<div className="row">
					<div className="col-12">
						<div className="card border">
							<div className="card-header">Dodavanje predmeta</div>
							<div className="card-body text-left">
								<div className="upload">
								
									<div className="form-group">
										<label htmlFor="exampleInputEmail1">Naziv:</label>									
										<input
												type="text"
												name="Naziv"
												onChange={e => this.handleInput(e)}
												className="form-control"
												placeholder="Naziv"
											/>
									</div>
									
									<br></br>
									<button type="button" class="btn btn-success btn-block" onClick={this.onClickHandler}>Dodaj</button> 
									
									


								</div>
							</div>
						</div>
					</div>
				</div>
      		</div>
			
		);
	}
}

export default DodajPredmet;
