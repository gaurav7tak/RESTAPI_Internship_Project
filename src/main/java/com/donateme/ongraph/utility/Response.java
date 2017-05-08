package com.donateme.ongraph.utility;

import java.util.HashMap;

public class Response {
    private int statusCode;
    private String accessToken;
    private String message;
    public Response(int status,String token,String message){
        this.statusCode=status;
        this.accessToken=token;
        this.message=message;

        //System.out.println(status+" "+token);
    }
   // public void setStatusCode(int status){
     //   this.statusCode=status;
   // }
    public int getStatusCode(){
        return this.statusCode;
    }
   /* public void setAccessToken(String at){
        this.accessToken=at;
    }*/
    public String getAccessToken(){
        return this.accessToken;
    }

    public String getMessage(){return this.message;}

}
