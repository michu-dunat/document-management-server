package com.example.documentmanagementserver.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserNamesForDocumentSenderField {
    private int id;
    private String firstNameLastName;

    public UserNamesForDocumentSenderField(int id, String firstNameLastName) {
        this.id = id;
        this.firstNameLastName = firstNameLastName;
    }
}
