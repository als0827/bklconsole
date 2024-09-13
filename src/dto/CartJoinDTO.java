package dto;

public class CartJoinDTO {
	
	private int bookId;
	private String memId;
	private String title;
	private String author;
	private int price;
	private int quantity;
	private int totalPrice;

	public CartJoinDTO() {
	}

	public CartJoinDTO(int bookId, String memId, String title, String author, int price, int quantity, int totalPrice) {
		super();
		this.bookId = bookId;
		this.memId = memId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartJoinDTO [bookId=" + bookId + ", memId=" + memId + ", title=" + title + ", author=" + author
				+ ", price=" + price + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

}