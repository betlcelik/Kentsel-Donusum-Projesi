package databaseManagement.kentselDonusum.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="konut_sahibi")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "evler"})
public class KonutSahibi {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="isim")
	private String isim;
	
	@Column(name="soyisim")
	private String soyisim;
	
	@Column(name="tel_no")
	private String telNo;
	
	@OneToMany(mappedBy = "konutSahibi")
    private List<Ev> evler;
    

}
