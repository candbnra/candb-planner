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

import net.nrasoft.candb.model.SiteDp;

@Repository
@Profile("jdbc")
public class SiteDpRepositoryImpl implements SiteDpRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertDp;

	@Autowired
	public SiteDpRepositoryImpl(DataSource dataSource) {

		this.insertDp = new SimpleJdbcInsert(dataSource).withTableName("t_dps").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Collection<SiteDp> findByCode(String code) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("code", code + "%");
		List<SiteDp> dps = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, status, contrat FROM t_dps WHERE code like :code",
				params, BeanPropertyRowMapper.newInstance(SiteDp.class));
		return dps;
	}
	
	
	@Override
	public Collection<SiteDp> findByName(String name) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name + "%");
		List<SiteDp> dps = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, status, contrat FROM t_dps WHERE name like :name",
				params, BeanPropertyRowMapper.newInstance(SiteDp.class));
		return dps;
	}



	@Override
	public SiteDp findById(Long id) throws DataAccessException {
		SiteDp dp;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			dp = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, code, status, contrat FROM t_dps WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(SiteDp.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(SiteDp.class, id);
		}
		return dp;
	}

	@Override
	public SiteDp save(SiteDp dp) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(dp);
		if (dp.isNew()) {
			Number newKey = this.insertDp.executeAndReturnKey(parameterSource);
			dp.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_dps SET code=:code, type=:type, name=:name, contrat_id=:contrat_id "
							+ "WHERE id=:id", parameterSource);
		}
		return dp;
	}

	@Override
	public Collection<SiteDp> findAll() throws DataAccessException {
		List<SiteDp> dps = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, status, contract_id FROM dps",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(SiteDp.class));
		return dps;
	}

	@Override
	@Transactional
	public void delete(SiteDp dp) throws DataAccessException {
		Map<String, Object> dp_params = new HashMap<>();
		dp_params.put("id", dp.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_dps WHERE id=:id", dp_params);
	}


}
