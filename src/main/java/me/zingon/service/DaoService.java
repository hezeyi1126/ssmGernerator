package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/13.
 */
public interface DaoService {

    void createDaos() throws IOException, TemplateException;

    void createDao(String table) throws IOException, TemplateException;

    void createMappers() throws IOException, TemplateException;

    void createMapper(String table) throws IOException, TemplateException;
}
