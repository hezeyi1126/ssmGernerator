package me.zingon.service.impl;

import freemarker.template.TemplateException;
import me.zingon.service.CreateService;
import me.zingon.service.DaoService;
import me.zingon.backup.util.Maps;
import me.zingon.backup.util.MyUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by page on 2017/6/13.
 */
public class DaoServiceImpl implements DaoService {
    CreateService cs=CreateServiceImpl.getInstance();

    @Override
    public void createDaos() throws IOException, TemplateException {
        String path= MyUtil.mkDir("dao");
        List<String> tables = Maps.getTables();
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            cs.write2File(root, "Dao.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Dao.java"));
        }
    }

    @Override
    public void createDao(String table) throws IOException, TemplateException {
    	
        String path= MyUtil.mkDir("dao");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("model", MyUtil.a_b2AB(table));
        
        cs.write2File(root, "Dao.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Dao.java"));
    }

    @Override
    public void createMappers() throws IOException, TemplateException {
        String path= MyUtil.mkDir("mapper");
        List<String> tables = Maps.getTables();
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            root.put("table",table);
            root.put("fields", Maps.getFields(table)); 
            root.put("pk", Maps.getPK(table));
            root.put("pkField", MyUtil.a_b2aB(Maps.getPK(table)) );
            cs.write2File(root, "Mapper.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Mapper.xml"));
        }
    }

    @Override
    public void createMapper(String table) throws IOException, TemplateException {
        String path= MyUtil.mkmapperDir();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("model", MyUtil.a_b2AB(table));
        root.put("table",table);
        root.put("fields", Maps.getFields(table));
        root.put("pk", Maps.getPK(table));
        root.put("pkField", MyUtil.a_b2aB(Maps.getPK(table)) );
        cs.write2File(root, "Mapper.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Mapper.xml"));
    }
}
