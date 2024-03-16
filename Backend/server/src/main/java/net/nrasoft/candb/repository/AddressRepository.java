package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Address;

public interface AddressRepository {

	Collection<Address> findByCity(String city) throws DataAccessException;
	
	Address findById(Long id) throws DataAccessException;

	Address save(Address address) throws DataAccessException;

	Collection<Address> findAll() throws DataAccessException;

	void delete(Address address) throws DataAccessException;

}