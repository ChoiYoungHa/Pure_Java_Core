package pure_Java_core.pure_core.Order;

public interface OrderService {
    Order createOrder(long memberId, String itemName, int price);
}
