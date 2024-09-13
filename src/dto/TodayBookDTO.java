package dto;

public class TodayBookDTO {
	
	int BookId;
    String Title;
    String Author;
    String Pub;
    int Price;
    int Sales;
    int Views;
    
    public TodayBookDTO() {
	}

	public TodayBookDTO(int bookId, String title, String author, String pub, int price, int sales, int views) {
		super();
		BookId = bookId;
		Title = title;
		Author = author;
		Pub = pub;
		Price = price;
		Sales = sales;
		Views = views;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPub() {
		return Pub;
	}

	public void setPub(String pub) {
		Pub = pub;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getSales() {
		return Sales;
	}

	public void setSales(int sales) {
		Sales = sales;
	}

	public int getViews() {
		return Views;
	}

	public void setViews(int views) {
		Views = views;
	}

	@Override
	public String toString() {
		return "TodayBookDTO [BookId=" + BookId + ", Title=" + Title + ", Author=" + Author + ", Pub=" + Pub
				+ ", Price=" + Price + ", Sales=" + Sales + ", Views=" + Views + "]";
	}
    
}
