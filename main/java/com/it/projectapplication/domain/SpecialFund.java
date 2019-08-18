package com.it.projectapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "special_fund")
public class SpecialFund implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String number;

    private String state;

    private String consultPersonalName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopDate;



    @OneToMany(mappedBy = "specialFund" )
    private Set<SpecialProject> specialProjects=new HashSet<SpecialProject>(0);



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConsultPersonalName() {
        return consultPersonalName;
    }

    public void setConsultPersonalName(String consultPersonalName) {
        this.consultPersonalName = consultPersonalName;
    }



    

    public Set<SpecialProject> getSpecialProjects() {
        return specialProjects;
    }

    public void setSpecialProjects(Set<SpecialProject> specialProjects) {
        this.specialProjects = specialProjects;
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
}
