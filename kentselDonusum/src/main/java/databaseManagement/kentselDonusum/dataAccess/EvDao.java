package databaseManagement.kentselDonusum.dataAccess;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import databaseManagement.kentselDonusum.entities.Ev;


public interface EvDao extends JpaRepository<Ev, Integer>{
  
	
	
	
	@Query(value = "SELECT * FROM evler ", 
			  nativeQuery = true)
	List<Ev> evleriListele();
	
	
	@Query(value = "SELECT * FROM evler e WHERE e.apartman_ismi = :apartmanIsmi", 
			  nativeQuery = true)
	Ev evAra(@Param("apartmanIsmi") String apartmanIsmi);
	
	
	
	
	@Modifying
	@Transactional
	@Query(
	  value = 
	    "insert into evler (apartman_ismi, apartman_no, adres,konut_sahibi_id) values (:apartmanIsmi, :apartmanNo, :adres , :konutSahibiId)",
	  nativeQuery = true)
	void evEkle(@Param("apartmanIsmi") String apartmanIsmi,@Param("apartmanNo") Integer apartmanNo,@Param("adres") String adres,@Param("konutSahibiId") Integer konutSahibiId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE  FROM evler e WHERE e.apartman_ismi = :apartmanIsmi", 
	  nativeQuery = true)
	void evSil(@Param("apartmanIsmi") String apartmanIsmi);
	
	
	
	
}
