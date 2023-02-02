package databaseManagement.kentselDonusum.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import databaseManagement.kentselDonusum.business.abtracts.YikimService;
import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.ErrorResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.core.results.SuccessDataResult;
import databaseManagement.kentselDonusum.core.results.SuccessResult;
import databaseManagement.kentselDonusum.dataAccess.YikimDao;
import databaseManagement.kentselDonusum.entities.Yikim;

@Service
public class YikimManager implements YikimService{
	
	private YikimDao yikimDao;

	@Autowired
	public YikimManager(YikimDao yikimDao) {
		super();
		this.yikimDao = yikimDao;
	}

	@Override
	public Result yikimEkle(Yikim yikim) {
		
		this.yikimDao.yikimEkle(yikim.getEv().getId(), yikim.getFirma().getId(),yikim.getYikimZamani(), yikim.getMaliyet(), yikim.isOdemeBilgisi());
		return new SuccessResult("Yıkım bilgileri eklendi");
	}

	

	@Override
	public Result odemeYap(int evId, int tutar) {
		int control = this.yikimDao.yikimOdemesi(evId, tutar);
		if(control == 1) {
			return new SuccessResult("Ödeme yapıldı");
		}
		return new ErrorResult("Ödeme gerçekleştirilemdi");
	}

	@Override
	public DataResult<List<Yikim>> maliyetiveIdsiDusukYikimlar() {
		
		return new SuccessDataResult<List<Yikim>>(this.yikimDao.maliyetiveIdsiDusukYikimlar());
	}

	

}
