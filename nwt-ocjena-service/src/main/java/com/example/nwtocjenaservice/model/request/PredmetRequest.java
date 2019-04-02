package com.example.nwtocjenaservice.model.request;

import javax.validation.constraints.NotBlank;

public class PredmetRequest {
    @NotBlank(message = "Morate unijeti naziv")
    public String naziv;

    public Integer nastavnik_id;
}