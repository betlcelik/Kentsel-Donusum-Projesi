package databaseManagement.kentselDonusum.dataAccess;



import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import databaseManagement.kentselDonusum.entities.KonutSahibi;
import databaseManagement.kentselDonusum.entities.Yikim;

public interface YikimDao extends JpaRepository<Yikim, Integer>{
	
	
	@Modifying
	@Transactional
	@Query(
	  value = 
	    "insert into yikim (ev_id,firma_id,yikim_zamani,maliyet,odeme_bilgisi ) values (:evId, :firmaId, :yikimZamani , :maliyet , :odemeBilgisi)",
	  nativeQuery = true)
	void yikimEkle(@Param("evId") Integer evId,@Param("firmaId") Integer firmaId,@Param("yikimZamani") LocalDate yikimZamani,@Param("maliyet") Integer maliyet,@Param("odemeBilgisi") Boolean odemeBilgisi);
	
	
	@Modifying
	@Procedure(value = "deneme")
	int bul(@Param("firma") String firma);
	
	@Modifying
	@Transactional
	@Procedure(value="")
	int yikimOdemesi(@Param("evId") Integer evId,@Param("tutar") Integer tutar);
	
	
	@Query(value = "select * from yikim where ev_id< 40 union select * from yikim where maliyet < 100000 ", 
			  nativeQuery = true)
	List<Yikim> maliyetiveIdsiDusukYikimlar();

	
	

}
