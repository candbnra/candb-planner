package net.nrasoft.candb.model;

import java.sql.Date;

public class Workstream extends BaseEntity {

	protected String codeG2r;
	protected String codeActivite;
	protected String nomActivite;

	protected String status;
	protected Date startDate;
	protected Date endDate;
	protected String resp;
	protected String respTeam;

	protected Site site;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public String getCodeG2r() {
		return codeG2r;
	}

	public void setCodeG2r(String codeG2r) {
		this.codeG2r = codeG2r;
	}

	public String getCodeActivite() {
		return codeActivite;
	}

	public void setCodeActivite(String codeActivite) {
		this.codeActivite = codeActivite;
		this.nomActivite  = getActivityName(codeActivite);
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getRespTeam() {
		return respTeam;
	}

	public void setRespTeam(String respTeam) {
		this.respTeam = respTeam;
	}

	public String getNomActivite() {
		return this.nomActivite;
	}
	
	
	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	public static String getActivityName(String activityCode) {
		if ("ARA_DOSSIER_INFORMATION_MAIRIE".equals(activityCode)) {
			return "DIM";
		} else {
			return toTitleCase(activityCode);
		}
		
	}

	private static String toTitleCase(String givenString) {
	    String[] arr = givenString.toLowerCase().split("_");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}  
}
