package cn.wxiach.beans.config;

import cn.wxiach.beans.BeanFactory;

/**
 * @author wxiach 2025/1/20
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
