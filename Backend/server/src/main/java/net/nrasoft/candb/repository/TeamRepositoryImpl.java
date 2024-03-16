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

import net.nrasoft.candb.model.Team;

@Repository
@Profile("jdbc")
public class TeamRepositoryImpl implements TeamRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertTeam;

	@Autowired
	public TeamRepositoryImpl(DataSource dataSource) {

		this.insertTeam = new SimpleJdbcInsert(dataSource).withTableName("t_teams").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Collection<Team> findByName(String name) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name + "%");
		List<Team> teams = this.namedParameterJdbcTemplate.query(
				"SELECT id, name, company FROM t_teams WHERE name like :name",
				params, BeanPropertyRowMapper.newInstance(Team.class));
		return teams;
	}

	@Override
	public Team findById(Long id) throws DataAccessException {
		Team team;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			team = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, code, status, contrat FROM t_teams WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(Team.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Team.class, id);
		}
		return team;
	}

	@Override
	public void save(Team team) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(team);
		if (team.isNew()) {
			Number newKey = this.insertTeam.executeAndReturnKey(parameterSource);
			team.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_teams SET name=:name, company_id=:company_id "
							+ "WHERE id=:id", parameterSource);
		}
	}

	@Override
	public Collection<Team> findAll() throws DataAccessException {
		List<Team> teams = this.namedParameterJdbcTemplate.query(
				"SELECT id, name, company FROM teams",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Team.class));
		return teams;
	}

	@Override
	@Transactional
	public void delete(Team team) throws DataAccessException {
		Map<String, Object> team_params = new HashMap<>();
		team_params.put("id", team.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_teams WHERE id=:id", team_params);
	}

}