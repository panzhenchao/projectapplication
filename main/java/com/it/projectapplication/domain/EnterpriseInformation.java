package com.it.projectapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "enterprise_information")
public class EnterpriseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_id")
    private Long id;
    @Column(name = "enterprise_username")
    private String username;
    @Column(name = "enterprise_companyName")
    private String companyName;
    @Column(name = "enterprise_organizing_nstitution_number")
    private String organizingInstitutionNumber;
    @Column(name = "enterprise_business_license_registration_number")
    private String businessLicenseRegistrationNumber;
    @Column(name = "enterprise_business_license_img")
    private String businessLicenseImg;
    @Column(name = "enterprise_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    @Column(name = "enterprise_corporate_representative")
    private String corporateRepresentative;
    @Column(name = "enterprise_corporate_identity_card")
    private String corporateIdentityCard;
    @Column(name = "enterprise_corporate_identity_card_img")
    private String corporateIdentityCardImg;
    @Column(name = "enterprise_company_contact")
    private String companyContact;
    @Column(name = "enterprise_phone")
    private String phone;
    @Column(name = "enterprise_industry")
    private String industry;
    @Column(name = "enterprise_registered_captial")
    private BigDecimal registeredCaptial;
    @Column(name = "enterprise_bank_name")
    private String bankName;
    @Column(name = "enterprise_bank_account")
    private String bankAccount;
    @Column(name = "enterprise_financer")
    private String financer;
    @Column(name = "enterprise_accounting_certificate")
    private String AccountingCertificate;
    @Column(name = "enterprise_ager_vectigalis")
    private String agerVectigalis;
    @Column(name = "enterprise_tax_registeration_certificate")
    private String taxRegistrationCertificate;
    @Column(name = "enterprise_address")
    private String address;
    @Column(name = "enterprise_individual_resume")
    private String individualResume;
    @Column(name = "enterprise_type")
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizingInstitutionNumber() {
        return organizingInstitutionNumber;
    }

    public void setOrganizingInstitutionNumber(String organizingInstitutionNumber) {
        this.organizingInstitutionNumber = organizingInstitutionNumber;
    }

    public String getBusinessLicenseRegistrationNumber() {
        return businessLicenseRegistrationNumber;
    }

    public void setBusinessLicenseRegistrationNumber(String businessLicenseRegistrationNumber) {
        this.businessLicenseRegistrationNumber = businessLicenseRegistrationNumber;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCorporateRepresentative() {
        return corporateRepresentative;
    }

    public void setCorporateRepresentative(String corporateRepresentative) {
        this.corporateRepresentative = corporateRepresentative;
    }

    public String getCorporateIdentityCard() {
        return corporateIdentityCard;
    }

    public void setCorporateIdentityCard(String corporateIdentityCard) {
        this.corporateIdentityCard = corporateIdentityCard;
    }

    public String getCorporateIdentityCardImg() {
        return corporateIdentityCardImg;
    }

    public void setCorporateIdentityCardImg(String corporateIdentityCardImg) {
        this.corporateIdentityCardImg = corporateIdentityCardImg;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public BigDecimal getRegisteredCaptial() {
        return registeredCaptial;
    }

    public void setRegisteredCaptial(BigDecimal registeredCaptial) {
        this.registeredCaptial = registeredCaptial;
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

    public String getFinancer() {
        return financer;
    }

    public void setFinancer(String financer) {
        this.financer = financer;
    }

    public String getAccountingCertificate() {
        return AccountingCertificate;
    }

    public void setAccountingCertificate(String accountingCertificate) {
        AccountingCertificate = accountingCertificate;
    }

    public String getAgerVectigalis() {
        return agerVectigalis;
    }

    public void setAgerVectigalis(String agerVectigalis) {
        this.agerVectigalis = agerVectigalis;
    }

    public String getTaxRegistrationCertificate() {
        return taxRegistrationCertificate;
    }

    public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
        this.taxRegistrationCertificate = taxRegistrationCertificate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndividualResume() {
        return individualResume;
    }

    public void setIndividualResume(String individualResume) {
        this.individualResume = individualResume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}