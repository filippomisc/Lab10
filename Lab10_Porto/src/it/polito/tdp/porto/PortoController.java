package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private Model m;
	private List<Author> authors;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<?> boxSecondo;
    
    @FXML
    private Button btnCoAutori;

    @FXML
    private Button btnSequenza;


    @FXML
    private TextArea txtResult;


    @FXML
    void handleCoautori(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Author a = this.boxPrimo.getValue();
    	
    	
    	List<Author> colls = m.getCollaboratoriDiAutore(a);
    	
    	String result = "";
    	
    	for (Author coll : colls) {
//    		result += coll.getId() + " " + coll.getLastname() + " " + coll.getFirstname() + "\n";
    		result += coll.getFirstname() + " " + coll.getLastname() + "\n";

    	}
    	
    	this.txtResult.setText("I collaboratore di " + a.getFirstname() + " " + a.getLastname() + " è: \n" + result );
    	
    	
    	
    	
//    	this.txtResult.setText(result.toString());
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//serve a resettare la comboBox
//        this.boxPrimo.getSelectionModel().clearSelection();    


    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }
    
    //questa volta all'avvio dell'interfaccia grafica bisognerà creare il grafo****
    void SetModel(Model model) {
    	m=model;
    	
    	m.createGraph();//_____________________________________________________****
    	
    	//bisogna settare la comboBox all'avvio dell'interfaccia grafica
    	authors = m.getAuthors();
		this.boxPrimo.getItems().addAll(authors);
    }
}
