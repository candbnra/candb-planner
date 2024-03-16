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

import net.nrasoft.candb.model.Address;

@Repository
@Profile("jdbc")
public class AddressRepositoryImpl implements AddressRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertAddress;

	@Autowired
	public AddressRepositoryImpl(DataSource dataSource) {

		this.insertAddress = new SimpleJdbcInsert(dataSource).withTableName("t_addresses").usingGeneratedKeyColumns("id");

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Override
	public Collection<Address> findByCity(String city) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("city", city + "%");
		List<Address> addresses = this.namedParameterJdbcTemplate.query(
				"SELECT id, city, street, dp_id type, status FROM addresss WHERE city like :city",
				params, BeanPropertyRowMapper.newInstance(Address.class));
		return addresses;
	}


	@Override
	public Address findById(Long id) throws DataAccessException {
		Address address;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			address = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, city, street, dp FROM t_addresses WHERE id= :id", params,
					BeanPropertyRowMapper.newInstance(Address.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(Address.class, id);
		}
		return address;
	}

	@Override
	public Address save(Address address) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(address);
		if (address.isNew()) {
			Number newKey = this.insertAddress.executeAndReturnKey(parameterSource);
			address.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate
					.update("UPDATE t_addresses SET code=:code, type=:type, name=:name, "
							+ "WHERE id=:id", parameterSource);
		}
		return address;
	}

	@Override
	public Collection<Address> findAll() throws DataAccessException {
		List<Address> addresss = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, type, name FROM addresses",
				new HashMap<String, Object>(), BeanPropertyRowMapper.newInstance(Address.class));
		return addresss;
	}

	@Override
	@Transactional
	public void delete(Address address) throws DataAccessException {
		Map<String, Object> address_params = new HashMap<>();
		address_params.put("id", address.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_addresses WHERE id=:id", address_params);
	}

}
