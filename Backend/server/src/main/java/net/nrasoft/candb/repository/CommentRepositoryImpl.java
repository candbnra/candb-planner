package net.nrasoft.candb.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.nrasoft.candb.model.Comment;

@Repository
@Profile("jdbc")
public class CommentRepositoryImpl implements CommentRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertComment;

	@Autowired
	public CommentRepositoryImpl(DataSource dataSource) {

		this.insertComment = new SimpleJdbcInsert(dataSource).withTableName("t_commentes").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Comment findById(Long id) throws DataAccessException {
		Comment comment;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			comment = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, entryDate, author, comment FROM t_comments WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(Comment.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Comment.class, id);
		}
		return comment;
	}

	@Override
	public Comment save(Comment comment) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
		if (comment.isNew()) {
			Number newKey = this.insertComment.executeAndReturnKey(parameterSource);
			comment.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_comments SET entryDate=:entryDate, author=:author, comment=:comment, "
							+ "WHERE id=:id", parameterSource);
		}
		return comment;
	}

	@Override
	public Collection<Comment> findAll() throws DataAccessException {
		List<Comment> comments = this.namedParameterJdbcTemplate.query(
				"SELECT id, entry_date, author, comment FROM commentes",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Comment.class));
		return comments;
	}

	@Override
	@Transactional
	public void delete(Comment comment) throws DataAccessException {
		Map<String, Object> comment_params = new HashMap<>();
		comment_params.put("id", comment.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_comments WHERE id=:id", comment_params);
	}

}
