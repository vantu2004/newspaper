package vn.vantu.news.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.Comment;
import vn.vantu.news.repository.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public void handleSaveComment(Comment comment) {
		this.commentRepository.save(comment);
	}
	
	public List<Comment> getAllCommentByNewsId(long newsId) {
		return this.commentRepository.findByNewsIdOrderByCommentDatetimeDesc(newsId);
	}
}
