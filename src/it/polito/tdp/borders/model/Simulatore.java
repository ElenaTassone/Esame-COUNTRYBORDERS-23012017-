package it.polito.tdp.borders.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulatore {
	
	private Queue<Evento> queue ;
	private int passo ;
	
	
	
	public Simulatore() {
		super();
		this.queue = new PriorityQueue<Evento> ();
		this.passo = 1;
	}



	public int run (Country c){
		Evento primo = new Evento (1000, c, passo);
		queue.add(primo);
			
		while (!queue.isEmpty()) {
			Evento e = queue.poll();
		
			int migranti = e.getMigranti(); 
			List<Country> confinanti = e.getPaese().getConfinanti() ;
			if(confinanti.size()!=0){
			
			int stanziali = migranti/2 +(migranti%2);
		
			int nuoviMigranti = (migranti-stanziali)/confinanti.size(); 
			stanziali += (migranti-stanziali)-(nuoviMigranti*confinanti.size()) ;
			
			e.getPaese().addStanziali(stanziali);
			
			// se il numero di confinanti è mmaggiore del numero di migranti
			if(nuoviMigranti!=0){
			passo++;
			
			for(Country paese : confinanti){
				Evento nuovo = new Evento (nuoviMigranti, paese, passo);
				queue.add(nuovo) ;
				}
			}
		}
		}
		return passo ;
	}
}
