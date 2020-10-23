package me.zingon.service.impl;

import freemarker.template.TemplateException;
import me.zingon.service.ControllerService;
import me.zingon.service.CreateService;
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
public class ControllerServiceImpl implements ControllerService {

    CreateService cs=CreateServiceImpl.getInstance();
    @Override
    public void createControllers() throws IOException, TemplateException {
        String path = MyUtil.mkDir("controller");

        List<String> tables = Maps.getTables();
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            cs.write2File(root, "Controller.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Controller.java"));
        }
    }

    @Override
    public void createController(String table) throws IOException, TemplateException {
        String path = MyUtil.mkDir("controller");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("model", MyUtil.a_b2AB(table));
        cs.write2File(root, "Controller.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + "Controller.java"));
    }
}
