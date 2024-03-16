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

import net.nrasoft.candb.model.Intervention;

@Repository
@Profile("jdbc")
public class InterventionRepositoryImpl implements InterventionRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertIntervention;

	@Autowired
	public InterventionRepositoryImpl(DataSource dataSource) {

		this.insertIntervention = new SimpleJdbcInsert(dataSource).withTableName("t_interventions").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Intervention findById(Long id) throws DataAccessException {
		Intervention intervention;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			intervention = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, type, dp_id, start_date, end_date, team_id, status FROM t_interventions WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(Intervention.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Intervention.class, id);
		}
		return intervention;
	}

	@Override
	public Intervention save(Intervention intervention) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(intervention);
		if (intervention.isNew()) {
			Number newKey = this.insertIntervention.executeAndReturnKey(parameterSource);
			intervention.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_interventions SET type=:type, dp_id=:dp_id, team_id=:team_id, start_date=:start_date, end_date=:end_date, status=:status "
							+ "WHERE id=:id", parameterSource);
		}
		return intervention;
	}

	@Override
	public Collection<Intervention> findAll() throws DataAccessException {
		List<Intervention> interventions = this.namedParameterJdbcTemplate.query(
				"SELECT id, type, dp, team_id, start_date, end_date, status FROM interventions",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Intervention.class));
		return interventions;
	}

	@Override
	@Transactional
	public void delete(Intervention intervention) throws DataAccessException {
		Map<String, Object> intervention_params = new HashMap<>();
		intervention_params.put("id", intervention.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_interventions WHERE id=:id", intervention_params);
	}

}