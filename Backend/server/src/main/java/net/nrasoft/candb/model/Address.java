package net.nrasoft.candb.model;

import java.util.Collection;

//@Entity
//@Table(name = "t_addresses")
public class Address extends BaseEntity{

	protected String city;

	protected String street;

	protected String status;
	
	protected Collection<Intervention> interventions;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(Collection<Intervention> interventions) {
		this.interventions = interventions;
	}
	

}
