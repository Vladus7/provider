package com.vlad.model;

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


public enum FileManager {

    CSS {
        @Override
        public void execute(String path, String fileName, HttpServletResponse response) throws IOException {
            response.setContentType("text/css");
            try (OutputStream out = response.getOutputStream()) {
                byte[] array = Files.readAllBytes(Paths.get(path + fileName));
                out.write(array);
            }
        }
    }, IMAGE {
        @Override
        public void execute(String path, String fileName, HttpServletResponse response) throws IOException {
            response.setContentType("image/png");
            BufferedImage bi = ImageIO.read(new File(path + fileName));
            if (bi == null) return;
            try (OutputStream out = response.getOutputStream()) {
                ImageIO.write(bi, "png", out);
            }
        }

    };

    public abstract void execute(String path, String url, HttpServletResponse response) throws IOException;
}
