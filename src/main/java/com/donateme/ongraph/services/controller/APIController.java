package com.donateme.ongraph.services.controller;
import com.donateme.ongraph.dao.UserService;
import com.donateme.ongraph.dao.impl.UserServiceImpl;
import com.donateme.ongraph.dto.User;
import com.donateme.ongraph.utility.DBTEST;
import com.donateme.ongraph.utility.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
public class APIController {
    //login
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    public ResponseEntity<Response> greeting
            (@RequestBody User usr)
             {
            UserService userimpl = new UserServiceImpl();
        Response obj =userimpl.loginAuthentication(usr.getUserName(), usr.getPassword());
        return new ResponseEntity<>(obj, HttpStatus.OK);

    }

    //logout
    @RequestMapping(value="/app/logout",method=RequestMethod.POST)
    public ResponseEntity<Response> logout(@RequestHeader(value="accessToken") String accessToken,@RequestBody User user )
    {
        UserServiceImpl usrimpl=new UserServiceImpl();

        return new ResponseEntity<>(usrimpl.logout(accessToken,user.getUserId()),HttpStatus.OK);
    }

    //getUser
    @RequestMapping(value="/app/user",method=RequestMethod.POST)
    public User getUser(@RequestBody User user){
        UserService usr=new UserServiceImpl();
        return usr.getUserByUserName(user.getUserName());
    }

    //getAllUSers
    @RequestMapping(value="/app/getUsers")
    public ResponseEntity<List<User>> getUsers()
    {
        UserService usr=new UserServiceImpl();
        return new ResponseEntity<List<User>>(usr.getAllUsers(),HttpStatus.OK);
    }

    //testapi
    @RequestMapping(value="/app/test")
    public void testing()
    {
        DBTEST d=new DBTEST();
        d.exquery();
    }

    //total user count
    @RequestMapping(value="/app/totalUserCount")
    public int countAll()
    {
        int count=-1;
        UserService usr=new UserServiceImpl();
        try {
            count=usr.getUserCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //new user signup
    @RequestMapping(value="/app/signup", method = RequestMethod.POST)
    public ResponseEntity<Response> adduser(@RequestBody User usr){
        UserService userimp=new UserServiceImpl();
        return new ResponseEntity<>(userimp.addUser(usr), HttpStatus.OK);
    }

    //disableUser
    @RequestMapping(value = "/app/disableuser",method =RequestMethod.POST)
    public ResponseEntity<Response> deleteUser(@RequestHeader(value="accessToken") String acessToken,@RequestBody  User user){
        UserService usrimpl=new UserServiceImpl();
        Response res=usrimpl.disableUser(acessToken,user.getUserId());
        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    //request for donation
    @RequestMapping(value="/app/request",method=RequestMethod.POST)
    public ResponseEntity<List<User>> requestblood(@RequestHeader (value="accessToken") String at,@RequestBody User user){
        UserService userimpl=new UserServiceImpl();
        return new ResponseEntity<>(userimpl.request(at,user.getUserId()),HttpStatus.OK);

    }
    @RequestMapping(value="/app/makerequest",method=RequestMethod.POST)
    public ResponseEntity<User> makerequest(@RequestHeader(value="accessToken") String at,@RequestBody List<User> usr)
    {
        Connection conn=null;
        //Date today=new Date();
        UserService usrimpl=new UserServiceImpl();
        Response r=usrimpl.makeRequest(usr.get(0).getUserId(),usr.get(1).getUserId(),new java.util.Date());
        return new ResponseEntity<>((MultiValueMap<String, String>) r,HttpStatus.OK);
    }


}

