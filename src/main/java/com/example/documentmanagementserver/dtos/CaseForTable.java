package com.example.documentmanagementserver.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CaseForTable {
    private int id;
    private String label;
    private String status;

    public CaseForTable(int id, String label, String status) {
        this.id = id;
        this.label = label;
        this.status = status;
    }
}
