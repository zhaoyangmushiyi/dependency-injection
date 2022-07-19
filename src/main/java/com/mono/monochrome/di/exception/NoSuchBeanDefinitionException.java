package com.mono.monochrome.di.exception;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class NoSuchBeanDefinitionException extends Exception{
    public NoSuchBeanDefinitionException(String s) {
        super(s);
    }
}
