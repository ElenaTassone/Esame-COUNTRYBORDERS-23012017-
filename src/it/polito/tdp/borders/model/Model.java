package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private List<Country> country;
	private BordersDAO dao;
	private UndirectedGraph<Country, Confine> grafo ;
	private Simulatore simulator ;
	private int passo ;
	
	public Model (){
		this.dao = new BordersDAO(); 
		passo = -1 ;
		
	}
	
	// TEST MODEL
	public static void main(String[] args) {
		Model m = new Model();
		m.getStati(2000);
		

	}

	public List<Country> getStati(int anno) {
		this.creaGrafo(anno) ;
//		System.out.println(grafo);
		country = new ArrayList<Country> () ; 
		for(Country c : this.grafo.vertexSet())
				country.add(c);
			
		Collections.sort(country);
		
		return country;
	}

	private void creaGrafo(int anno) {
		
		grafo = new SimpleGraph<Country, Confine>(Confine.class);
		country = new ArrayList<Country> () ;
		List<Confine> confini = dao.getConfini(anno);
		this.azzeraConfini(confini);
		for(Confine c : confini){
			if(!country.contains(c.getC1())){
//				country.add(c.getC1());
				grafo.addVertex(c.getC1());
				}
			if(!country.contains(c.getC2())){
//				country.add(c.getC2());
				grafo.addVertex(c.getC2());
				}
		grafo.addEdge(c.getC1(), c.getC2(), c) ;
		this.addConfinanti();
		
		}

	}

	private void addConfinanti() {
		for (Country c : grafo.vertexSet()){
			List<Country> confinanti = Graphs.neighborListOf(grafo, c);
			c.addConfinanti(confinanti);
		}
		
	}

	private void azzeraConfini(List<Confine> confini) {
		for(Confine c : confini){
			c.getC1().setConfinanti();
			c.getC2().setConfinanti();
			
		}
		
	}

	public List<Country> getSimulazione(Country c) {
		if(this.grafo==null)
			return null;
		this.simulator= new Simulatore() ;
		this.passo = simulator.run(c);
		List<Country> stati = new ArrayList<Country> () ;
		
		for(Country paese : grafo.vertexSet()){
			if(paese.getStanziali()!=0)
				stati.add(paese);
		}
		
		Collections.sort(stati, new Comparator<Country> (){
			public int compare(Country primo, Country secondo) {
				return -primo.getStanziali()+secondo.getStanziali();
				
			}
		});
		return stati ;
	}

}
