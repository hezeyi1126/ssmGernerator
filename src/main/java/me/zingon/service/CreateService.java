package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by page on 2017/6/13.
 */
public interface CreateService {
    String getString(Map<String,Object> root,String ftlName) throws IOException, TemplateException;

    void write2File(Map<String,Object> root, String ftlName, File file) throws IOException, TemplateException;
}
