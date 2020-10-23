package me.zingon.backup.util;

import me.zingon.model.Field;

import java.util.*;

/**
 * Created by page on 2017/6/12.
 */
public class Maps {
    static Map<String,List<Field>> tables = new HashMap<String, List<Field>>();
    static Map<String,String> pkmap = new HashMap<String, String>();

    public static List<Field> put(String table,List<Field> fields){
        return tables.put(table,fields);
    }
    public static void  putPK(String table,String pk){
    	 pkmap.put(table,pk);
    }
    
    public static String  getPK(String table){
    	return pkmap.get(table);
    }

    public static List<Field> getFields(String table){
        return tables.get(table);
    }

    public static List<String> getTables(){
        Set<Map.Entry<String, List<Field>>> set=tables.entrySet();
        List<String> tables=new ArrayList<String>(set.size());
        for(Map.Entry<String,List<Field>> e:set){
            tables.add(e.getKey());
        }
        return tables;
    }

    public static void print(){
        System.out.println("一共 ："+tables.size()+" 张表。");
        Set<Map.Entry<String, List<Field>>> set=tables.entrySet();
        for(Map.Entry<String,List<Field>> e:set){
            System.out.println("表： "+e.getKey());
            System.out.println("---------------------");
            List<Field> fields=e.getValue();
            for(int i=0;i<fields.size();i++){
                System.out.println(fields.get(i).getField());
            }
        }
        System.out.println();
    }
}
