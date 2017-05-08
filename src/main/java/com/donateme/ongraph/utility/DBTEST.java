package com.donateme.ongraph.utility;

import java.sql.*;

/**
 * Created by GRV on 6/6/2016.
 */
public class DBTEST {
    public static Connection conn=null;
    public static Connection getConnection()
    {
        if(conn!=null)
            return conn;
        else
        {
            try{
                Class.forName(Constants.MYSQLDRIVER);
                try {
                    conn= DriverManager.getConnection(Constants.DATABASESERVERADDRESS,Constants.DATABASEUSERNAME,Constants.DATABASEPASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return conn;
        }
    }
    public void exquery()
    {
        Connection con=DBTEST.getConnection();
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement("Select * from User");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
