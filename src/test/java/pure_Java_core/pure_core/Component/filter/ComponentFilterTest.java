package pure_Java_core.pure_core.Component.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA bean = ac.getBean(BeanA.class);
        Assertions.assertThat(bean).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean(BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(classes = MyIncludeComponent.class),
            excludeFilters = @Filter(classes = MyExcludeComponent.class)

    )
    static class ComponentFilterAppConfig {

    }
}
