package databaseManagement.kentselDonusum.business.concretes;

import java.util.List;


import org.springframework.stereotype.Service;

import databaseManagement.kentselDonusum.business.abtracts.KonutSahibiService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.core.results.SuccessDataResult;
import databaseManagement.kentselDonusum.core.results.SuccessResult;
import databaseManagement.kentselDonusum.dataAccess.KonutSahibiDao;
import databaseManagement.kentselDonusum.entities.KonutSahibi;


@Service
public class KonutSahibiManager implements KonutSahibiService{
	
	private KonutSahibiDao konutSahibiDao;

	public KonutSahibiManager(KonutSahibiDao konutSahibiDao) {
		super();
		this.konutSahibiDao = konutSahibiDao;
	}

	@Override
	public DataResult<List<String>> IsmeGoreEvleriniGetir(String isim) {
		
		return new SuccessDataResult<List<String>>(this.konutSahibiDao.IsmeGoreEvleriniGetir(isim));
	}

	@Override
	public Result konutSahibiEkle(KonutSahibi konutSahibi) {
		this.konutSahibiDao.konutSahibiEkle(konutSahibi.getIsim(), konutSahibi.getSoyisim(), konutSahibi.getTelNo());
		return new SuccessResult("Konut sahibi eklendi");
	}

	@Override
	public DataResult<List<KonutSahibi>> konutSahipleriniListele() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<KonutSahibi>>(this.konutSahibiDao.konutSahipleriniListele(),"Konut Sahipleri Listeleniyor");
	}

	@Override
	public DataResult<List<Integer>> birdenFazlaEviOlankonutSahipleriniListele() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Integer>>(this.konutSahibiDao.birdenFazlaEviOlankonutSahipleriniListele(), "Birden fazla evi olanlar listeleniyor");
	}

}
