package com.mono.monochrome.di.impl;

import com.mono.monochrome.di.ApplicationContext;
import com.mono.monochrome.di.BeanConfigParser;
import com.mono.monochrome.di.BeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private final BeansFactory beansFactory;
    private final BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        this.loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/" + configLocation);
            if (inputStream == null) {
                throw new RuntimeException("Can not find config file: " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(inputStream);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }
}
