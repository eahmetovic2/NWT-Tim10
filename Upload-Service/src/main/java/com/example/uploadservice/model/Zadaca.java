package com.example.uploadservice.model;

import java.sql.Date;

import com.example.uploadservice.model.Predmet;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "zadaca")
public class Zadaca{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    //@JoinColumn(name = "predmet_id", nullable = true)
    //private Predmet predmet;

    private Integer predmetId;
    

    @Column(name="file_name")
    private String fileName;

    @Column(name="datumIsteka")
    public Date datumIsteka;

    @Column(name="naziv")
    private String naziv;

    @Column(name="file_id")
    private String fileId;

    @Column(name="file_type")
    private String fileType;


    @Column(name="web_content_link")
    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Web link mora biti u formatu http:// ili https://")
    private String webContentLink;


    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Web view link mora biti u formatu http:// ili https://")
    @Column(name="web_view_link")
    private String webViewLink;

    @NotNull(message = "Status zadaca ne moze biti prazan")
    @Size(min=3, max=30, message= "Status zadace mora imati vise od 3 slova i manje od 30")
    private String status;


    public Integer getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmet) {
        this.predmetId = predmet;
    }

    public String getFileName() {
        return fileName;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getWebContentLink() {
        return webContentLink;
    }

    public void setWebContentLink(String webContentLink) {
        this.webContentLink = webContentLink;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    public Date getDatumIsteka() {
        return this.datumIsteka;
    }

    public void setDatumIsteka(Date datum) {
        this.datumIsteka = datum;
    }

    public Zadaca() {}
    public Zadaca(String status){
        this.status =  status;
    }
    public Zadaca(String status,String naziv, Date datum){
        this.status =  status;
        this.naziv = naziv;
        this.datumIsteka = datum;
    }
    public Zadaca(String status, Predmet predmet, String fileName, String fileId, String fileType, String webContentLink, String webViewLink){
        this.status = status;
        //this.predmet = predmet;
        this.fileName = fileName;
        this.fileId = fileId;
        this.fileType = fileType;
        this.webContentLink = webContentLink;
        this.webViewLink = webViewLink;
    }   
}