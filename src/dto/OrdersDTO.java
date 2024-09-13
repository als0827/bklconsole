package dto;

public class OrdersDTO {
	  
    private int orderId;
    private String memId;
    private int totalPrice;
    private int quantity;
    private String orderComment;
    private String orderDate;
    private String recipient;
    private String address;
    private String phone;
    
    public OrdersDTO() {
	}

	public OrdersDTO(int orderId, String memId, int totalPrice, int quantity, String orderComment, String orderDate,
			String recipient, String address, String phone) {
		super();
		this.orderId = orderId;
		this.memId = memId;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.orderComment = orderComment;
		this.orderDate = orderDate;
		this.recipient = recipient;
		this.address = address;
		this.phone = phone;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "OrdersDTO [orderId=" + orderId + ", memId=" + memId + ", totalPrice=" + totalPrice + ", quantity="
				+ quantity + ", orderComment=" + orderComment + ", orderDate=" + orderDate + ", recipient=" + recipient
				+ ", address=" + address + ", phone=" + phone + "]";
	}

}