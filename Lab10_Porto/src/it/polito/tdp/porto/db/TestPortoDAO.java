package it.polito.tdp.porto.db;

import java.util.*;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.CreatorIdMap;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO dao = new PortoDAO();
//		System.out.println(pd.getAutore(85));
//		System.out.println(pd.getArticolo(2293546));
//		System.out.println(pd.getArticolo(1941144));

		CreatorIdMap cIdMap = new CreatorIdMap();
		AuthorIdMap aIdMap = new AuthorIdMap();
		PaperIdMap pIdMap = new PaperIdMap();
		
		System.out.println("numero autori: " + dao.getAllAuthors(aIdMap).size());
		System.out.println("numero articoli: " + dao.getAllPapers(pIdMap).size());
		System.out.println("numero creazioni: " + dao.getAllCreator(cIdMap, aIdMap, pIdMap).size());
		
		
		//troviamo i collaboratori di norese Maria Franca (id: 780)
		Author a = aIdMap.getAuthorByID(780);
		List<Author> coll = dao.getCollaborator(aIdMap, a);
		
		System.out.println(String.format("collaboratori di %s %s: \n", a.getFirstname(), a.getLastname()));
		System.out.println(coll.toString());
		
	}

}
