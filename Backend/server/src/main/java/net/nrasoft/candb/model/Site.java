package net.nrasoft.candb.model;


//@Entity
//@Table(name = "t_workstreams")
public class Site extends NamedEntity {

	protected String code;
	protected String status;
	protected Project project;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
