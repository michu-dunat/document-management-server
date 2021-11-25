package com.example.documentmanagementserver.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CaseForTable {

    private int id;
    private String label;
    private Date fillingOrClaimingDate;
    private String status;

    public CaseForTable(int id, String label, Date fillingOrClaimingDate, String status) {
        this.id = id;
        this.label = label;
        this.fillingOrClaimingDate = fillingOrClaimingDate;
        this.status = status;
    }
}
