package com.it.projectapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "special_project")
public class SpecialProject implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;

    private String batch;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopTime;

    @ManyToOne
    @JoinColumn(name = "project_fund_id",referencedColumnName = "id")
    private SpecialProject specialProject;

    private Integer typeNumber;

    private String undertakLetterFile;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getStartTime() {
        return startTime;
    }

    public SpecialProject getSpecialProject() {
        return specialProject;
    }

    public void setSpecialProject(SpecialProject specialProject) {
        this.specialProject = specialProject;
    }

    public Integer getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getUndertakLetterFile() {
        return undertakLetterFile;
    }

    public void setUndertakLetterFile(String undertakLetterFile) {
        this.undertakLetterFile = undertakLetterFile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
}
