package com.it.projectapplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "Manager_Information")
public class ManagerInformation implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String username;

    private String name;

    private String identityCard;

    private String phone;

    private String mobilePhone;

    private String fixedLinePhone;

    private String InternalEmail;

    private String department;

    private String administrativeOffice;

    private String administrativeDivisonNumber;

    private String administrativeDivisonName;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFixedLinePhone() {
        return fixedLinePhone;
    }

    public void setFixedLinePhone(String fixedLinePhone) {
        this.fixedLinePhone = fixedLinePhone;
    }

    public String getInternalEmail() {
        return InternalEmail;
    }

    public void setInternalEmail(String internalEmail) {
        InternalEmail = internalEmail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAdministrativeOffice() {
        return administrativeOffice;
    }

    public void setAdministrativeOffice(String administrativeOffice) {
        this.administrativeOffice = administrativeOffice;
    }

    public String getAdministrativeDivisonNumber() {
        return administrativeDivisonNumber;
    }

    public void setAdministrativeDivisonNumber(String administrativeDivisonNumber) {
        this.administrativeDivisonNumber = administrativeDivisonNumber;
    }

    public String getAdministrativeDivisonName() {
        return administrativeDivisonName;
    }

    public void setAdministrativeDivisonName(String administrativeDivisonName) {
        this.administrativeDivisonName = administrativeDivisonName;
    }
}
