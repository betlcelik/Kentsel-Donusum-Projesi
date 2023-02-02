package databaseManagement.kentselDonusum.entities;


import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="evler")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ev {
	 
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="apartman_ismi")
	private String apartmanIsmi;
	
	@Column(name="apartman_no")
	private int apartmanNo;
	
	@Column(name="adres")
	private String adres;
	
	@ManyToOne
	@JoinColumn(name="konut_sahibi_id")
	private KonutSahibi konutSahibi;
	
	//@OneToOne(mappedBy = "ev")
	//private Yikim yikim;
	
	
	

}
