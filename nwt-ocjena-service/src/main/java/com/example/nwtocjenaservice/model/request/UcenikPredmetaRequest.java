package com.example.nwtocjenaservice.model.request;

import javax.validation.constraints.NotBlank;

public class UcenikPredmetaRequest {
    @NotBlank(message = "Morate unijeti naziv")
    public String naziv;

    public Integer ucenik_id;

    public Integer predmet_id;
}