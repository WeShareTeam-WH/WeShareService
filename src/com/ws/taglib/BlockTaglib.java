package com.ws.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.ws.common.BlockAbstract;
import com.ws.util.SpringUtil;

/**
 * BlockTaglib class for block
 * Set the rule of attribute
 */
public class BlockTaglib extends TagSupport {

    private static final long serialVersionUID = -3600035804653408819L;
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        BlockAbstract block = (BlockAbstract) applicationContext.getBean(id);
        String content = block.displayBlock(pageContext);
        JspWriter out = pageContext.getOut();
        try {
            out.println(content);
        } catch (IOException e) {
            ////Empty block
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }
}
