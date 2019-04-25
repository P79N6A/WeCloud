package com.bat.parser;

import com.bat.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Author: caoke
 * @Date: 2019/4/25 15:30
 * @Version 1.0
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String desc = element.getAttribute("description");

        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("id is required");
        }
        builder.addPropertyValue("id",id);
        builder.addPropertyValue("name",name);
        builder.addPropertyValue("description",desc);
    }
}
