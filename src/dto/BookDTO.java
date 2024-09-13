package dto;

public class BookDTO {
	
	private int bookId;
	private String title;
	private String author;
	private String pub;
	private String bookDesc;
	private String detail;
	private int price;
	private int inventory;
	private String genre;
	private String enroll;
	private String pubDate;
	private String bookType;
	
	public BookDTO() {
	}

	public BookDTO(int bookId, String title, String author, String pub, String bookDesc, String detail, int price,
			int inventory, String genre, String enroll, String pubDate, String bookType) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.pub = pub;
		this.bookDesc = bookDesc;
		this.detail = detail;
		this.price = price;
		this.inventory = inventory;
		this.genre = genre;
		this.enroll = enroll;
		this.pubDate = pubDate;
		this.bookType = bookType;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getEnroll() {
		return enroll;
	}

	public void setEnroll(String enroll) {
		this.enroll = enroll;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", title=" + title + ", author=" + author + ", pub=" + pub + ", bookDesc="
				+ bookDesc + ", detail=" + detail + ", price=" + price + ", inventory=" + inventory + ", genre=" + genre
				+ ", enroll=" + enroll + ", pubDate=" + pubDate + ", bookType=" + bookType + "]";
	}
}
