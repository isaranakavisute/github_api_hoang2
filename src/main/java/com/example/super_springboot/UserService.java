package com.example.super_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private ADODB_LOGSQLRepository1 ADODB_LOGSQLRepository1;
    public List<ADODB_LOGSQL> findAll()
    {
        return (List<ADODB_LOGSQL>) ADODB_LOGSQLRepository1.findAll();
    }
    public List<ADODB_LOGSQL> Get_SQL0_SQL1() { return (List<ADODB_LOGSQL>) ADODB_LOGSQLRepository1.Get_SQL0_SQL1(); }

}
