package pure_Java_core.pure_core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//AppConfig랑 충돌나면 안되기때문에 예제 보호를 위해 사용
@ComponentScan(
        basePackages = "pure_Java_core.pure_core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
