package it.polito.tdp.porto.model;

import java.util.*;

public class CreatorIdMap {

private Map<Integer, Creator> creators;
	
	public CreatorIdMap() {
		this.creators = new HashMap<>();
	}	

	
	public Creator getCreatorByID(int id) {
		return this.creators.get(id);
	}
	
	public Creator getCreator(Creator c) {
		Creator old = creators.get(c.getCreatorid());
		if(old==null) {
			this.creators.put(c.getCreatorid(), c);
			return c;
		}
		return old;
	}
	
	public void put(Creator a, int creatorId) {
		this.creators.put(creatorId, a);
	}
}
