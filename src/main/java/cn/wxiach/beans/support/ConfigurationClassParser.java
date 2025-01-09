package cn.wxiach.beans.support;

import cn.wxiach.annotations.ComponentScan;
import cn.wxiach.beans.BeanDefinition;
import cn.wxiach.beans.BeanDefinitionRegistry;
import cn.wxiach.context.ClassPathBeanDefinitionScanner;

import java.util.List;

/**
 * 
 * @author wxiach 2025/1/10
 */
public class ConfigurationClassParser {
    private final BeanDefinitionRegistry registry;

    public ConfigurationClassParser(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void parse(List<BeanDefinition> configurationClasses) {
        configurationClasses.forEach(beanDefinition -> {
            parseConfigurationClass(beanDefinition.getBeanClass());
        });
    }

    private void parseConfigurationClass(Class<?> configurationClass) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this.registry);
        if (configurationClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = configurationClass.getAnnotation(ComponentScan.class);
            String basePackageName = componentScan.value();
            if (basePackageName == null || basePackageName.isEmpty()) {
                basePackageName = configurationClass.getPackage().getName();
            }
            scanner.scanPackage(basePackageName);
        }
    }
}
