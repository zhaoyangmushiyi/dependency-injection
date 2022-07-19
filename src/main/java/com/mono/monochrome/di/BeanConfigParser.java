package com.mono.monochrome.di;

import java.io.InputStream;
import java.util.List;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public interface BeanConfigParser {

    List<BeanDefinition> parse(InputStream inputStream);

}
