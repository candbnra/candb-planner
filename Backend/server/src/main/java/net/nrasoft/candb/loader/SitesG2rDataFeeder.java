package net.nrasoft.candb.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import net.nrasoft.candb.model.SiteG2r;
import net.nrasoft.candb.repository.SiteG2rRepositoryImpl;
import net.nrasoft.candb.repository.SiteG2rRepository;

public class SitesG2rDataFeeder {

	public static void main(String[] args) throws DataAccessException, IOException, ParseException {
		new SitesG2rDataFeeder().loadSitesG2rFromFile();
	}
	
	public void loadSitesG2rFromFile() throws IOException, ParseException, DataAccessException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				new ClassPathResource("db/t_site_g2r_data.csv").getInputStream(), "ISO-8859-1"));

		try (CSVParser csvParser = new CSVParser(bReader,
				CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			List<SiteG2r> sites = new ArrayList<>();
			for (CSVRecord csvRecord : csvRecords) {
				sites.add(getSiteG2rFromCsvRecord(csvRecord));
			}
			SiteG2rRepository repository = new SiteG2rRepositoryImpl();
			repository.saveAll(sites);
		}

	}

	private SiteG2r getSiteG2rFromCsvRecord(CSVRecord csvRecord) throws ParseException {
		SiteG2r siteG2r = new SiteG2r();
		siteG2r.setCode(csvRecord.get("code"));
		siteG2r.setName(csvRecord.get("name"));
		siteG2r.setRegion(csvRecord.get("region"));
		siteG2r.setZone(csvRecord.get("zone"));
		siteG2r.setArea(csvRecord.get("area"));
		siteG2r.setState(csvRecord.get("state"));
		siteG2r.setPorteurProspection(csvRecord.get("porteur_prospection"));
		siteG2r.setBailleur(csvRecord.get("bailleur"));
		siteG2r.setProgramme(csvRecord.get("programme"));
		siteG2r.setProjet(csvRecord.get("projet"));
		siteG2r.setMoeProjet(csvRecord.get("moe_projet"));
		siteG2r.setAgenceMoe(csvRecord.get("agence_moe"));
		siteG2r.setEquipePlanifDsor(csvRecord.get("equipe_planif_dsor"));
		siteG2r.setCdpPlanifDsor(csvRecord.get("cdp_planif_dsor"));
		siteG2r.setEquipePatrimoineRegion(csvRecord.get("equipe_patrimoine_region"));
		siteG2r.setActeurPatrimoineRegion(csvRecord.get("acteur_patrimoine_region"));
		siteG2r.setRdpRegion(csvRecord.get("rdp_region"));
		siteG2r.setEquipeRespSite(csvRecord.get("equipe_resp_site"));
		siteG2r.setRespSite(csvRecord.get("resp_site"));
		siteG2r.setIngeRadio(csvRecord.get("inge_radio"));
		siteG2r.setEb(csvRecord.get("eb"));
		return siteG2r;
	}

}
