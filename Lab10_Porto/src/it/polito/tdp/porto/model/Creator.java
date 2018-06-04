package it.polito.tdp.porto.model;

public class Creator {

	private int eprintid;
	private int authorid;
	private int creatorid;
	

	public Creator(int creatorid, int eprintid, int authorid) {
		super();
		this.eprintid = eprintid;
		this.authorid = authorid;
		this.creatorid = creatorid;
	}
	
	public int getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(int creatorid) {
		this.creatorid = creatorid;
	}

	public int getEprintid() {
		return eprintid;
	}

	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	@Override
	public String toString() {
		return "Creator [eprintid=" + eprintid + ", authorid=" + authorid + ", creatorid=" + creatorid + "]";
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
