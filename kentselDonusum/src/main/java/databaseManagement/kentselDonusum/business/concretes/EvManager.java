package databaseManagement.kentselDonusum.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import databaseManagement.kentselDonusum.business.abtracts.EvService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.core.results.SuccessDataResult;
import databaseManagement.kentselDonusum.core.results.SuccessResult;
import databaseManagement.kentselDonusum.dataAccess.EvDao;
import databaseManagement.kentselDonusum.entities.Ev;

@Service
public class EvManager implements EvService {
	
	private EvDao evdao;

	@Autowired
	public EvManager(EvDao evdao) {
		super();
		this.evdao = evdao;
	}


	@Override
	public DataResult<List<Ev>> evleriListele() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Ev>>
		(this.evdao.evleriListele(),"Evler listeleniyor");
	}


	@Override
	public DataResult<Ev> evAra(String apartmanIsmi) {
		
		return new SuccessDataResult<Ev>(this.evdao.evAra(apartmanIsmi),"Aradığınız ev ");
	}


	@Override
	public Result evEkle(Ev ev) {
		
		this.evdao.evEkle(ev.getApartmanIsmi(),ev.getApartmanNo(),ev.getAdres(),ev.getKonutSahibi().getId());
		return new Result();
	}


	@Override
	public Result evSil(Ev ev) {
		this.evdao.evSil(ev.getApartmanIsmi());
		return new SuccessResult(ev.getId() +  " numaralı " + ev.getApartmanIsmi() + " isimli apartman silindi");
	}


	



	
	


}
