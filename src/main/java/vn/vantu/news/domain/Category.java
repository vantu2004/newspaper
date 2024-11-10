package vn.vantu.news.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nameCategory;
	
    //	1 category có nhiều news, ko nắm khóa ngoại -> mapped by news
    //	mối liên kết giữa news và category là "category"
    @OneToMany(mappedBy = "category")
    Set<News> news;

	// 1 category có thể lưu được dùng bởi nhiều user, tham chiếu tới UserCategory
	@OneToMany(mappedBy = "category")
	private Set<UserCategory> UserCategory;
    
	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public Set<UserCategory> getUserCategory() {
		return UserCategory;
	}

	public void setUserCategory(Set<UserCategory> userCategory) {
		UserCategory = userCategory;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
}
