package net.nrasoft.candb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

//@Entity
//@Table(name = "t_companies")
public class Company extends NamedEntity {

//	@Column(name = "type")
//	@NotEmpty
	protected String type;
	protected String code;

   
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Team> teams;
	
    protected Set<Team> getTeamsInternal() {
        if (this.teams == null) {
            this.teams = new HashSet<>();
        }
        return this.teams;
    }

    protected void setTeamsInternal(Set<Team> teams) {
        this.teams = teams;
    }


    public List<Team> getTeams() {
        List<Team> sortedTeams = new ArrayList<>(getTeamsInternal());
        PropertyComparator.sort(sortedTeams, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedTeams);
    }

    public void setTeams(List<Team> teams) {
        this.teams = new HashSet<>(teams);
    }

    public void addTeam(Team team) {
        getTeamsInternal().add(team);
        team.setCompany(this);
    }

    
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
    
    
}
