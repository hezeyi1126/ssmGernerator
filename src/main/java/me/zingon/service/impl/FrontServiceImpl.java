package me.zingon.service.impl;

import freemarker.template.TemplateException;
import me.zingon.service.CreateService;
import me.zingon.service.FrontService;
import me.zingon.backup.util.Maps;
import me.zingon.backup.util.MyUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by page on 2017/6/29.
 */
public class FrontServiceImpl implements FrontService{
    CreateService cs=CreateServiceImpl.getInstance();

    @Override
    public void createJss() throws IOException, TemplateException {
        String path= MyUtil.mkFrontDir("");
        List<String> tables = Maps.getTables();
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            root.put("fields", Maps.getFields(table));
            cs.write2File(root, "Js.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Script.js"));
        }
    }

    @Override
    public void createJs(String table) throws IOException, TemplateException {

    }

    @Override
    public void createJsps() throws IOException, TemplateException {
        String path= MyUtil.mkFrontDir("");
        
        List<String> tables = Maps.getTables();
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            root.put("fields", Maps.getFields(table));
            root.put("pk", Maps.getPK(table));
            root.put("pkField", MyUtil.a_b2aB(Maps.getPK(table)) );
            cs.write2File(root, "Jsp.ftl", new File(path + File.separator + MyUtil.a_b2aB(table) + ".jsp"));
        }
    }

    @Override
    public void createJsp(String table) throws IOException, TemplateException {
    	String path= MyUtil.mkFrontDir("html") ;
    	Map<String, Object> root = new HashMap<String, Object>();
        root.put("model", MyUtil.a_b2AB(table));
        root.put("fields", Maps.getFields(table));
        root.put("pk", Maps.getPK(table));
        root.put("pkField", MyUtil.a_b2aB(Maps.getPK(table)) );
        cs.write2File(root, "Jsp.ftl", new File(path + File.separator + MyUtil.a_b2aB(table) + ".html"));
    }
}
