package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/12.
 */
public interface ModelService {
    int createModels() throws IOException, TemplateException;

    int createModel(String table) throws IOException, TemplateException;
}
