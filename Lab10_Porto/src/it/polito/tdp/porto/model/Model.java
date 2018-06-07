package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;


public class Model {

	SimpleGraph<Author, DefaultEdge> grafo;

	private PortoDAO dao = null;

	private List<Author> authors;
	private List<Paper> papers;
	private List<Creator> creators;
	
	private AuthorIdMap aIdMap;
	private PaperIdMap pIdMap;
	private CreatorIdMap cIdMap;

	public Model() {		
		
//		this.grafo = new SimpleGraph<>(DefaultEdge.class);

		this.dao = new PortoDAO();
		
//		this.authors = dao.getAllAuthors(aIdMap);
//		this.papers = dao.getAllPapers(pIdMap);
//		this.creators = dao.getAllCreator(cIdMap, aIdMap, pIdMap);
		
		this.aIdMap = new AuthorIdMap();
		this.pIdMap = new PaperIdMap();
		this.cIdMap = new CreatorIdMap();
		
	}

	public void createGraph() {
		
		//leggere la lista di tutti gli oggetti (fatto anche nel costruttore)
		this.authors = dao.getAllAuthors(aIdMap);
		
		//creare il grafo SimpleGraph() (fatto anche nel costruttore) 
		this.grafo = new SimpleGraph<>(DefaultEdge.class);

		//aggiungere i vertici 
		Graphs.addAllVertices(grafo, authors);

		//aggiungere gli archi
		
		for(Author a : this.getAuthors()) {
			
		List<Author> collaboratori = dao.getCollaborator(aIdMap, aIdMap.getAuthor(a));
			
			for(Author c : collaboratori) 
				
				this.grafo.addEdge(a, aIdMap.getAuthor(c));
		}
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}

	//importante per popolare la comboBox 
	public List<Author> getAuthors() {
		if (this.authors == null) {
			return new ArrayList<Author>();
		}
		return this.authors;
	}

	public List<Author> getCollaboratoriDiAutore(Author a) {

		return Graphs.neighborListOf(this.grafo, a);
	}

	public List<Author> getCollaboratoriDiAutore(int authorId) {
		return Graphs.neighborListOf(this.grafo, aIdMap.getAuthorByID(authorId));
	}

	public List<Author> getNoCoAuthorsOf(Author a) {

		List<Author> noCoAuthors = this.getAuthors();
		
		List<Author> autoriDaEscludere = this.getCollaboratoriDiAutore(a);
		
		noCoAuthors.removeAll(autoriDaEscludere);//togliamo dalla lista i co-autori
		noCoAuthors.remove(a);//togliamo dalla lista l'autore sorgente
		
		return noCoAuthors;
	}
}
