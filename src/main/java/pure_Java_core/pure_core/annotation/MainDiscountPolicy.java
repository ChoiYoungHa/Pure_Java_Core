package pure_Java_core.pure_core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//Qualifier를 어노테이션으로 만들어서 사용하면 컴파일오류(컴파일러는 문자를 못잡음)를 쉽게 잡을 수 있고
//더욱 간결하게 사용 가능하다.
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
