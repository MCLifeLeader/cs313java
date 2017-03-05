package edu.byui.cs313.models;

public class UserComment 
{
    public String user;
    public String date;
    public String comment;
    
    public UserComment() 
    {
        this.user = "";
        this.date = "";
        this.comment = "";
    }
    
    public UserComment(String user, String date, String comment) 
    {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }
}
