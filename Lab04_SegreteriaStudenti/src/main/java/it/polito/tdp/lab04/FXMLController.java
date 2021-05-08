package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;
	
	public void setModel(Model model) {
	    this.model = model;
	    this.txtResult.setStyle("-fx-font-family: monospace");
	    corsi = this.model.getTuttiICorsi();
	    corsi.add(new Corso(null,null, "", null));
	    Collections.sort(corsi);
	    this.comboCorso.getItems().addAll(corsi);
	    corsi.remove(0);
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	this.txtResult.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	
    	String m = this.txtMatricola.getText();
    	if(m.isEmpty()) {
    		this.txtResult.setText("Inserire una matricola");
    		return;
    	}
    	try {
    		int matricola = Integer.parseInt(m);
    		
    		Studente s = this.model.getStudente(matricola);
    	
    		if(s==null) {
    			this.txtResult.setText("Matricola inesistente");
    		}else {
    			
    			List<Corso> corsi = this.model.getCorsiStudente(s);
    			
    			if(corsi.size()==0) {
    	    		this.txtResult.setText("Lo studente non è iscritto a nessun corso");
    	    		return;
    	    	}
    	    	
    	    	StringBuilder sb = new StringBuilder();
    	    	
    	    	for (Corso c : corsi) {
    	    		
    	    		sb.append(String.format("%-10s ", c.getCodIns()));
    	    		sb.append(String.format("%-5d ", c.getCrediti()));
    	    		sb.append(String.format("%-50s ", c.getNome()));
    	    		sb.append(String.format("%-5d\n", c.getPd()));
    	    	}
    	    	
    	    	this.txtResult.setText(sb.toString());
    		}
    	
    	
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("La matricola deve essere numerica");
    		return;
    	}
    }

 

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	
    	Corso c = this.comboCorso.getValue();
    	
    	if( c== null || c.getNome()=="") {
    		this.txtResult.setText("Selezionare un corso");
    		return;
    	}
    	
    	List<Studente> studenti = this.model.getIscrittiCorso(c);
    	
    	if(studenti.size()==0) {
    		this.txtResult.setText("Il corso selezionato non ha ancora iscritti");
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (Studente s : studenti) {
    		
    		sb.append(String.format("%-8d ", s.getMatricola()));
    		sb.append(String.format("%-20s ", s.getNome()));
    		sb.append(String.format("%-30s ", s.getCognome()));
    		sb.append(String.format("%-8s\n", s.getCds()));
    	}
    	
    	this.txtResult.setText(sb.toString());
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	
    	String m = this.txtMatricola.getText();
    	if(m.isEmpty()) {
    		this.txtResult.setText("Inserire una matricola");
    		return;
    	}
    	try {
    	int matricola = Integer.parseInt(m);
    	Studente s = this.model.getStudente(matricola);
    	
    	if(s==null) {
    		this.txtResult.setText("Matricola inesistente");
    	}else {
    		this.txtCognome.setText(s.getCognome());
    		this.txtNome.setText(s.getNome());
    	}
    	
    	
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("La matricola deve essere numerica");
    		return;
    	}
    }

    @FXML
    void doCerca(ActionEvent event) {
    	
    	this.txtResult.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	
    	Corso c = this.comboCorso.getValue();
    	
    	if( c== null || c.getNome()=="") {
    		this.txtResult.setText("Selezionare un corso");
    		return;
    	}
    	
    	String m = this.txtMatricola.getText();
    	if(m.isEmpty()) {
    		this.txtResult.setText("Inserire una matricola");
    		return;
    	}
    	try {
    		int matricola = Integer.parseInt(m);
    		Studente s = this.model.getStudente(matricola);
    	
    		if(s==null) {
    			this.txtResult.setText("Matricola inesistente");
    			return;
    		}
    		this.txtCognome.setText(s.getCognome());
    		this.txtNome.setText(s.getNome());
    		
    		if(this.model.isStudenteIscrittoCorso(s,c)) {
    			this.txtResult.setText("Studente già iscritto al corso");
    		}
    		else
    			this.txtResult.setText("Studente non iscritto al corso");
    		
    		
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("La matricola deve essere numerica");
    		return;
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtResult.clear();
    	this.txtNome.clear();
    	this.comboCorso.getSelectionModel().clearSelection();

    }

    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
