package databaseManagement.kentselDonusum.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import databaseManagement.kentselDonusum.business.abtracts.YikimService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.Yikim;

@RestController
@RequestMapping("yikim")
public class YikimController {
	
	private YikimService yikimService;

	public YikimController(YikimService yikimService) {
		super();
		this.yikimService = yikimService;
	}
	
	@PostMapping("/yikimEkle")
	public Result yikimEkle(Yikim yikim) {
		return this.yikimService.yikimEkle(yikim);
		
	}
	
	
	@PutMapping("/odemeYap")
	public Result odemeYap(int evId,int tutar) {
		return this.yikimService.odemeYap(evId, tutar);
	}
	
	

}
