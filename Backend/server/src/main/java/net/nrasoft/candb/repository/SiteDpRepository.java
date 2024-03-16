package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.SiteDp;

public interface SiteDpRepository {
	
	Collection<SiteDp> findByCode(String code) throws DataAccessException;
	Collection<SiteDp> findByName(String name) throws DataAccessException;
	SiteDp findById(Long id) throws DataAccessException;

	SiteDp save(SiteDp dp) throws DataAccessException;

	Collection<SiteDp> findAll() throws DataAccessException;

	void delete(SiteDp dp) throws DataAccessException;

}