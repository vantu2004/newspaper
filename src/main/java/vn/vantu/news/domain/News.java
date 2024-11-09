package vn.vantu.news.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String category;
	
	private LocalDate pubdate;
	private LocalTime pubtime;
	
	private String link;
	private String image;

	//	dùng mediumtext có thể lưu chuỗi nặng 4mb
	@Column(columnDefinition = "MEDIUMTEXT")
	private String summary;

	//	1 tin có thể được lưu bởi nhiều user, tham chiếu tới UserNews
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public void setPubdate(LocalDate pubdate) {
		this.pubdate = pubdate;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", category=" + category + ", pubdate=" + pubdate + ", pubtime="
				+ pubtime + ", link=" + link + ", image=" + image + ", summary=" + summary + "]";
	}

}
