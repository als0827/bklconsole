package dto;

import java.util.List;

public class OrderWithItems {
	
	private OrdersDTO order;
    private List<OrderItemDTO> items;

    public OrderWithItems(OrdersDTO order, List<OrderItemDTO> items) {
        this.order = order;
        this.items = items;
    }

    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
