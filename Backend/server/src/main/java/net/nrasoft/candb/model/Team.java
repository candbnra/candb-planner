package net.nrasoft.candb.model;



public class Team extends NamedEntity {

	private Company company;

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
