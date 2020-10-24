package me.zingon.service.impl;

import freemarker.template.TemplateException;
import me.zingon.service.CreateService;
import me.zingon.service.ModelService;
import me.zingon.backup.util.Maps;
import me.zingon.backup.util.MyUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by page on 2017/6/12.
 */
public class ModelServiceImpl implements ModelService {
    CreateService cs = CreateServiceImpl.getInstance();

    @Override
    public int createModels() throws IOException, TemplateException {
        String path = MyUtil.mkDir("model");

        List<String> tables = Maps.getTables();
        
        for (String table : tables) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("model", MyUtil.a_b2AB(table));
            root.put("fields", Maps.getFields(table));
            cs.write2File(root, "Model.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + ".java"));
        }
        return 0;
    }

    @Override
    public int createModel(String table) throws IOException, TemplateException {
        String path = MyUtil.mkDir("entity");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("table", table);
        root.put("model", MyUtil.a_b2AB(table));
        root.put("fields", Maps.getFields(table));
        cs.write2File(root, "Model.ftl", new File(path + File.separator + MyUtil.a_b2AB(table) + ".java"));
        return 0;
    }
}
