package scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithProtoTypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
    }

        @Test
        void singletonClientUsePrototype(){
            AnnotationConfigApplicationContext ac
                    = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
            ClientBean clientBean1 = ac.getBean(ClientBean.class);
            int logic1 = clientBean1.logic();
            assertThat(logic1).isEqualTo(1);

            ClientBean clientBean2 = ac.getBean(ClientBean.class);
            int logic2 = clientBean1.logic();
            assertThat(logic2).isEqualTo(1);


        }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;  // 생성자 주입 시점에 x01

        private final Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

        @Scope("prototype")
        static class PrototypeBean{
            private int count = 0;

            public void addCount() {
                count++;
            }

            public int getCount() {
                return count;
            }

            @PostConstruct
            public void init(){
                System.out.println("PrototypeBean.init" + this);
            }

            @PreDestroy
            public void destroy(){
                System.out.println("PrototypeBean.destroy");
            }
        }
}
