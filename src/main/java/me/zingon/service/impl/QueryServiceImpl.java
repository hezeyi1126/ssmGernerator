package me.zingon.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateException;
import me.zingon.backup.util.Maps;
import me.zingon.backup.util.MyUtil;
import me.zingon.model.Field;
import me.zingon.service.CreateService;
import me.zingon.service.QueryService;

public class QueryServiceImpl implements QueryService {
	 CreateService cs=CreateServiceImpl.getInstance();
	@Override
	public void createQuery(String table) throws IOException, TemplateException {
		  String path= MyUtil.mkqueryDir();
	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("model", MyUtil.a_b2AB(table));
	        root.put("table",table);
	        List<Field> fields = Maps.getFields(table);
	        root.put("fields", fields);
	        cs.write2File(root, "Query.ftl", new File(path + File.separator + MyUtil.a_b2aB(table) + "Query.xml"));
		
	}

}
