package com.it.projectapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "special_project")
public class SpecialProject implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;

    private String batch;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopDate;

    @ManyToOne
    @JoinColumn(name = "project_fund_id", referencedColumnName = "id")
    private SpecialFund specialFund;

    private Integer declareSubject;

    private String undertakLetterFile;

    private String state;

    private String declareExplain;

    @OneToMany(mappedBy = "specialProject")
    private Set<Project> projects=new HashSet<>(0);

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public SpecialFund getSpecialFund() {
        return specialFund;
    }

    public void setSpecialFund(SpecialFund specialFund) {
        this.specialFund = specialFund;
    }

    public Integer getDeclareSubject() {
        return declareSubject;
    }

    public void setDeclareSubject(Integer declareSubject) {
        this.declareSubject = declareSubject;
    }

    public String getUndertakLetterFile() {
        return undertakLetterFile;
    }

    public void setUndertakLetterFile(String undertakLetterFile) {
        this.undertakLetterFile = undertakLetterFile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeclareExplain() {
        return declareExplain;
    }

    public void setDeclareExplain(String declareExplain) {
        this.declareExplain = declareExplain;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
