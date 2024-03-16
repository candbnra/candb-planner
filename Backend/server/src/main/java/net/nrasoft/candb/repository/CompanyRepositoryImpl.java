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

import net.nrasoft.candb.model.Company;

@Repository
@Profile("jdbc")
public class CompanyRepositoryImpl implements CompanyRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertCompany;

	@Autowired
	public CompanyRepositoryImpl(DataSource dataSource) {

		this.insertCompany = new SimpleJdbcInsert(dataSource).withTableName("t_companies").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Collection<Company> findByName(String name) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name + "%");
		List<Company> companies = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, type, name FROM t_companies WHERE name like :name",
				params, BeanPropertyRowMapper.newInstance(Company.class));
		return companies;
	}


	@Override
	public Company findById(Long id) throws DataAccessException {
		Company company;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			company = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, code, type, name FROM t_companies WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(Company.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Company.class, id);
		}
		return company;
	}

	@Override
	public Company save(Company company) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(company);
		if (company.isNew()) {
			Number newKey = this.insertCompany.executeAndReturnKey(parameterSource);
			company.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_companies SET code=:code, type=:type, name=:name "
							+ "WHERE id=:id", parameterSource);
		}
		return company;
	}

	@Override
	public Collection<Company> findAll() throws DataAccessException {
		List<Company> companies = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, type, name FROM t_companies",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Company.class));
		return companies;
	}

	@Override
	@Transactional
	public void delete(Company company) throws DataAccessException {
		Map<String, Object> company_params = new HashMap<>();
		company_params.put("id", company.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_companies WHERE id=:id", company_params);
	}

}
