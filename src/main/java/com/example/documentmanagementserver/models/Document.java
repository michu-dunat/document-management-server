package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

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
    @NotNull
    private String fileName;


    private Boolean isResponseRequired;
    private java.sql.Date deadlineForResponse;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="case_id")
    private Case documentCase;

    public Document(String type, String addresseeOrSender, Boolean isIncoming, Date dateOfReceiptOrDispatch,
                    @NotNull byte[] file, Boolean isResponseRequired, Date deadlineForResponse, String fileName) {
        this.type = type;
        this.addresseeOrSender = addresseeOrSender;
        this.isIncoming = isIncoming;
        this.dateOfReceiptOrDispatch = dateOfReceiptOrDispatch;
        this.file = file;
        this.isResponseRequired = isResponseRequired;
        this.deadlineForResponse = deadlineForResponse;
        this.fileName = fileName;
    }
}
