package com.donateme.ongraph.utility;


import javax.sql.DataSource;

import com.donateme.ongraph.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;

public class ConnectionUtility{


    private static DriverManagerDataSource dataSource;


    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }
}
