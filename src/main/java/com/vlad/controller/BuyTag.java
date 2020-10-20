package com.vlad.controller;

import com.vlad.model.dao.entity.Tariff;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import java.util.List;

/**
 *
 * @param list, massage
 *       if list = 0
 * @return massage.
 */
public class BuyTag extends SimpleTagSupport {
    private String message;
    private List<Tariff> list;

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setList(List<Tariff> list) {
        this.list = list;
    }

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {
        if (list.size() == 0) {
            JspWriter out = getJspContext().getOut();
            out.println("<div class=\"message\">" + message + "</div>");
        } else {
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}
