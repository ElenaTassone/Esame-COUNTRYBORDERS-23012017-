/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model m ;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	String num = txtAnno.getText() ;
    	if(num.length()==0){
    		txtResult.setText("Errore: INSERIRE UN ANNO COMPRESO TRA 1816 E 2006");
    		}
    	try{
    		int n = Integer.parseInt(num);
    		if(n<1816 || n>2006)
    			txtResult.setText("Errore: INSERIRE UN ANNO COMPRESO TRA 1816 E 2006");
    		else{
    		List<Country> lista = m.getStati(n) ;
    		boxNazione.getItems().addAll(lista) ;
    		txtResult.clear();
        	for(Country c : lista){
        		txtResult.appendText(c.getStateAbb()+" "+c.getStateName()+": "+c.getConfinanti().size()+"\n");
        		}
    		}
    		}
    	catch(NumberFormatException e){
    		txtResult.setText("Errore: INSERIRE UN ANNO COMPRESO TRA 1816 E 2006");
    		}
    	
    	}
    
    public void setModel(Model model){
    	this.m = model ;
    }

    @FXML
    void doSimula(ActionEvent event) {
    	Country c = boxNazione.getValue() ;
    	if(c== null)
    		txtResult.setText("Errore: SELEZIONARE UNA NAZIONE");
    	else{
    		List<Country> paesi = m.getSimulazione(c);
    		if(paesi == null)
    			txtResult.setText("Errore: SELEZIONARE PRIMA UN ANNO");
    		else{
    			txtResult.clear();
    			for(Country p : paesi){
    				txtResult.appendText(p.getStateAbb()+" "+p.getStateName()+"con "+p.getStanziali()+" persone stanziali \n");
    			}
    		}
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }
}
