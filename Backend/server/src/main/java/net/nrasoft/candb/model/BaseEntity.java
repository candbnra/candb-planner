package net.nrasoft.candb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@MappedSuperclass
public class BaseEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
