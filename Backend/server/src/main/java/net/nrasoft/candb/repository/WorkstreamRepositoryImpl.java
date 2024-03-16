package net.nrasoft.candb.repository;

import java.util.ArrayList;
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

import net.nrasoft.candb.model.Workstream;
import net.nrasoft.candb.util.DataSourceUtils;

@Repository
@Profile("jdbc")
public class WorkstreamRepositoryImpl implements WorkstreamRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertWorkstream;
 
	private DataSource dataSource;
	
	@Autowired
	public WorkstreamRepositoryImpl() {
		this.dataSource = DataSourceUtils.getDataSource();
		this.insertWorkstream = new SimpleJdbcInsert(dataSource).withTableName("t_workstream_g2r")
				.usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Collection<Workstream> findBySiteCode(String siteCode) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("siteCode", siteCode.trim());
		Collection<Workstream> workstreams = this.namedParameterJdbcTemplate.query(
				"SELECT id, code_g2r, code_activite, status, start_date, end_date, resp, resp_team FROM t_workstream_g2r WHERE code_g2r=:siteCode",
				params, BeanPropertyRowMapper.newInstance(Workstream.class));
		for (Workstream workstream : workstreams) {
			workstream.setNomActivite(Workstream.getActivityName(workstream.getCodeActivite()));
		}
		return workstreams;
	}

	@Override
	public Workstream findById(Long id) throws DataAccessException {
		Workstream workstream;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			workstream = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, code_g2r, code_activite, status, start_date, end_date, status, start_date, end_date, resp, resp_team FROM t_workstream_g2r WHERE id= :id",
					params, BeanPropertyRowMapper.newInstance(Workstream.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Workstream.class, id);
		}
		workstream.setNomActivite(Workstream.getActivityName(workstream.getCodeActivite()));
		return workstream;
	}

	@Override
	public Workstream save(Workstream workstream) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(workstream);
		if (workstream.isNew()) {
			Number newKey = this.insertWorkstream.executeAndReturnKey(parameterSource);
			workstream.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate.update(
					"UPDATE t_workstream_g2r SET code_g2r=:codeSite, code_activite=:codeActivite, status=:status, start_date=:startDate, end_date=:endDate,resp_team=:respTeam, resp=:resp "
							+ "WHERE id=:id",
					parameterSource);
		}
		workstream.setNomActivite(Workstream.getActivityName(workstream.getCodeActivite()));
		return workstream;
	}

	@Override
	public Collection<Workstream> findAll() throws DataAccessException {
		List<Workstream> workstreams = this.namedParameterJdbcTemplate.query(
				"SELECT id, code_g2r, code_activite, status, start_date, end_date, status, resp, resp_team  FROM t_workstream_g2r where id < 50690",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Workstream.class));
		for (Workstream workstream : workstreams) {
			workstream.setNomActivite(Workstream.getActivityName(workstream.getCodeActivite()));
		}
		return workstreams;
	}

	@Override
	@Transactional
	public void delete(Workstream workstream) throws DataAccessException {
		Map<String, Object> workstream_params = new HashMap<>();
		workstream_params.put("id", workstream.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_workstream_g2r WHERE id=:id", workstream_params);
	}

	@Override
	@Transactional
	public Collection<Workstream> saveAll(Collection<Workstream> workstreams) throws DataAccessException {
		List<Workstream> result = new ArrayList<Workstream>();
		for (Workstream workstream : workstreams) {
			result.add(save(workstream));
		}
		return result;
	}
}
