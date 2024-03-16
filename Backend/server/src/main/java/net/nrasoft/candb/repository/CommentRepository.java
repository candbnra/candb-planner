package net.nrasoft.candb.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Comment;

public interface CommentRepository {

	Comment findById(Long id) throws DataAccessException;

	Comment save(Comment comment) throws DataAccessException;

	Collection<Comment> findAll() throws DataAccessException;

	void delete(Comment comment) throws DataAccessException;

}