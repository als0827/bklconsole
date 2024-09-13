package dto;

public class OrderItemDTO {
	
	private int orderItemId;
	private int orderId;
	private int bookId;
	private String title;
	private int quantity;
	private int price; 
	
	public OrderItemDTO() {
	}

	public OrderItemDTO(int orderItemId, int orderId, int bookId, String title, int quantity, int price) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.bookId = bookId;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [orderItemId=" + orderItemId + ", orderId=" + orderId + ", bookId=" + bookId + ", title="
				+ title + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
