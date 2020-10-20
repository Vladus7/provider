package com.vlad.model.command;

import com.vlad.model.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    public void process(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException, AppException;

    /**
     * @return Name of the command.
     */
    public String getUrl();
}
