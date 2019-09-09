package com.it.projectapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department_information")
public class DepartmentInformation implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String administrativeDivisonName;

    private String administrativeDivisonNumber;

    private String departmentName;

    private String administrativLevel;

    @OneToMany(mappedBy = "departmentInformation")
    private Set<ManagerInformation> managerInformations=new HashSet<ManagerInformation>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdministrativeDivisonName() {
        return administrativeDivisonName;
    }

    public void setAdministrativeDivisonName(String administrativeDivisonName) {
        this.administrativeDivisonName = administrativeDivisonName;
    }

    public String getAdministrativeDivisonNumber() {
        return administrativeDivisonNumber;
    }

    public void setAdministrativeDivisonNumber(String administrativeDivisonNumber) {
        this.administrativeDivisonNumber = administrativeDivisonNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAdministrativLevel() {
        return administrativLevel;
    }

    public void setAdministrativLevel(String administrativLevel) {
        this.administrativLevel = administrativLevel;
    }

    public Set<ManagerInformation> getManagerInformations() {
        return managerInformations;
    }

    public void setManagerInformations(Set<ManagerInformation> managerInformations) {
        this.managerInformations = managerInformations;
    }
}
