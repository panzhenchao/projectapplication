package com.it.projectapplication.domain;

import javax.persistence.*;

@Entity
@Table(name = "personal_information")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "personal_username")
    private String username;
    @Column (name = "personal_name")
    private String name;
    @Column (name = "personal_identity_card")
    private String identityCard;
    @Column (name = "personal_phone")
    private String phone;
    @Column (name = "personal_community")
    private String community;
    @Column (name = "personal_bank_name")
    private String bankName;
    @Column (name = "personal_bank_Account")
    private String bankAccount;
    @Column (name = "personal_address")
    private String address;
    @Column (name = "personal_identity_card_img")
    private String identityCardImg;
    @Column (name = "personal_individual_resume")
    private String individualResume;

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

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCardImg() {
        return identityCardImg;
    }

    public void setIdentityCardImg(String identityCardImg) {
        this.identityCardImg = identityCardImg;
    }

    public String getIndividualResume() {
        return individualResume;
    }

    public void setIndividualResume(String individualResume) {
        this.individualResume = individualResume;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", phone='" + phone + '\'' +
                ", community='" + community + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", address='" + address + '\'' +
                ", identityCardImg='" + identityCardImg + '\'' +
                ", individualResume='" + individualResume + '\'' +
                '}';
    }
}
