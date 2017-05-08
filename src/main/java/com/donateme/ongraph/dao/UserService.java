package com.donateme.ongraph.dao;

import com.donateme.ongraph.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.donateme.ongraph.dto.User;
import com.donateme.ongraph.utility.Response;


public interface UserService {
   // public Connection connection = null;

    public int getUserCount() throws SQLException;
    public User getUserByUserName(String userName);
    public Response disableUser(String accessToken,int id);
    public  void updateUserAT(String userName, String value,Connection connection) throws ParseException;
    public List<User> getAllUsers();
    public Response addUser(User usr );
    public Response logout(String username,int id);
    public List<User> request(String at,int userid);


    public Response loginAuthentication(String loginId, String password);

    public Response makeRequest(int userId, int userId1, Date date);
}
