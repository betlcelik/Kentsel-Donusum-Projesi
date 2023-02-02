package databaseManagement.kentselDonusum.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="yikim")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"id"})
public class Yikim {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ApiModelProperty(example = ("yyyy-MM-dd"))
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="yikim_zamani")
	private LocalDate yikimZamani;
	
	@Column(name="maliyet")
	private int maliyet;
	
	@Column(name="odeme_bilgisi")
	private boolean odemeBilgisi;
	
	@OneToOne
	@JoinColumn(name="ev_id")
	private Ev ev;
	
	@ManyToOne
	@JoinColumn(name="firma_id")
	private Firma firma;

	

}
