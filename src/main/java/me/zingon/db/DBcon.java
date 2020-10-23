package me.zingon.db;

import me.zingon.backup.util.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by page on 2017/6/12.
 */
public class DBcon {

	 private static Connection conn = null;

//    public static Connection getCon(){
//        Connection dbConnection = null;
//        try {
//            Class.forName(Config.get("db.driver"));
//            dbConnection= DriverManager
//                    .getConnection(Config.get("db.url"), Config.get("db.username"),Config.get("db.password"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dbConnection;
//    }
	 
	 public static Connection getCon(){
		 return conn;
	 }
    
    
    public static Connection getCon(String driver,String url,String username,String password) throws Exception{
    	Connection dbConnection = null;
    		Class.forName(driver);
    		dbConnection= DriverManager
    				.getConnection(url, username,password);
    		conn = dbConnection;
    	return dbConnection;
    }

}
