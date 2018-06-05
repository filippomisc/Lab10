package it.polito.tdp.porto.model;

public class Creator {

	//private int eprintid;
	private Paper paper;
	private Author author;
	//private int authorid;
	private int creatorid;
	

	public Creator(int creatorid, Author author, Paper paper) {
		super();
		this.paper = paper;
		this.author = author;
		this.creatorid = creatorid;
	}


	public int getEprintid() {
		return this.paper.getEprintid();
	}


	public void setEprintid(int eprintid) {
		this.paper.setEprintid(eprintid);
	}


	public Paper getPaper() {
		return paper;
	}


	public void setPaper(Paper paper) {
		this.paper = paper;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public int getAuthorid() {
		return this.author.getId();
	}


	public void setAuthorid(int authorid) {
		this.author.setId(authorid);
	}


	public int getCreatorid() {
		return creatorid;
	}


	public void setCreatorid(int creatorid) {
		this.creatorid = creatorid;
	}


	@Override
	public String toString() {
		return "Creator [eprintid=" + this.getEprintid() + ", paper=" + paper + ", author=" + author + ", authorid=" + this.getAuthorid()
				+ ", creatorid=" + creatorid + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creatorid;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		if (creatorid != other.creatorid)
			return false;
		return true;
	}
	
	
	
}
