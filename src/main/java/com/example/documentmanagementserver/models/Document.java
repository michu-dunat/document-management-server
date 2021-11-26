package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String type;
    @NotNull
    private String addresseeOrSender;
    @NotNull
    private Boolean isIncoming;
    @NotNull
    private java.sql.Date dateOfReceiptOrDispatch;
    @Lob
    @NotNull
    byte[] file;

    private Boolean isResponseRequired;
    private java.sql.Date deadlineForResponse;


    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Case aCase;
}
