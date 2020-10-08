package com.vlad.model.command;

import javafx.scene.shape.Path;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


enum  FileManager{//} implements Command {
    //private final String URL= "/resource/css/login.css";

    CSS{
        @Override
    public void execute(String path, String fileName, HttpServletResponse response) throws  IOException {
        response.setContentType("text/css");
        try(OutputStream out = response.getOutputStream()){
            byte[] array = Files.readAllBytes(Paths.get(path+fileName));
            out.write(array);
        }
    }},IMAGE{

        @Override
    public void execute(String path, String fileName, HttpServletResponse response) throws IOException {
//        String path = request.getServletContext().getRealPath(File.separator);
//        path = path.replace("\\","/");
//        path = path.replace("/target/provider","");
        response.setContentType("image/png");
//        BufferedImage bi = ImageIO.read(new File(path+"/src/main/webapp/WEB-INF/style/images/bigLogo.png"));
        BufferedImage bi = ImageIO.read(new File(path+fileName));
        if (bi == null) return;
        try(OutputStream out = response.getOutputStream()){
            ImageIO.write(bi,"png",out);
        }
    }

    };
    public abstract void execute(String path, String url, HttpServletResponse response)  throws IOException;
}
