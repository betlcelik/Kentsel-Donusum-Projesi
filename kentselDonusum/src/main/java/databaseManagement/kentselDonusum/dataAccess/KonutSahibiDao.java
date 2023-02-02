package databaseManagement.kentselDonusum.dataAccess;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import databaseManagement.kentselDonusum.entities.Ev;
import databaseManagement.kentselDonusum.entities.KonutSahibi;


public interface KonutSahibiDao extends JpaRepository<KonutSahibi, Integer> {
	
	
	
	
	
	@Query(value = "select e.apartman_ismi from evler e join konut_sahibi ko on e.konut_sahibi_id = ko.id and ko.isim= :isim ", 
			  nativeQuery = true)
	List<String> IsmeGoreEvleriniGetir(@Param("isim") String isim);
	

	
	@Query(value = "SELECT * FROM konut_sahibi ", 
			  nativeQuery = true)
	List<KonutSahibi> konutSahipleriniListele();
	
	@Modifying
	@Transactional
	@Query(
	  value = 
	    "insert into konut_sahibi (isim,soyisim,tel_no) values (:isim, :soyisim, :telNo )",
	  nativeQuery = true)
	void konutSahibiEkle(@Param("isim") String isim,@Param("soyisim") String soyisim,@Param("telNo") String telNo);

	@Query(
			  value = 
			    "select e.konut_sahibi_id from konut_sahibi k,evler e where e.konut_sahibi_id=k.id group by konut_sahibi_id having count(*)>1 ",
			  nativeQuery = true)
	List<Integer> birdenFazlaEviOlankonutSahipleriniListele();
	
}
