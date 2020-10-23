package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/13.
 */
public interface ServiceService {
    void createServices() throws IOException, TemplateException;

    void createService(String table) throws IOException, TemplateException;

    void createServiceImpls() throws IOException, TemplateException;

    void createServiceImpl(String table) throws IOException, TemplateException;
}
