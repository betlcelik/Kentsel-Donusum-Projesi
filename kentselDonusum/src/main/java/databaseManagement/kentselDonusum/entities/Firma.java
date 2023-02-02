package databaseManagement.kentselDonusum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="firmalar")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "yikim"})
public class Firma {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="firma_adi")
	private String firmaAdi;
	
	@Column(name="tel_no")
	private String telNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adres")
	private String adres;
	
	
	@OneToOne(mappedBy = "firma")
	private Yikim yikim;

}
