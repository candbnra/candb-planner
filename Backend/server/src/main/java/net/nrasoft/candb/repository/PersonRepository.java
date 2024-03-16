package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Person;


public interface PersonRepository {

	Person findById(Long id) throws DataAccessException;

	Person save(Person address) throws DataAccessException;

	Collection<Person> findAll() throws DataAccessException;

	void delete(Person address) throws DataAccessException;

}