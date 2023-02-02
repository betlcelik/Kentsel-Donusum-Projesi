package databaseManagement.kentselDonusum.api;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import databaseManagement.kentselDonusum.business.abtracts.KonutSahibiService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.KonutSahibi;


@RestController
@RequestMapping("konutSahipleri")
public class KonutSahibiController {
	
	private KonutSahibiService konutSahibiService;

	public KonutSahibiController(KonutSahibiService konutSahibiService) {
		super();
		this.konutSahibiService = konutSahibiService;
	}
	
	@PostMapping("/konutSahibiEkle")
	public Result konutSahibiEkle(KonutSahibi konutSahibi) {
		return this.konutSahibiService.konutSahibiEkle(konutSahibi);
	}

	@GetMapping("/evleriniGetir")
	public DataResult<List<String>> IsmeGoreEvleriniGetir(String isim){
		return this.konutSahibiService.IsmeGoreEvleriniGetir(isim);
	}
	
	@GetMapping("/konutSahipleriniListele")
	public DataResult<List<KonutSahibi>> konutSahipleriniListele(){
		return this.konutSahibiService.konutSahipleriniListele();
	}
	
	@GetMapping("/birdenFazlaEviOlanKonutSahipleriniListele")
	public DataResult<List<Integer>> birdenFazlaEviOlankonutSahipleriniListele(){
		return this.konutSahibiService.birdenFazlaEviOlankonutSahipleriniListele();
	}
}
