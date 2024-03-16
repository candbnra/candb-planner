package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Workstream;


public interface WorkstreamRepository {
	
	Workstream findById(Long id) throws DataAccessException;
	
	Collection<Workstream> findBySiteCode(String siteCode) throws DataAccessException;

	Workstream save(Workstream address) throws DataAccessException;
	
	Collection<Workstream> saveAll(Collection<Workstream> workstreams) throws DataAccessException;

	Collection<Workstream> findAll() throws DataAccessException;

	void delete(Workstream workstream) throws DataAccessException;
}