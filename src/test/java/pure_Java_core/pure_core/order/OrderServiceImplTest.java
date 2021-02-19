package pure_Java_core.pure_core.order;

import org.junit.jupiter.api.Test;
import pure_Java_core.pure_core.Order.OrderServiceImpl;

public class OrderServiceImplTest {
    @Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl(); //수정자 주입 -> 생성자 주입으로 변경
        //appConfig도 null해둔거 변경
        orderService.createOrder(1L, "Item1", 10000);
    }

}
