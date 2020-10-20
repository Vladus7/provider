package com.vlad.model.command;

import com.vlad.model.FileManager;
import com.vlad.model.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ResourceCommand implements Command {
    private final String URL = "/resource";
    private final static Logger logger = Logger.getLogger(ResourceCommand.class);


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Process started");
        String[] array = request.getServletPath().split("/");
        String resourceType = array[2].toUpperCase();
        String path = request.getServletContext().getRealPath(File.separator);
        path = path.replace("\\", "/");
        path = path.replace("/target/provider", "");
        FileManager fileManager;

        if ("CSS".equals(resourceType)) {
            fileManager = FileManager.CSS;
            path = path + "/src/main/webapp/WEB-INF/style/css/";
        } else if ("IMAGE".equals(resourceType)) {
            fileManager = FileManager.IMAGE;
            path = path + "/src/main/webapp/WEB-INF/style/images/";
        } else {
            System.out.println("Exception");
            return;
        }

        fileManager.execute(path, array[3], response);
    }

    /**
     * @return Name of the command.
     */
    @Override
    public String getUrl() {
        return URL;
    }
}
