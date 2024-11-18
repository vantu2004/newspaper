package vn.vantu.news.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	
	// nhiều news thuộc 1 category, giữ khóa ngoại -> owner
	@ManyToOne
	// nếu ko có annotation này thì bên mysql mặc định tự tạo 1 bảng trung gian, có
	// annotation cho mysql biết 2 table liên kết theo category_id
	// category là tên tự đặt, thực tế nó liên kết tới thuộc tính đc đặt làm id (@Id)
	// bên category
	@JoinColumn(name = "category_id")
	private Category category;

	private LocalDate pubdate;
	private LocalTime pubtime;

	private String link;
	private String image;

	// dùng mediumtext có thể lưu chuỗi nặng 4mb
	@Column(columnDefinition = "TEXT")
	private String summary;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(columnDefinition = "TEXT")
	private String author;

	// 1 tin có thể được lưu bởi nhiều user, tham chiếu tới UserNews
	@OneToMany(mappedBy = "news")
	private Set<UserNews> userNews;

	public News() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<UserNews> getUserNews() {
		return userNews;
	}

	public void setUserNews(Set<UserNews> userNews) {
		this.userNews = userNews;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Set<UserNews> getUsers() {
		return userNews;
	}

	public void setUsers(Set<UserNews> userNews) {
		this.userNews = userNews;
	}

	public LocalTime getPubtime() {
		return pubtime;
	}

	public void setPubtime(LocalTime pubtime) {
		this.pubtime = pubtime;
	}

	public LocalDate getPubdate() {
		return pubdate;
	}

	public void setPubdate(LocalDate pubdate) {
		this.pubdate = pubdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
