package com.example.nwtocjenaservice.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.time.LocalDate;

public class OcjenaRequest{
    @Max(value = 10, message="Ocjena mora biti manja ili jednaka 10")
    @Min(value = 1, message="ocjena mora biti veća ili jednaka 0")
    public Integer ocjena;

    public LocalDate datum;

    public Integer ucenik_id;

    public Integer predmet_id;
    
}