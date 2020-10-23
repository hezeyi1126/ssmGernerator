package me.zingon.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import me.zingon.model.Field;
import me.zingon.backup.util.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**mysql
 * Created by page on 2017/6/12.
 */
public class TableDao {

    Log log=LogFactory.getLog(TableDao.class);

    public void loadTables(){
        String sql="show tables;";
        PreparedStatement ps=null;
        try {
            ps=DBcon.getCon().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            	if(rs.getString(1).contains("__")) {//跳过含有__的表
            		 continue;
            	}
                Maps.put(rs.getString(1),loadFields(rs.getString(1)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Field> loadFields(String table){
        String sql="show full columns from `"+table+"`;";
        PreparedStatement ps=null;
        List<Field> fields=new ArrayList<Field>();
        try {
            ps=DBcon.getCon().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            int index = 0;
            while(rs.next()){
            	
                Field field=new Field();
                field.setField(rs.getString(1));
                field.setType(rs.getString(2));
                field.setCollation(rs.getString(3));
                field.setNvll(rs.getString(4));
                field.setKey(rs.getString(5));
                field.setDefaults(rs.getString(6));
                field.setExtra(rs.getString(7));
                field.setPrivileges(rs.getString(8));
                field.setComment(rs.getString(9));
                fields.add(field);
                if(rs.getString("Key").equals("PRI") && index ==0) {
            		Maps.putPK(table, rs.getString(1));
            		index++;
            	}
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }
}
