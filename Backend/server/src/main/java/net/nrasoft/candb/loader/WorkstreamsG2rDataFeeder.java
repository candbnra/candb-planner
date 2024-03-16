package net.nrasoft.candb.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;

import net.nrasoft.candb.model.Workstream;
import net.nrasoft.candb.repository.WorkstreamRepositoryImpl;
import net.nrasoft.candb.repository.WorkstreamRepository;

public class WorkstreamsG2rDataFeeder {
	
	public static void main(String[] args) throws DataAccessException, IOException, ParseException {
		new WorkstreamsG2rDataFeeder().loadWorkstreamsFromFile();
	}

	public void loadWorkstreamsFromFile() throws IOException, ParseException, DataAccessException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				new ClassPathResource("db/t_workstream_g2r_data.csv").getInputStream(), "ISO-8859-1"));

		try (CSVParser csvParser = new CSVParser(bReader,
				CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			List<Workstream> workstreams = new ArrayList<>();
			for (CSVRecord csvRecord : csvRecords) {
				workstreams.addAll(getWorkstreamsFromCsvRecord(csvRecord));
			}
			WorkstreamRepository repository = new WorkstreamRepositoryImpl();
			repository.saveAll(workstreams);
		}

	}

	private List<Workstream> getWorkstreamsFromCsvRecord(CSVRecord csvRecord) throws ParseException {
		List<Workstream> workstreams = new ArrayList<>();
		for (String activityCode : ACTIVITIES) {
			workstreams.add(getWorkstreamFromCsvRecord(csvRecord, activityCode, WorkstreamsG2rFileMetadata.MAPPING,
					new SimpleDateFormat("dd/MM/yyyy")));
		}
		return workstreams;
	}

	private Workstream getWorkstreamFromCsvRecord(CSVRecord csvRecord, String activityCode,
			Map<String, String> fieldsMapping, DateFormat dateFormat) throws ParseException {
		Workstream workstream = new Workstream();
		workstream.setCodeG2r(csvRecord.get("G2R"));
		workstream.setCodeActivite(activityCode);

		try {
			String statut = csvRecord.get(activityCode + "_STATUT");
			workstream.setStatus(statut);
		} catch (IllegalArgumentException e) {
			System.out.println("Error for G2R " + workstream.getCodeG2r() + ":" + e.getMessage());
		}
		try {
			if (csvRecord.get(activityCode + "_DATE_SOUMISSION") != null
					&& csvRecord.get(activityCode + "_DATE_SOUMISSION").length() > 0) {
				workstream.setStartDate(new java.sql.Date(
						dateFormat.parse(csvRecord.get(activityCode + "_DATE_SOUMISSION")).getTime()));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error for G2R " + workstream.getCodeG2r() + ":" + e.getMessage());
		}
		try {
			if (csvRecord.get(activityCode + "_DATE_REALISATION") != null
					&& csvRecord.get(activityCode + "_DATE_REALISATION").length() > 0) {

				workstream.setEndDate(new java.sql.Date(
						dateFormat.parse(csvRecord.get(activityCode + "_DATE_REALISATION")).getTime()));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error for G2R " + workstream.getCodeG2r() + ":" + e.getMessage());
		}

		try {
			if (!ACTIVITIES_SANS_PERSONNE_EN_CHARGE.contains(activityCode) && csvRecord.get(activityCode + "_PERSONNE_EN_CHARGE") != null
					&& csvRecord.get(activityCode + "_PERSONNE_EN_CHARGE").length() > 0) {
				workstream.setRespTeam(csvRecord.get(activityCode + "_PERSONNE_EN_CHARGE"));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error for G2R " + workstream.getCodeG2r() + ":" + e.toString());
		}
		try {
			if (!ACTIVITIES_SANS_EQUIPE_EN_CHARGE.contains(activityCode) && csvRecord.get(activityCode + "_EQUIPE_EN_CHARGE") != null
					&& csvRecord.get(activityCode + "_EQUIPE_EN_CHARGE").length() > 0) {
				workstream.setRespTeam(csvRecord.get(activityCode + "_EQUIPE_EN_CHARGE"));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error for G2R " + workstream.getCodeG2r() + ":" + e.toString());
		}
		return workstream;
	}

	private List<String> ACTIVITIES = Arrays.asList(new String[] { "REMONTEE_DE_PROSPECTS", "AVIS_RADIO", "ARBITRAGE_PROSPECT",
			"AVIS_TRANS", "ACCORD_DE_PRINCIPE", "VT", "COMITE_SITE_NEUF", "CHOIX_PROSPECT", "ENVOI_EB", "REPONSE_EBS",
			"REDACTION_DTB", "VALIDATION_DTB", "DEVIS_TS", "DOSSIER_INFORMATION_MAIRIE", "DEPOT_ADMIN", "AUTO_ADMIN",
			"DEMANDE_ENEDIS", "DEVIS_ENEDIS", "SIGNATURE_CONVENTION", "GO_REALISATION", "MAD_INFRA",
			"MAD_ENEDIS_DEFINITIF", "MES", "RECETTE", "PARFAIT_ACHEVEMENT" });
	
	private List<String> ACTIVITIES_SANS_PERSONNE_EN_CHARGE = Arrays.asList(new String[] { "REMONTEE_DE_PROSPECTS","AVIS_RADIO", "ARBITRAGE_PROSPECT",
			"AVIS_TRANS", "ACCORD_DE_PRINCIPE", "VT", "COMITE_SITE_NEUF", "CHOIX_PROSPECT","ENVOI_EB","DEMANDE_ENEDIS"});
	
	private List<String> ACTIVITIES_SANS_EQUIPE_EN_CHARGE = Arrays.asList(new String[] {"REMONTEE_DE_PROSPECTS", "AVIS_RADIO", "ARBITRAGE_PROSPECT",
			"AVIS_TRANS", "ACCORD_DE_PRINCIPE", "VT", "COMITE_SITE_NEUF", "CHOIX_PROSPECT"});
}
