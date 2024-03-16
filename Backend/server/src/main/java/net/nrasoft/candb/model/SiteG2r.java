package net.nrasoft.candb.model;

import java.util.Collection;

public class SiteG2r extends NamedEntity {

	private String code;
	private String region;
	private String area;
	private String zone;
	private String state;
	private String porteurProspection;
	private String bailleur;
	private String programme;
	private String projet;
	private String moeProjet;
	private String agenceMoe;
	private String equipePlanifDsor;
	private String cdpPlanifDsor;
	private String equipePatrimoineRegion;
	private String acteurPatrimoineRegion;
	private String rdpRegion;
	private String equipeRespSite;
	private String respSite;
	private String ingeRadio;
	private String eb;
	
	
	
	
	private Collection<Workstream> workstreams;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

	public String getPorteurProspection() {
		return porteurProspection;
	}

	public void setPorteurProspection(String porteurProspection) {
		this.porteurProspection = porteurProspection;
	}

	public String getBailleur() {
		return bailleur;
	}

	public void setBailleur(String bailleur) {
		this.bailleur = bailleur;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}

	public String getMoeProjet() {
		return moeProjet;
	}

	public void setMoeProjet(String moeProjet) {
		this.moeProjet = moeProjet;
	}

	public String getAgenceMoe() {
		return agenceMoe;
	}

	public void setAgenceMoe(String agenceMoe) {
		this.agenceMoe = agenceMoe;
	}

	public String getEquipePlanifDsor() {
		return equipePlanifDsor;
	}

	public void setEquipePlanifDsor(String equipePlanifDsor) {
		this.equipePlanifDsor = equipePlanifDsor;
	}

	public String getCdpPlanifDsor() {
		return cdpPlanifDsor;
	}

	public void setCdpPlanifDsor(String cdpPlanifDsor) {
		this.cdpPlanifDsor = cdpPlanifDsor;
	}

	public String getEquipePatrimoineRegion() {
		return equipePatrimoineRegion;
	}

	public void setEquipePatrimoineRegion(String equipePatrimoineRegion) {
		this.equipePatrimoineRegion = equipePatrimoineRegion;
	}

	public String getActeurPatrimoineRegion() {
		return acteurPatrimoineRegion;
	}

	public void setActeurPatrimoineRegion(String acteurPatrimoineRegion) {
		this.acteurPatrimoineRegion = acteurPatrimoineRegion;
	}

	public String getRdpRegion() {
		return rdpRegion;
	}

	public void setRdpRegion(String rdpRegion) {
		this.rdpRegion = rdpRegion;
	}

	public String getEquipeRespSite() {
		return equipeRespSite;
	}

	public void setEquipeRespSite(String equipeRespSite) {
		this.equipeRespSite = equipeRespSite;
	}

	public String getRespSite() {
		return respSite;
	}

	public void setRespSite(String respSite) {
		this.respSite = respSite;
	}

	public String getIngeRadio() {
		return ingeRadio;
	}

	public void setIngeRadio(String ingeRadio) {
		this.ingeRadio = ingeRadio;
	}

	public String getEb() {
		return eb;
	}

	public void setEb(String eb) {
		this.eb = eb;
	}

	public Collection<Workstream> getWorkstreams() {
		return workstreams;
	}

	public void setWorkstreams(Collection<Workstream> workstreams) {
		this.workstreams = workstreams;
	}

}
