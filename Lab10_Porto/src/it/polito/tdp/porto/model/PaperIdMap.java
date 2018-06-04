package it.polito.tdp.porto.model;

import java.util.*;

public class PaperIdMap {

private Map<Integer, Paper> papers;
	
	public PaperIdMap() {
		this.papers = new HashMap<>();
	}	

	
	public Paper getPaperByID(int id) {
		return this.papers.get(id);
	}
	
	public Paper getAuthor(Paper p) {
		Paper old = papers.get(p.getEprintid());
		if(old==null) {
			this.papers.put(p.getEprintid(), p);
			return p;
		}
		return old;
	}
	
	public void put(Paper a, int paperId) {
		this.papers.put(paperId, a);
	}
}
