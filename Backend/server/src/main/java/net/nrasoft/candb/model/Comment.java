package net.nrasoft.candb.model;

import java.time.LocalDate;


//@Entity
//@Table(name = "t_comment")
public class Comment extends BaseEntity {

//	@ManyToOne
//	@JoinColumn(name = "intervention_id")
    private Intervention intervention;

//	@Column(name = "comment")
//	@NotEmpty
	private String comment;

//	@Column(name = "author")
//	@NotEmpty
	private String author;

//	@Column(name = "entry_date")
//	@NotEmpty
	private LocalDate entryDate;
	

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	
	
	

}
