package me.zingon.backup.util;

import org.apache.log4j.Logger;

import me.zingon.App;

import java.io.File;

/**
 * Created by page on 2017/6/13.
 */
public class MyUtil {
	
	
    public static String a_b2aB(String str){
        if(str.contains("_")){
            String[] temp=str.split("_");
            temp[0] = temp[0].toLowerCase();
            for(int i=1;i<temp.length;i++){
                temp[0]+=temp[i].substring(0,1).toUpperCase()+temp[i].substring(1).toLowerCase();
            }
            return temp[0];
        }
        return str.toLowerCase();
    }

    public static String a_b2AB(String str) {
        StringBuilder sb=new StringBuilder();
        String[] temp = str.split("_");
        for (int i = 0; i < temp.length; i++) {
            sb.append(temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return sb.toString();
    }

//    public static String getPath(){
//        return Config.get("path")+Config.get("package").replace(".", File.separator);
//    }
    public static String getPath(){
    	return App.path+(Config.get("package")+App.expath).replace(".", File.separator);
    }

    public static String mkDir(String name){
        Logger logger= Logger.getLogger("");
        String path= (getPath()+File.separator+name).replace(".", File.separator);
        File f=new File(path);
        if(!f.isDirectory()) {
            f.mkdirs();
            logger.info("创建目录：" + path + " [ 成功 ]");
        } 
        return path;
    }
    public static String mkqueryDir(){
    	Logger logger= Logger.getLogger("");
    	String path= App.path+(Config.get("package")+".query"+App.expath).replace(".", File.separator);
    	File f=new File(path);
    	if(!f.isDirectory()) {
    		f.mkdirs();
    		logger.info("创建目录：" + path + " [ 成功 ]");
    	} 
    	return path;
    }
    public static String mkmapperDir(){
    	Logger logger= Logger.getLogger("");
    	String path= App.path+(Config.get("package")+".mapper"+App.expath).replace(".", File.separator);
    	File f=new File(path);
    	if(!f.isDirectory()) {
    		f.mkdirs();
    		logger.info("创建目录：" + path + " [ 成功 ]");
    	} 
    	return path;
    }

    public static String mkFrontDir(String expath){
        Logger logger= Logger.getLogger("");
        //App.path回到上级
        String path = App.path.substring(0 , App.path.length() - 1);
        path = path.substring(0 , path.lastIndexOf("\\") );
        path = path + File.separator +"resources"+ File.separator +"static" +File.separator +App.expath.replace(".", File.separator);
        path = path + File.separator +expath;
        
//        String path= Config.get("path")+File.separator+"static"+File.separator+App.expath;
        File f=new File(path);
        if(!f.isDirectory()){
            f.mkdirs();
            logger.info("创建目录：" + path + " [ 成功 ]");
        }
        return path;
    }
    
    public static void main(String[] args) {
		System.out.println(a_b2aB("DSDS_SD_DFS"));
	}
}
