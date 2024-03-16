package net.nrasoft.candb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

public class SiteDp extends NamedEntity {

	private String code;

	private String status;

	private Set<Address> addresses;

	private Set<Intervention> interventions;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	protected Set<Address> getAddressesInternal() {
		if (this.addresses == null) {
			this.addresses = new HashSet<>();
		}
		return this.addresses;
	}

	protected void setAddressesInternal(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		List<Address> sortedAddresses = new ArrayList<>(getAddressesInternal());
		PropertyComparator.sort(sortedAddresses, new MutableSortDefinition("street", true, true));
		return Collections.unmodifiableList(sortedAddresses);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = new HashSet<>(addresses);
	}

	public void addAddress(Address address) {
		getAddressesInternal().add(address);
	}

	protected Set<Intervention> getInterventionsInternal() {
		if (this.interventions == null) {
			this.interventions = new HashSet<>();
		}
		return this.interventions;
	}

	protected void setInterventionsInternal(Set<Intervention> interventions) {
		this.interventions = interventions;
	}

	public List<Intervention> getInterventions() {
		List<Intervention> sortedInterventions = new ArrayList<>(getInterventionsInternal());
		PropertyComparator.sort(sortedInterventions, new MutableSortDefinition("street", true, true));
		return Collections.unmodifiableList(sortedInterventions);
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = new HashSet<>(interventions);
	}

	public void addIntervention(Intervention intervention) {
		getInterventionsInternal().add(intervention);
		intervention.setDp(this);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}