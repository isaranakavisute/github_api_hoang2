package com.example.super_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ADODB_LOGSQL")
@Getter
@Setter
public class ADODB_LOGSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMER")
    private Integer TIMER;

//    @Id
//    @Column(name = "ROWID")
//    private String ROWID;

//    @Id
//    @Column(name="ROWID")
//    private String rowId;

    @Column(name = "SQL0")
    private String SQL0;

    @Column(name = "SQL1")
    private String SQL1;

    public String getSQL0() {
        return SQL0;
    }

    public String getSQL1() {
        return SQL1;
    }

}