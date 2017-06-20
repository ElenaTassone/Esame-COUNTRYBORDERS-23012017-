package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Bean class for representing instances of the COUNTRY table in the COUNTRIES data-set.
 * 
 * <p>Includes 3 properties: 
 * <ul>
 * <li>{@code cCode} (the country code)
 * <li>{@code stateAbb} (a 3 letter abbreviation for the state),
 * <li>{@code stateName} (the full name of the state).
 * </ul>
 * 
 * @author Fulvio
 *
 */
public class Country implements Comparable<Country>{

	private int cCode ; // Country Code for the state
	private String stateAbb ; // State Abbreviation (3 capital letters)
	private String stateName ; // Full State name
	private List<Country> confinanti ; //paesi confinanti
	private int stanziali ; //numero di migranti che si sono stanziati
	
	/**
	 * Initialize a new {@link Country} object, with full parameters.
	 * 
	 * @param cCode
	 * @param stateAbb
	 * @param stateName
	 */
	public Country(int cCode, String stateAbb, String stateName) {
		super();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
		this.addStanziali(0) ;
		
	}

	/**
	 * @return the cCode
	 */
	public int getcCode() {
		return cCode;
	}

	/**
	 * @param cCode the cCode to set
	 */
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	/**
	 * @return the stateAbb
	 */
	public String getStateAbb() {
		return stateAbb;
	}

	/**
	 * @param stateAbb the stateAbb to set
	 */
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("[%s=%s]", stateAbb, stateName);
	}
	
	public void addConfinante(Country c){
		if(!this.confinanti.contains(c))
			this.confinanti.add(c);
		
	}
	public List<Country> getConfinanti(){
		return this.confinanti ;
	}
	
	public void setConfinanti(){
		this.confinanti= new ArrayList <Country> (); 
	}

	@Override
	public int compareTo(Country altro) {
		return -this.getConfinanti().size()+altro.getConfinanti().size();
	}

	public int getStanziali() {
		return stanziali;
	}

	public void addStanziali(int stanziali) {
		this.stanziali += stanziali;
	}

	public void addConfinanti(List<Country> c) {
		this.confinanti= c ;
		
	}
	
	
}
