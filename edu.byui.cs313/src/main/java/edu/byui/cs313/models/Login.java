package edu.byui.cs313.models;

public class Login 
{
	private String _username;
	private String _password;
	
	public String GetUserName()
	{
		return _username;
	}
	
	public String GetPassword()
	{
		return _password;
	}
	
	public void SetUserName(String username)
	{
		_username = username;
	}
	
	public void SetPassword(String password)
	{
		_password = password;
	}
}
