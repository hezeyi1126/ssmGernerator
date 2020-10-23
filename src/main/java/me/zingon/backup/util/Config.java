package me.zingon.backup.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by page on 2017/6/12.
 */
public class Config {
    static Properties config=new Properties();
    static String path;
    static {
        try {
//            String path=Config.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//            String pattern = Pattern.quote("/");
//            String[] temp=path.split(pattern);
//            path=path.substring(0,path.length()-temp[temp.length-1].length()-1);
//            System.out.println("path is :"+path);
            path = Config.class.getResource("/").getPath();
            config.load(new FileInputStream(path+"/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return config.getProperty(key);
    }
    
    public static void set(String key,String value){
    	config.setProperty(key, value);
    }
    
    public static void save() {
    	FileOutputStream out;
		try {
			out = new FileOutputStream(path+"/config.properties");
			config.store(out, "....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
	
	public static void main(String[] args) throws Exception {
		
	}

}
