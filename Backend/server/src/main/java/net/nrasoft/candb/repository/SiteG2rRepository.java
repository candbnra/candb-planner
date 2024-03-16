package net.nrasoft.candb.repository;

import java.util.Collection;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import net.nrasoft.candb.model.SiteG2r;


@Repository
public interface SiteG2rRepository {
	
	Collection<SiteG2r> findByCode(String code) throws DataAccessException;
	
	Collection<SiteG2r> findByName(String name) throws DataAccessException;

	SiteG2r findById(Long id) throws DataAccessException;

	SiteG2r save(SiteG2r dp) throws DataAccessException;

	Collection<SiteG2r> findAll() throws DataAccessException;

	void delete(SiteG2r siteG2r) throws DataAccessException;

	Collection<SiteG2r> saveAll(Collection<SiteG2r> sitesG2r) throws DataAccessException;

	Collection<SiteG2r> findByParams(Map<String,String> params) throws DataAccessException;

}