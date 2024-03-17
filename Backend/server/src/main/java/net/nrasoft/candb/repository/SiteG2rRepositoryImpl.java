
package net.nrasoft.candb.repository;

import java.util.ArrayList;
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

import net.nrasoft.candb.model.SiteG2r;
import net.nrasoft.candb.model.Workstream;
import net.nrasoft.candb.util.DataSourceUtils;

@Repository
@Profile("jdbc")
public class SiteG2rRepositoryImpl implements SiteG2rRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertG2r;

	private WorkstreamRepositoryImpl workstreamRepository;

	private DataSource dataSource;

	@Autowired
	public SiteG2rRepositoryImpl() {
		this.dataSource = DataSourceUtils.getDataSource();
//		this.insertG2r = new SimpleJdbcInsert(dataSource).withTableName("t_site_g2r").usingGeneratedKeyColumns("id");
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.workstreamRepository = new WorkstreamRepositoryImpl();

	}

	@Override
	public Collection<SiteG2r> findByCode(String code) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("code", '%' + code + "%");
		List<SiteG2r> sitesG2r = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, name, zone, area, region, state, porteur_prospection, bailleur, "
						+ "programme, projet, moe_projet, agence_moe, " + "equipe_planif_dsor, cdp_planif_dsor, "
						+ "equipe_patrimoine_region, acteur_patrimoine_region, rdp_region, equipe_resp_site, resp_site, inge_radio, eb"
						+ "\nFROM t_site_g2r WHERE code like :code",
				params, BeanPropertyRowMapper.newInstance(SiteG2r.class));
		return sitesG2r;

	}

	@Override
	public Collection<SiteG2r> findByParams(Map<String, String> params) throws DataAccessException {
		@SuppressWarnings("serial")
		HashMap<String, String> mapping = new HashMap<String, String>() {
			{
				put("code", "code");
				put("name", "name");
				put("region", "region");
				put("zone", "zone");
				put("area", "area");
				put("moeProjet", "moe_projet");
				put("porteurProspection", "porteur_prospection");

			}
		};
		String whereClause = "";
		for (String key : params.keySet()) {
			if (mapping.get(key) == null) {
				continue;
			}
			if (whereClause.length() != 0) {
				whereClause = whereClause + " and ";
			}
			whereClause = whereClause + " " + mapping.get(key) + " like :" + key;
		}
		String query = "SELECT id, code, name, zone, area, region, state, porteur_prospection, bailleur, "
				+ "programme, projet, moe_projet, agence_moe, " + "equipe_planif_dsor, cdp_planif_dsor, "
				+ "equipe_patrimoine_region, acteur_patrimoine_region, rdp_region, equipe_resp_site, resp_site, inge_radio, eb"
				+ " FROM t_site_g2r WHERE " + whereClause;
		System.out.println(query);

		List<SiteG2r> sitesG2r = this.namedParameterJdbcTemplate.query(query, params,
				BeanPropertyRowMapper.newInstance(SiteG2r.class));
		return sitesG2r;
	}

	@Override
	public Collection<SiteG2r> findByName(String name) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("name", '%' + name + "%");
		List<SiteG2r> sitesG2r = this.namedParameterJdbcTemplate.query(
				"SELECT id, code, name, zone, area, region, state, porteur_prospection, bailleur, "
						+ "programme, projet, moe_projet, agence_moe, " + "equipe_planif_dsor, cdp_planif_dsor, "
						+ "equipe_patrimoine_region, acteur_patrimoine_region, rdp_region, equipe_resp_site, resp_site, inge_radio, eb"
						+ "\nFROM t_site_g2r WHERE name like :name",
				params, BeanPropertyRowMapper.newInstance(SiteG2r.class));
		return sitesG2r;
	}

	@Override
	public SiteG2r findById(Long id) throws DataAccessException {
		SiteG2r siteG2r;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			siteG2r = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT id, code, name, zone, area, region, state, porteur_prospection, bailleur, "
							+ "programme, projet, moe_projet, agence_moe, " + "equipe_planif_dsor, cdp_planif_dsor, "
							+ "equipe_patrimoine_region, acteur_patrimoine_region, rdp_region, equipe_resp_site, resp_site, inge_radio, eb"
							+ "\nFROM t_site_g2r WHERE id=:id",
					params, BeanPropertyRowMapper.newInstance(SiteG2r.class));
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectRetrievalFailureException(SiteG2r.class, id);
		}
		Collection<Workstream> workstreams = workstreamRepository.findBySiteCode(siteG2r.getCode());
		siteG2r.setWorkstreams(workstreams);
		return siteG2r;
	}

	@Override
	public SiteG2r save(SiteG2r siteG2r) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(siteG2r);
		if (siteG2r.isNew()) {
			Number newKey = this.insertG2r.executeAndReturnKey(parameterSource);
			siteG2r.setId(newKey.longValue());
		} else {
			this.namedParameterJdbcTemplate.update("UPDATE t_site_g2r SET "
					+ "code=:code, name=:name, region=:region, zone=:zone, area=:area, state=:state, porteur_prospection=:porteurProspection, bailleur=:bailleur, "
					+ "programme=:programme, projet=:projet, moe_projet=:moeProjet, agence_moe=: agenceMoe, "
					+ "equipe_planif_dsor=:equipePlanifDsor, cdp_planif_dsor=:cdpPlanifDsor, "
					+ "equipe_patrimoine_region=:equipePatrimoineRegion, acteur_patrimoine_region=:acteurPatrimoineRegion, "
					+ "rdp_region=:rdpRegion, equipe_resp_site:=equipeRespSite, resp_site=:respSite, inge_radio:=ingeRadio, eb=:eb"
					+ "\nWHERE id=:id", parameterSource);
		}
		return siteG2r;
	}

	@Override
	@Transactional
	public Collection<SiteG2r> saveAll(Collection<SiteG2r> sitesG2r) throws DataAccessException {
		List<SiteG2r> result = new ArrayList<SiteG2r>();
		for (SiteG2r siteG2r : sitesG2r) {
			result.add(save(siteG2r));
		}
		return result;
	}

	@Override
	public Collection<SiteG2r> findAll() throws DataAccessException {
		List<SiteG2r> sitesG2r = this.namedParameterJdbcTemplate
				.query("SELECT id, code, name, zone, area, region, state, porteur_prospection, bailleur, "
						+ "programme, projet, moe_projet, agence_moe, " + "equipe_planif_dsor, cdp_planif_dsor, "
						+ "equipe_patrimoine_region, acteur_patrimoine_region, rdp_region, equipe_resp_site, resp_site, inge_radio, eb"
						+ "\nFROM t_site_g2r ", BeanPropertyRowMapper.newInstance(SiteG2r.class));
		return sitesG2r;
	}

	@Override
	@Transactional
	public void delete(SiteG2r siteG2r) throws DataAccessException {
		Map<String, Object> dp_params = new HashMap<>();
		dp_params.put("id", siteG2r.getId());
		this.namedParameterJdbcTemplate.update("DELETE FROM t_site_g2r WHERE id=:id", dp_params);
	}

}
