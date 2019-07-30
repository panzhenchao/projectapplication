package com.it.projectapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_name")
    private String name;
    @ManyToMany
    @JoinTable(name = "user_role_rel",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")})
    private Set<User>users=new HashSet<User>(0);
    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions =new HashSet<Permission>(0);

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", permissions=" + permissions +
                '}';
    }
}
