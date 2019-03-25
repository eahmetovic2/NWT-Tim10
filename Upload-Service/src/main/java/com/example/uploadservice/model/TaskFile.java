package com.example.uploadservice.model;
import javax.persistence.*;

@Entity
public class TaskFile{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name="file_id")
    private String fileId;

    @Column(name="file_type")
    private String fileType;

    @Column(name="web_content_link")
    private String webContentLink;

    @Column(name="web_view_link")
    private String webViewLink;


    public TaskFile() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TaskFile(String name, String fileId, String fileType, String webContentLink, String webViewLink){
        this.name = name;
        this.fileId = fileId;
        this.fileType = fileType;
        this.webContentLink = webContentLink;
        this.webViewLink = webViewLink;
    }
}