package me.zingon.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by page on 2017/6/29.
 */
public interface FrontService {
    void createJss() throws IOException, TemplateException;

    void createJs(String table) throws IOException, TemplateException;

    void createJsps() throws IOException, TemplateException;

    void createJsp(String table) throws IOException, TemplateException;
}
