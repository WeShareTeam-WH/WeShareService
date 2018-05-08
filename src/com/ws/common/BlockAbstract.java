package com.ws.common;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.jsp.PageContext;

import com.ws.Constant;
import com.ws.util.StringUtil;

/**
 * The class is used to display template block.
 */
public abstract class BlockAbstract {

    private String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String displayBlock(PageContext pageContext) {
        execute(pageContext);
        Writer body = new StringWriter();
        try {
            if (!StringUtil.isEmpty(template.trim())) {
                pageContext.pushBody(body);
                pageContext.include(template);
                pageContext.popBody();
                return body.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                body.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Constant.BLANK;
    }

    abstract protected void execute(PageContext pageContext);
}
