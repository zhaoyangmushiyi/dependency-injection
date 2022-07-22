package com.mono.monochrome.di.impl;

import com.mono.monochrome.di.BeanConfigParser;
import com.mono.monochrome.di.BeanDefinition;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    private SAXReader reader = null;

    public XmlBeanConfigParser() {
        this.reader = new SAXReader();
    }

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        try {
            Document document = reader.read(inputStream);// 获取根节点
            Element beans = document.getRootElement();
            // 获取迭代器
            Iterator beanIt = beans.elementIterator();
            // 遍历迭代器，获取根节点信息
            while(beanIt.hasNext()){
                BeanDefinition beanDefinition = new BeanDefinition();
                Element bean = (Element) beanIt.next();

                List<Attribute> attributes = bean.attributes();
                // 获取bean属性名和属性值
                for (Attribute attribute : attributes) {
                    if ("id".equals(attribute.getName())) {
                        beanDefinition.setId(attribute.getValue());
                    } else if ("class".equals(attribute.getName())) {
                        beanDefinition.setClassName(attribute.getValue());
                    }
                    System.out.println("节点名："+attribute.getName()+"节点值："+attribute.getValue());
                }
                Iterator argsIt = bean.elementIterator();
                List<BeanDefinition.ConstructorArg> constructorArgs = new ArrayList<>();
                while(argsIt.hasNext()){
                    BeanDefinition.ConstructorArg.Builder builder = new BeanDefinition.ConstructorArg.Builder();
                    Element arg = (Element) argsIt.next();
                    List<Attribute> argAttributes = arg.attributes();
                    // 获取bean属性名和属性值
                    for (Attribute attribute : argAttributes) {
                        if ("type".equals(attribute.getName())) {
                            try {
                                builder.setType(Class.forName(attribute.getValue()));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            builder.setArg(arg.getText());
                        } else if ("ref".equals(attribute.getName())) {
                            builder.setRef(true);
                            builder.setArg(attribute.getValue());
                        }
                        System.out.println("节点名："+attribute.getName()+"节点值："+attribute.getValue());
                    }
                    constructorArgs.add(builder.build());
                    beanDefinition.setConstructorArgs(constructorArgs);
                }
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return beanDefinitions;
    }
}
