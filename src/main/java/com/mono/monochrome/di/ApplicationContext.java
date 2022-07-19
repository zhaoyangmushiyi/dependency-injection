package com.mono.monochrome.di;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public interface ApplicationContext {
    Object getBean(String beanId);
}
