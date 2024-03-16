package net.nrasoft.candb.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Team;

public interface TeamRepository {

	Collection<Team> findByName(String name) throws DataAccessException;

	Team findById(Long id) throws DataAccessException;

	void save(Team team) throws DataAccessException;

	Collection<Team> findAll() throws DataAccessException;

	void delete(Team team) throws DataAccessException;

}