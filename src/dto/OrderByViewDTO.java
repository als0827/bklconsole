package dto;

public class OrderByViewDTO {
	
	private String title;
    private String author;
    private int price;
    private String genre;
    private String pubDate;
    
    public OrderByViewDTO() {
	}

	public OrderByViewDTO(String title, String author, int price, String genre, String pubDate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.genre = genre;
		this.pubDate = pubDate;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
    
    
}
