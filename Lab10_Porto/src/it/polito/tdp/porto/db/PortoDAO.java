package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore. inutile
	 */
//	public Author getAutore(AuthorIdMap aIdMap, int id) {
//
//		final String sql = "SELECT * FROM author where id=?";
//
//		try {
//			Connection conn = DBConnect.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, id);
//
//			ResultSet rs = st.executeQuery();
//
//			if (rs.next()) {
//
//				
//				
//				
//				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
//				return autore;
//			}
//
//			return null;
//
//		} catch (SQLException e) {
//			// e.printStackTrace();
//			throw new RuntimeException("Errore Db");
//		}
//	}
	
	
	
	/**
	 * metodo che aggiunge ad una lista tutti gli articoli del DB
	 * @param aIdMap
	 * @return
	 */
	public List<Author> getAllAuthors(AuthorIdMap aIdMap){
		
		final String sql = "SELECT * FROM author";
		
		List<Author> autori = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				
				autori.add(aIdMap.getAuthor(autore));
			
			}

			conn.close();
			
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo. inutile
	 */
//	public Paper getArticolo(int eprintid) {
//
//		final String sql = "SELECT * FROM paper where eprintid=?";
//
//		try {
//			Connection conn = DBConnect.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, eprintid);
//
//			ResultSet rs = st.executeQuery();
//
//			if (rs.next()) {
//				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
//						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
//				return paper;
//			}
//
//			return null;
//
//		} catch (SQLException e) {
//			 e.printStackTrace();
//			throw new RuntimeException("Errore Db");
//		}
//	}
	
	
	/**
	 * metodo che aggiunge ad una lista tutti gli articoli del DB
	 * @param pIdMap
	 * @return lista di articoli del DB
	 */
	public List<Paper> getAllPapers(PaperIdMap pIdMap){
		
		final String sql = "SELECT * FROM paper";
		
		List<Paper> articoli = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Paper articolo = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				
				articoli.add(pIdMap.getPaper(articolo));
			
			}

			conn.close();
			
			return articoli;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}


}