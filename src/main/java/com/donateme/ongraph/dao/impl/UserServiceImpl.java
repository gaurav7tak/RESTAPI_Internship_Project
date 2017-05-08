package com.donateme.ongraph.dao.impl;
        import java.security.NoSuchAlgorithmException;
        import java.security.NoSuchProviderException;
        import java.sql.*;
        import java.sql.Date;
        import java.text.ParseException;
        import java.util.*;


        import com.donateme.ongraph.dao.UserService;
        import com.donateme.ongraph.dto.User;
        import com.donateme.ongraph.utility.ConnectionUtility;
        import com.donateme.ongraph.utility.MessageConstants;
        import com.donateme.ongraph.utility.Response;
        import com.donateme.ongraph.utility.SimpleMD5;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class UserServiceImpl extends ConnectionUtility implements UserService {

    //newUserSignUp
    public Response addUser(User usr) {
        // ConnectionUtility util=null;
        int n=0;
        int statuscode=0;
        String message=null;

        try {
            Connection connection=getConnection();

            PreparedStatement preparedStatement = connection
                    .prepareStatement
                            ("insert into User(UserName,UserPassword,FirstName,LastName,UserEmail,dob,BloodGroup,MobileNo,RoleId,DonationEn,RequestEn,LastDonationId,CityId) values (?,MD5(?),?,?,?, ? ,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,usr.getUserName() );
            preparedStatement.setString(2,usr.getPassword());
            preparedStatement.setString(3,usr.getFirstName());
            preparedStatement.setString(4,usr.getLastName());
            preparedStatement.setString(5,usr.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(usr.getDob().getTime()));
            preparedStatement.setString(7,usr.getBloodGroup());
            preparedStatement.setString(8,usr.getMobileNo());
            preparedStatement.setInt(9,usr.getRole());
            preparedStatement.setBoolean(10,usr.getDonationEn());
            preparedStatement.setBoolean(11,usr.getRequestEn());
            preparedStatement.setInt(12,usr.getDonationId());
            preparedStatement.setInt(13,usr.getCityId());
            n= preparedStatement.executeUpdate();

            if(n==1)

            {
                statuscode=1;
                message=MessageConstants.REGISTER_SUCCESSFULLY;
            }
            else{
                message=MessageConstants.YOU_HAVENT_REGISTERED;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Response(statuscode,null,message);
    }

    //login
    public  Response loginAuthentication(String userName,String password) {
        boolean check = false;
        int status = 0;
        String message=null;
        String at = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String qry = "Select * from User where UserName = '" + userName + "' and UserPassword = MD5('" + password + "')";
            System.out.println(qry);
            PreparedStatement ps = connection.prepareStatement(qry);
            ResultSet rs1 = ps.executeQuery();
            // System.out.println(rs1);
            check = rs1.next();
            String uuid = userName + password;
            //System.out.println(check);
            if (check) {
                at = SimpleMD5.hashing(uuid);//access token generation
                try {
                    this.updateUserAT(userName, at,connection); //for storing access token

                } catch (ParseException e) {
                    e.printStackTrace(); //
                }
                status = 1;
                message="login successful";


            } else {
                status = 0;
                at = null;
                message=MessageConstants.INVALID_CREDENTIALS;
            }
            rs1.close();
            ps.close();
            connection.close();
            //System.out.println(check);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            message="Unknown Error";
        } catch (SQLException e) {
            e.printStackTrace();
            status = 0;
            message= MessageConstants.INTERNAL_SERVER_ERROR;
            //internal server eroor to establish database query/connection
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    // System.out.print("infinallyy block");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Response(status, at,message);
    }

    public void updateUserAT(String userName,String value,Connection connection) throws ParseException {
        try {
            PreparedStatement preparedStatement =connection
                    .prepareStatement("update User set AccessToken=? where UserName=?");
            preparedStatement.setString(1,value);
            preparedStatement.setString(2,userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //logout
    public Response logout(String accessToken,int id){
        String query=("Update User set AccessToken=null where AccessToken=? and Id=?");
        int statuscode=0;
        Connection connection = null;
        String message=null;
        try {
            connection=getConnection();
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,accessToken);
            ps.setInt(2,id);
            int n=ps.executeUpdate();

            if(n==1){
                statuscode=1;
                message=MessageConstants.SUCCESSFULLY_LOGGEDOUT;
            }
            else {
                message=MessageConstants.UNABLETOLOGOUT;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message=MessageConstants.INTERNAL_SERVER_ERROR;
        }
        finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                message=MessageConstants.INTERNAL_SERVER_ERROR;
            }
        }
        return new Response(statuscode,null,message);
    }

    //DisableAccount
    public Response disableUser(String accessToken,int id) {
        String message=null;
        int statuscode=0;
        int n=0;
        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE  User set enable=false where Id=? and accessToken=?");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,accessToken);
            n=preparedStatement.executeUpdate();
            if(n==1){
                statuscode=1;
                message=MessageConstants.RECORD_UPDATED;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            message= MessageConstants.INTERNAL_SERVER_ERROR;
        }
        return new Response(statuscode,null,message);
    }

    //countUser
    public int getUserCount(){
        int count=0;
        Connection connection= null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) AS count FROM User");
            while (rs.next()) {
                count = rs.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return count;
    }

    //getUser
    public List<User> getAllUsers() {
                List<User> users = new ArrayList<User>();
        try {
            Connection connection=getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from User limit 15");
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("useremail"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public  User getUserByUserName(String userName) {
        User user = new User();
                Connection connection=null;
        try {
            connection=getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from User where UserName=?");
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt("Id"));
                user.setUserName(rs.getString("UserName"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastNAme"));
                user.setEmail(rs.getString("UserEmail"));
                user.setAddress(rs.getString("Address"));
                user.setBloodGroup(rs.getString("BloodGroup"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public List<User> request(String at,int userid) {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection=getConnection();
            PreparedStatement  stmt=connection.prepareStatement("update user set RequestEn=1 where id=? and accessToken=?");
            stmt.setInt(1,userid);
            stmt.setString(2,at);
            int reqfd=stmt.executeUpdate();
            if(reqfd==1) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM User WHERE DonationEn=TRUE ");
                while (rs.next()) {
                    User user = new User();
                    user.setUserName(rs.getString("username"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    user.setBloodGroup(rs.getString("bloodgroup"));
                    user.setCityId(rs.getInt("CityId"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public List<User> donate() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection=getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from User where RequestEn=TRUE ");
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("username"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setBloodGroup(rs.getString("bloodgroup"));
                user.setCityId(rs.getInt("cityid"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    public Response makeRequest(int requesterId, int donorId, java.util.Date requestDate)
    {
        int statuscode=0;
        String message=null;
        try {
            Connection conn=getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement("insert into request(RequesterId,DonorId,RequestDate) values(?,?,?)");
            preparedStatement.setInt(1,requesterId);
            preparedStatement.setInt(2,donorId);
            preparedStatement.setDate(3, new java.sql.Date(requestDate.getTime()));
            //preparedStatement.setInt(4,requesterId);
            int n=preparedStatement.executeUpdate();
            if(n==1)
            {
                statuscode=1;
                message="request submitted succesfully";
            }
            else{
                message="request was not sent";
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    return new Response(statuscode,null,message);
    }
}

