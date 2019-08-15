package com.it.projectapplication.domain;

import javax.persistence.*;

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
}
