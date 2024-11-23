package vn.vantu.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment save (Comment comment);
	
	List<Comment> findByNewsIdOrderByCommentDatetimeDesc(long newsId);
}
