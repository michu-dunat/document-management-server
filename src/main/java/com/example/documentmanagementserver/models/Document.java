package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Document {

    @Lob
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    byte[] file;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String type;
    @NotNull
    private String addresseeOrSender;
    @NotNull
    private Boolean isIncoming;
    private String methodOfReceipt;
    private java.sql.Date dateOfDelivery;
    @NotNull
    private java.sql.Date dateOfReceiptOrDispatch;
    @NotNull
    private java.sql.Date dateOfLetter;
    @NotNull
    private String fileName;
    private String comments;
    @ManyToOne
    private User sender;


    private Boolean isResponseRequired;
    private java.sql.Date deadlineForResponse;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "case_id")
    private Case documentCase;

    public Document(String type, String addresseeOrSender, Boolean isIncoming, Date dateOfReceiptOrDispatch, Date dateOfLetter,
                    @NotNull byte[] file, Boolean isResponseRequired, Date deadlineForResponse, String fileName) {
        this.type = type;
        this.addresseeOrSender = addresseeOrSender;
        this.isIncoming = isIncoming;
        this.dateOfReceiptOrDispatch = dateOfReceiptOrDispatch;
        this.dateOfLetter = dateOfLetter;
        this.file = file;
        this.isResponseRequired = isResponseRequired;
        this.deadlineForResponse = deadlineForResponse;
        this.fileName = fileName;
    }
}
