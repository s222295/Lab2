package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {
	
	private Dictionary d;
	private ItalianDictionary italiano;
	private EnglishDictionary inglese;
	//private TextFlow txt;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblStato;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtDaCorreggere;


    @FXML
    private TextFlow txtCorretto;

    @FXML
    private Label lblErrori;

    @FXML
    void doClearText(ActionEvent event) {

    	txtDaCorreggere.clear();
    	txtCorretto.getChildren().removeAll();
    	lblErrori.setText("");
    	
    }
    
    void setModel(ItalianDictionary d1, EnglishDictionary d2){
    	italiano=d1;
    	d1.loadDictionary();
    	inglese=d2;
    	d2.loadDictionary();
    	
    }
    
    @FXML
    void doActivation(ActionEvent event) {

    	if(boxLingua.getValue()!=null){
    		txtDaCorreggere.setDisable(false);
    	}
    }
    
    
    
    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	if(boxLingua.getValue().compareTo("English")==0){
    		d=inglese; 
    	}else{
    		d=italiano;
    	}
    	
    	
    	List<String> listaDaCorreggere= new LinkedList<String>();
    	String s=txtDaCorreggere.getText();
    	StringTokenizer st = new StringTokenizer(s, " ");
	    
    	while(st.hasMoreTokens()){
	    	listaDaCorreggere.add(st.nextToken().trim());
	    }
    	long l1=System.nanoTime();
    	List<RichWord> lista = d.spellCheckText(listaDaCorreggere);
    	long l2=System.nanoTime();
		
    	Text text1=new Text("");
    	
    	int errori=0;
 	
    	for(RichWord r:lista){
    		if(r.isCorretta()==true){
    			text1=new Text(r.getParola()+" ");
    		}else{
    			text1 = new Text(r.getParola()+" ");
    			text1.setFill(Color.RED);
    			errori++;
    		}
    	
    		txtCorretto.getChildren().add(text1);
    	}
    	//txtCorretto.getChildren().add(txt);
    	
    	if(errori==1){
    		lblErrori.setText("C'è 1 errore");
    	}else if(errori>1){
    		lblErrori.setText("Ci sono " +errori+" errori");	
    	}else{
    		lblErrori.setText("Non ci sono errori");
    	}
    	
    	lblStato.setText("Spell check completato in "+(l2-l1)/1E9 +" secondi");
    	

    }

    @FXML
    void initialize() {
        assert lblStato != null : "fx:id=\"lblStato\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtDaCorreggere\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCorretto != null : "fx:id=\"txtCorretto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        //assert txt != null : "fx:id=\"txt\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        txtDaCorreggere.setDisable(true);
        boxLingua.getItems().addAll("English","Italian");
        
    }
}
