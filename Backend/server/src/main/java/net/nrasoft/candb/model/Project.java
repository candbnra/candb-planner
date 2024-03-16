package net.nrasoft.candb.model;

public class Project extends NamedEntity {

	protected String code;
	protected Site site;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
 

	
}

