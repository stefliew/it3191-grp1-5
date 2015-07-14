package entity;

import database.*;
import entity.Password;
import java.sql.*;

public class User {
	private String id;
	private String pwd;
	private DBController db = new DBController();	
	public User(String id, String pwd){	this.id = id;this.pwd = pwd;	}

	public String getId(){	return id;	}
	public String getPwd(){	return pwd;	}	
	public void setId(String id){	this.id = id;	}
	public void setPwd(String pwd){this.pwd = pwd;	}

	public boolean createUser() throws Exception{
		boolean success = false;
		db.setUp("it3191");
		String sql = "INSERT INTO User(userid, password) ";
		sql += "VALUES ('" + id + "','" + Password.getSaltedHash(pwd) +"')";
		if (db.updateRequest(sql)==1)	success = true;
		db.terminate();
		return success;
	}
	public boolean updateUser() throws Exception{
		boolean success = false;
		db.setUp("it3191");
		String sql = "UPDATE User SET password = '" +
				Password.getSaltedHash(pwd) + "' WHERE userid = '" + id +"'";
		if (db.updateRequest(sql)==1)	success = true;
		db.terminate();
		return success;
	}

	public boolean deleteUser(){
		boolean success = false;
		db.setUp("it3191");
		String sql = "DELETE FROM User where userid = '" +
				id + "'" ;
		if (db.updateRequest(sql)==1)
			success = true;
		db.terminate();
		return success;
	}
	public boolean retrieveUser(){
		boolean success = false;
		ResultSet rs = null;
		db.setUp("it3191");
		String dbQuery = "SELECT * FROM User WHERE userid ='";
		dbQuery += id + "'";
		rs = db.readRequest(dbQuery);
		try{	if (rs.next()){
				if (Password.check(pwd, rs.getString("password")))
				success = true;
			}
		}
catch (Exception e) {e.printStackTrace();}
		db.terminate();
		return success;
	}
}
