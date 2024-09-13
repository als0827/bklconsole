package dto;

public class CartDTO {
	
	private int cartId;
	private String memId;
	private int bookId;
	private int quantity;
	
	public CartDTO() {
	}

	public CartDTO(int cartId, String memId, int bookId, int quantity) {
		super();
		this.cartId = cartId;
		this.memId = memId;
		this.bookId = bookId;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", memId=" + memId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
	}
	
}
