package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;


public class Model {

	SimpleGraph<Author, DefaultEdge> grafo;

	private PortoDAO dao = null;

	private List<Author> authors;
	private List<Paper> papers;
	private List<Creator> creators;
	private GraphPath<Author, DefaultEdge> path;
	
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

	public List<Author> getShortestPath(Author authorSorgrnte, Author authorDestinazione) {
		
		Author aS = this.aIdMap.getAuthor(authorSorgrnte);
		Author aD = this.aIdMap.getAuthor(authorDestinazione);
		
		if(aS == null || aD == null)
			throw new RuntimeException("gli autori non sono presenti in memoria");
		
		//calcolare lo shortestPath
		ShortestPathAlgorithm<Author, DefaultEdge> spa = new DijkstraShortestPath<>(this.grafo);
		
		path = spa.getPath(aS, aD);
//		path.getEdgeList();
		List<Author> autoriConnessi = this.path.getVertexList();
		
		System.out.println("elenco di co-autori collegati: " + path.getVertexList() + "\n");
		
		return autoriConnessi;
		
		
	
	}
	
	
	//PER TEST MODEL - metodo bovino con un for con indice i
	public List<Author> getShortestPath(int aUno, int aDue) {
			
			Author a1 = this.aIdMap.getAuthorByID(aUno);
			Author a2 = this.aIdMap.getAuthorByID(aDue);
			
			if(a1 == null || a2 == null)
				throw new RuntimeException("gli autori non sono presenti in memoria");
			
			//calcolare lo shortestPath
			ShortestPathAlgorithm<Author, DefaultEdge> spa = new DijkstraShortestPath<>(this.grafo);
			
			path = spa.getPath(a1, a2);
			
			List<Author> autoriConnessi = this.path.getVertexList();

			
			System.out.println("");
			System.out.println("elenco di co-autori collegati: " + path.getLength());//numero di archi per arrivare alla destinzazione 
			System.out.println("");
			System.out.println("elenco di co-autori collegati: " + path.getVertexList());
			System.out.println("");

			return autoriConnessi;
		}
	
	
	
	public List<Paper> getArticoliInComune(Author authorSorgrnte, Author authorDestinazione) {
		
		List<Paper> articoliToStamp = new ArrayList<>();
		
		List<Author> aConnessi = this.getShortestPath(authorSorgrnte, authorDestinazione);
		
		
		for(int i=0; i<aConnessi.size()-1; i++) {
					
					Author a1 = this.aIdMap.getAuthor(aConnessi.get(i));
					Author a2 = this.aIdMap.getAuthor(aConnessi.get(i+1));
					
					Paper p = dao.getArticoloInComune(a1, a2);
					
					articoliToStamp.add(p);
					
				}
		
				return articoliToStamp;
			}
	
	//PER TEST MODEL - metodo bovino con un for con indice i
	public List<Paper> getArticoliInComune(int iDauthorSorgrnte, int iDauthorDestinazione) {
		
		List<Paper> articoliToStamp = new ArrayList<>();
		
		List<Author> aConnessi = this.getShortestPath(iDauthorSorgrnte, iDauthorDestinazione);
		
		
		for(int i=0; i<aConnessi.size()-1; i++) {
					
//			if(i==)
					Author a1 = this.aIdMap.getAuthor(aConnessi.get(i));
					Author a2 = this.aIdMap.getAuthor(aConnessi.get(i+1));
					
					Paper p = dao.getArticoloInComune(a1, a2);
					
					articoliToStamp.add(p);
					
				}
		
		System.out.println(articoliToStamp.toString());
		return articoliToStamp;
			}
}
