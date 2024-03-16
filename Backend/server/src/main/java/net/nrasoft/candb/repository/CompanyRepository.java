package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Company;

public interface CompanyRepository {

	Collection<Company> findByName(String name) throws DataAccessException;

	Company findById(Long id) throws DataAccessException;

	Company save(Company company) throws DataAccessException;

	Collection<Company> findAll() throws DataAccessException;

	void delete(Company company) throws DataAccessException;

}
