package com.it.projectapplication.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submitDate;

    private Integer subsidyMethod;

    private Integer grade;

    private Integer property;


    private String address;

    private String introduction;

    private String scheme;

    private BigDecimal stateSubsidy;

    private BigDecimal provinceSubsidy;

    private BigDecimal citySubsidy;

    private BigDecimal countySubsidy;

    private BigDecimal unitSubsidy;

    private BigDecimal bankLoan;

    private BigDecimal otherSubsidy;

    private BigDecimal totalINvestment;

    private BigDecimal totalSubsidy;

    private BigDecimal totalHypothesisSubsidy;

    private BigDecimal totalFactSubsidy;

    private Integer state;

    private Integer villageDepartmentState;

    private Integer manageDepartmentState;

    private Integer manageDepartmentPlanState;

    private  Integer fundDepartmentState;

    private  String villageName;

    private  String manageName;

    private  String intermExamineUsername;

    private Integer interimExamineState;

    private String intermExamineOpinion;

    private  String interimExaminPaperFile;

    private String checkUsername;

    private Integer checkState;

    private String checkPaperFile;

    private String checkOpionion;

    private String perfarmanceUsername;

    private Integer perfarmanceState;

    private String perfarmanceOpinion;

    private String perfarmancePaperFile;
    @ManyToOne
    @JoinColumn(name = "project_special_id", referencedColumnName = "id")
    private SpecialProject specialProject;

    @ManyToOne
    @JoinColumn(name = "project_user_id", referencedColumnName = "user_id")
    private User user;

    private String companyContact;

    private String phone;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getSubsidyMethod() {
        return subsidyMethod;
    }

    public void setSubsidyMethod(Integer subsidyMethod) {
        this.subsidyMethod = subsidyMethod;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public BigDecimal getStateSubsidy() {
        return stateSubsidy;
    }

    public void setStateSubsidy(BigDecimal stateSubsidy) {
        this.stateSubsidy = stateSubsidy;
    }

    public BigDecimal getProvinceSubsidy() {
        return provinceSubsidy;
    }

    public void setProvinceSubsidy(BigDecimal provinceSubsidy) {
        this.provinceSubsidy = provinceSubsidy;
    }

    public BigDecimal getCitySubsidy() {
        return citySubsidy;
    }

    public void setCitySubsidy(BigDecimal citySubsidy) {
        this.citySubsidy = citySubsidy;
    }

    public BigDecimal getCountySubsidy() {
        return countySubsidy;
    }

    public void setCountySubsidy(BigDecimal countySubsidy) {
        this.countySubsidy = countySubsidy;
    }

    public BigDecimal getUnitSubsidy() {
        return unitSubsidy;
    }

    public void setUnitSubsidy(BigDecimal unitSubsidy) {
        this.unitSubsidy = unitSubsidy;
    }

    public BigDecimal getBankLoan() {
        return bankLoan;
    }

    public void setBankLoan(BigDecimal bankLoan) {
        this.bankLoan = bankLoan;
    }

    public BigDecimal getOtherSubsidy() {
        return otherSubsidy;
    }

    public void setOtherSubsidy(BigDecimal otherSubsidy) {
        this.otherSubsidy = otherSubsidy;
    }

    public BigDecimal getTotalINvestment() {
        return totalINvestment;
    }

    public void setTotalINvestment(BigDecimal totalINvestment) {
        this.totalINvestment = totalINvestment;
    }

    public BigDecimal getTotalSubsidy() {
        return totalSubsidy;
    }

    public void setTotalSubsidy(BigDecimal totalSubsidy) {
        this.totalSubsidy = totalSubsidy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getVillageDepartmentState() {
        return villageDepartmentState;
    }

    public void setVillageDepartmentState(Integer villageDepartmentState) {
        this.villageDepartmentState = villageDepartmentState;
    }

    public Integer getManageDepartmentState() {
        return manageDepartmentState;
    }

    public void setManageDepartmentState(Integer manageDepartmentState) {
        this.manageDepartmentState = manageDepartmentState;
    }

    public Integer getFundDepartmentState() {
        return fundDepartmentState;
    }

    public void setFundDepartmentState(Integer fundDepartmentState) {
        this.fundDepartmentState = fundDepartmentState;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getIntermExamineUsername() {
        return intermExamineUsername;
    }

    public void setIntermExamineUsername(String intermExamineUsername) {
        this.intermExamineUsername = intermExamineUsername;
    }

    public Integer getInterimExamineState() {
        return interimExamineState;
    }

    public void setInterimExamineState(Integer interimExamineState) {
        this.interimExamineState = interimExamineState;
    }

    public String getIntermExamineOpinion() {
        return intermExamineOpinion;
    }

    public void setIntermExamineOpinion(String intermExamineOpinion) {
        this.intermExamineOpinion = intermExamineOpinion;
    }

    public String getInterimExaminPaperFile() {
        return interimExaminPaperFile;
    }

    public void setInterimExaminPaperFile(String interimExaminPaperFile) {
        this.interimExaminPaperFile = interimExaminPaperFile;
    }

    public String getCheckUsername() {
        return checkUsername;
    }

    public void setCheckUsername(String checkUsername) {
        this.checkUsername = checkUsername;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public String getCheckPaperFile() {
        return checkPaperFile;
    }

    public void setCheckPaperFile(String checkPaperFile) {
        this.checkPaperFile = checkPaperFile;
    }

    public String getCheckOpionion() {
        return checkOpionion;
    }

    public void setCheckOpionion(String checkOpionion) {
        this.checkOpionion = checkOpionion;
    }

    public String getPerfarmanceUsername() {
        return perfarmanceUsername;
    }

    public void setPerfarmanceUsername(String perfarmanceUsername) {
        this.perfarmanceUsername = perfarmanceUsername;
    }

    public Integer getPerfarmanceState() {
        return perfarmanceState;
    }

    public void setPerfarmanceState(Integer perfarmanceState) {
        this.perfarmanceState = perfarmanceState;
    }

    public String getPerfarmanceOpinion() {
        return perfarmanceOpinion;
    }

    public void setPerfarmanceOpinion(String perfarmanceOpinion) {
        this.perfarmanceOpinion = perfarmanceOpinion;
    }

    public String getPerfarmancePaperFile() {
        return perfarmancePaperFile;
    }

    public void setPerfarmancePaperFile(String perfarmancePaperFile) {
        this.perfarmancePaperFile = perfarmancePaperFile;
    }

    public SpecialProject getSpecialProject() {
        return specialProject;
    }

    public void setSpecialProject(SpecialProject specialProject) {
        this.specialProject = specialProject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getManageDepartmentPlanState() {
        return manageDepartmentPlanState;
    }

    public void setManageDepartmentPlanState(Integer manageDepartmentPlanState) {
        this.manageDepartmentPlanState = manageDepartmentPlanState;
    }

    public BigDecimal getTotalHypothesisSubsidy() {
        return totalHypothesisSubsidy;
    }

    public void setTotalHypothesisSubsidy(BigDecimal totalHypothesisSubsidy) {
        this.totalHypothesisSubsidy = totalHypothesisSubsidy;
    }

    public BigDecimal getTotalFactSubsidy() {
        return totalFactSubsidy;
    }

    public void setTotalFactSubsidy(BigDecimal totalFactSubsidy) {
        this.totalFactSubsidy = totalFactSubsidy;
    }
}
