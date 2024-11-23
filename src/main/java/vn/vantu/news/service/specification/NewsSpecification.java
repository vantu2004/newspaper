package vn.vantu.news.service.specification;

import org.springframework.data.jpa.domain.Specification;

import vn.vantu.news.domain.News;
import vn.vantu.news.domain.News_;

public class NewsSpecification {
	public static Specification<News> nameLike(String keyword) {
		// phải thêm %temp% giống truy vấn SQL để tìm chuỗi có chứa abc
		return (root, query, criteriaBuilder) -> {
			// Sắp xếp theo pubDate giảm dần
			query.orderBy(criteriaBuilder.desc(root.get(News_.PUBDATE)));

			// Điều kiện lọc theo tiêu chí `keyword`
			return criteriaBuilder.like(criteriaBuilder.lower(root.get(News_.TITLE)),
					"%" + keyword.toLowerCase() + "%");
		};

	}
}
