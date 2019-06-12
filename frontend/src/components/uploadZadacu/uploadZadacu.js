import React, { Component } from "react";
import "./UploadZadacu.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Badge from 'react-bootstrap/Badge';
import Figure from 'react-bootstrap/Figure';
import Button from 'react-bootstrap/Button';

import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment'; 


class UploadZadacu extends Component {
	constructor(props) {
		super(props);
		  this.state = {
			selectedFile: null
		  }
			Moment.locale('en');
			momentLocalizer();
	   
	  }
	  

	componentDidMount() {
		console.log("UPLOAD",this.props.match.params.predmetId);
		this.setState({predmetId: this.props.match.params.predmetId}); 
	}
	onChangeHandler = event => {

		console.log(event.target.files[0])
		this.setState({
			selectedFile: event.target.files[0],
			loaded: 0,
		  })
	}
	onClickHandler = () => {

		var data2 = {
			status: 'open',
			naziv: this.state.naziv,
			datumIsteka: this.state.datum
	   	}
		var headers = {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + this.props.auth 
        }
        axios.post("/nwtUpload/zadaca", data2, {headers: headers})
			// Kreiranje zadace
            .then((response) => {
				console.log(response.data.id);
				axios
				// Dodavanje predmeta zadaci
				.get("/nwtUpload/dodjeliZadacuPredmetu/" + response.data.id + "/" + this.state.predmetId, {
					headers: {
						Authorization: "Bearer " + this.props.auth 
					}
				})
				.then(response => {
					//OVO JE UPLOAD ZADACE
					
					console.log(response.data);

					const data = new FormData()
					data.append('file', this.state.selectedFile)
					data.append('zadacaId', response.data.id)

					var headers = {
						'Authorization': "Bearer " + this.props.auth 
					}

					axios.post("/nwtUpload/uploadFile", data, {headers: headers})
					.then(res => { 
						alert("Uspješno ste dodali zadaću!");
					})
				})
				.catch(err => {alert("Uspješno ste dodali zadaću!")});
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
							<div className="card-header">Dodavanje zadaće</div>
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
									<div className="form-group">
										<label htmlFor="exampleInputEmail1">Datum isteka zadaće:</label>
										
										<DateTimePicker
											value={this.state.datum}
											onChange={value => this.setState({ datum : value })}
											time={false}
										/>
									</div>
									<div className="form-group">
										<label htmlFor="exampleInputEmail1">Materijal:</label>
										<br></br>
										<input 
											type="file" 
											name="file" 
											onChange={this.onChangeHandler}
											placeholder="Odaberite datoteku"/>
									</div>
									
									<br></br>
									<button type="button" class="btn btn-success btn-block" onClick={this.onClickHandler}>Upload</button> 
									
									
									{/* <Button variant="primary">Upload</Button>
									<h2>    Uploadani materijal:  </h2>
									<Figure>
										<Figure.Image
											width={171}
											height={180}
											alt="171x180"
											//src="holder.js/171x180"
										/>
										<Figure.Caption>
											Uploadani materijal
										</Figure.Caption>
									</Figure>
									<h2>    Progres:   </h2>
									<ProgressBar animated now={100} />
									<h2>    Poruka:  </h2>
									<p>Upload u toku</p> */}


								</div>
							</div>
						</div>
					</div>
				</div>
      		</div>
			
		);
	}
}

export default UploadZadacu;
