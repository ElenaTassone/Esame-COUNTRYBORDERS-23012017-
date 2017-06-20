package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Confine;
import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BordersDAO {
	
	private Map<Integer, Country> countryMap ;

	public List<Country> loadAllCountries() {
	
		countryMap = new TreeMap<Integer, Country>  () ;	
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
				countryMap.put(c.getcCode(), c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public static void main(String[] args) {
		List<Confine> list ;
		BordersDAO dao = new BordersDAO() ;
		list = dao.getConfini(2000) ;
		for(Confine c: list) {
			System.out.println(c);
		}
	}

	public List<Confine> getConfini(int anno) {
		if(countryMap==null)
			this.loadAllCountries();
		String sql = 
				"SELECT state1no, state2no " +
				"FROM contiguity " +
				"WHERE year<=? AND conttype=1 ";

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery() ;
			
			List<Confine> list = new LinkedList<Confine>() ;
			
			while( rs.next() ) {
				Confine c = new Confine (countryMap.get(rs.getInt("state1no")), countryMap.get(rs.getInt("state2no")));
				if(!list.contains(c))
					list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
		
	}
	
	
}
