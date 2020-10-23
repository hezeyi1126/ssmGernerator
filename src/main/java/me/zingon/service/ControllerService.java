package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/13.
 */
public interface ControllerService {

    void createControllers() throws IOException, TemplateException;

    void createController(String table) throws IOException, TemplateException;

}
