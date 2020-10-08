package com.vlad.model.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Resource implements Command{
    private final String URL = "/resource";
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] array = request.getServletPath().split("/");
        String resourceType = array[2].toUpperCase();
        String path = request.getServletContext().getRealPath(File.separator);
        path = path.replace("\\","/");
        path = path.replace("/target/provider","");
        FileManager fileManager = null;
        if("CSS".equals(resourceType)){
        fileManager = FileManager.CSS;
        path = path+"/src/main/webapp/WEB-INF/style/css/";}
        if ("IMAGE".equals(resourceType)){
        fileManager = FileManager.IMAGE;
        path = path+"/src/main/webapp/WEB-INF/style/images/";}
        fileManager.execute(path, array[3], response);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
