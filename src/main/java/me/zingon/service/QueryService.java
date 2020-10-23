package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/13.
 */
public interface QueryService {
    void createQuery(String table) throws IOException, TemplateException;

}
