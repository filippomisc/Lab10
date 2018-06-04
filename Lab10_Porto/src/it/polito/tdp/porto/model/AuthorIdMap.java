package it.polito.tdp.porto.model;

import java.util.*;

public class AuthorIdMap {
	
	private Map<Integer, Author> authors;
	
	public AuthorIdMap() {
		this.authors = new HashMap<>();
	}	

	
	public Author getAuthorByID(int id) {
		return this.authors.get(id);
	}
	
	public Author getAuthor(Author a) {
		Author old = authors.get(a.getId());
		if(old==null) {
			this.authors.put(a.getId(), a);
			return a;
		}
		return old;
	}
	
	public void put(Author a, int authorId) {
		this.authors.put(authorId, a);
	}
}
