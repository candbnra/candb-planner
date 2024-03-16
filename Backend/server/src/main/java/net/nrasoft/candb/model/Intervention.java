package net.nrasoft.candb.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

public class Intervention extends BaseEntity {

	protected String type;
	protected Date startDate;
	protected Date endDate;
	private Person person;
	private SiteDp dp;
    private Set<Comment> comments;
    
	protected Set<Comment> getCommentsInternal() {
		if (this.comments == null) {
			this.comments = new HashSet<>();
		}
		return this.comments;
	}

	protected void setCommentsInternal(Set<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		List<Comment> sortedComments = new ArrayList<>(getCommentsInternal());
		PropertyComparator.sort(sortedComments, new MutableSortDefinition("startDate", false, false));
		return Collections.unmodifiableList(sortedComments);
	}

	public void setComments(List<Comment> comments) {
		this.comments = new HashSet<>(comments);
	}

	public void addComment(Comment comment) {
		getCommentsInternal().add(comment);
		comment.setIntervention(this);
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public SiteDp getDp() {
		return dp;
	}

	public void setDp(SiteDp dp) {
		this.dp = dp;
	}

}
