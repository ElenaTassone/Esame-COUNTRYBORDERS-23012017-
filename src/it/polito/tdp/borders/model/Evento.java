package it.polito.tdp.borders.model;

import org.w3c.dom.events.Event;

public class Evento implements Comparable <Evento>{
	
	private int migranti;
	private Country paese ;
	private int passo ;
	
	public Evento(int migranti, Country paese, int passo) {
		super();
		this.setMigranti(migranti);
		this.setPaese(paese);
		this.setPasso(passo);
	}

	public Country getPaese() {
		return paese;
	}

	public void setPaese(Country paese) {
		this.paese = paese;
	}

	public int getMigranti() {
		return migranti;
	}

	public void setMigranti(int migranti) {
		this.migranti = migranti;
	}

	public int getPasso() {
		return passo;
	}

	public void setPasso(int passo) {
		this.passo = passo;
	}

	@Override
	public int compareTo(Evento altro) {
		return this.passo-altro.getPasso();
	}
	
	

}
