package com.example.uploadservice.model;

import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.model.TaskFile;
import javax.persistence.*;

@Entity
public class Zadaca{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;
    

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_id")
    private String fileId;

    @Column(name="file_type")
    private String fileType;

    @Column(name="web_content_link")
    private String webContentLink;

    @Column(name="web_view_link")
    private String webViewLink;


    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Zadaca() {}

    public Zadaca(String status, Predmet predmet, String fileName, String fileId, String fileType, String webContentLink, String webViewLink){
        this.status = status;
        this.predmet = predmet;
        this.fileName = fileName;
        this.fileId = fileId;
        this.fileType = fileType;
        this.webContentLink = webContentLink;
        this.webViewLink = webViewLink;
    }   
}