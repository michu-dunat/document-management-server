package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String code;
    @NotNull
    @Column(unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "role")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users;

    public Role(String code, String name) {
        this.name = name;
        this.code = code;
    }
}
