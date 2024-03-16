package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Intervention;

public interface InterventionRepository {

	Intervention findById(Long id) throws DataAccessException;

	Intervention save(Intervention intervention) throws DataAccessException;

	Collection<Intervention> findAll() throws DataAccessException;

	void delete(Intervention intervention) throws DataAccessException;

}
