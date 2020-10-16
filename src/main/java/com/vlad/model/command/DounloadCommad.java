package com.vlad.model.command;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class DounloadCommad implements Command {
    private final String URL = "/download";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        URL url;
//        URLConnection con;
//        DataInputStream dis;
//        FileOutputStream fos;
//        byte[] fileData;
//        try {
//            url = new URL("http://localhost:8080/resource/image/sample.pdf"); //File download Location goes here
//
//            System.out.println("URL " + url.toString());
//            con = url.openConnection(); // open the url connection.
//            dis = new DataInputStream(con.getInputStream());
//            fileData = new byte[con.getContentLength()];
//            for (int q = 0; q < fileData.length; q++) {
//                fileData[q] = dis.readByte();
//            }
//            dis.close(); // close the data input stream
//            fos = new FileOutputStream(new File("/Users/admin/sample.pdf")); //FILE Save Location goes here
//            fos.write(fileData);  // write out the file we want to save.
//            fos.close(); // close the output stream writer
//        } catch (Exception m) {
//            System.out.println(m);
//        }
        try {
            Document doc=new Document();
            PdfWriter.getInstance(doc,new FileOutputStream("listDemo.pdf"));
            doc.open();
//Create a numbered List with  30-point field for the numbers.
            List list=new List(true,30);
            list.add(new ListItem("First List"));
            list.add(new ListItem("Second List"));
            list.add(new ListItem("Third List"));
            doc.add(list);
//Add a separator.
            doc.add(Chunk.NEWLINE);
//Create a symboled List with a 30-point field for the symbols.
            list=new List(false,30);
            list.add (new ListItem ("Orange"));
            list.add (new ListItem ("Apple"));
            list.add (new ListItem ("Cherry"));
            list.add (new ListItem ("Banana"));
// Add the list to the document.
            doc.add (list);
//            try (OutputStream out = response.getOutputStream()) {
//                doc.write(bi, "png", out);
//            }
            doc.close ();
        }catch (DocumentException e){
            System.out.println(e);
        }

    }

    @Override
    public String getUrl() {
        return URL;
    }
}
