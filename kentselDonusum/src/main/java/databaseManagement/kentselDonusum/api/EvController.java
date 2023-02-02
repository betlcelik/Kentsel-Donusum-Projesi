package databaseManagement.kentselDonusum.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import databaseManagement.kentselDonusum.business.abtracts.EvService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.Ev;


@RestController
@RequestMapping("evler")
public class EvController {
	
	private EvService evService;
	
	@Autowired
	public EvController(EvService evService) {
		super();
		this.evService = evService;
	}
	
	@GetMapping("/evleriListele")
	DataResult<List<Ev>> evleriListele(){
		return this.evService.evleriListele();
	}
	
	
	@GetMapping("/evAra")
	DataResult<Ev> evAra(String apartmanIsmi){
		return this.evService.evAra(apartmanIsmi);
	}
	
	@PostMapping("/evEkle")
	Result evEkle( Ev ev) {
		return this.evService.evEkle(ev);
	}
	
	@DeleteMapping("/evSil")
	Result evSil(Ev ev) {
		return this.evService.evSil(ev);
	}
	
	
	
	
	
	
	

}
