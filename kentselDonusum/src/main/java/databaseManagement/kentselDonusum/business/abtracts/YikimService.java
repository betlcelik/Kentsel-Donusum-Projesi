package databaseManagement.kentselDonusum.business.abtracts;

import java.util.List;

import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.Ev;
import databaseManagement.kentselDonusum.entities.Yikim;

public interface YikimService {
	
	public Result yikimEkle(Yikim yikim);
	
	public Result odemeYap(int evId,int tutar);
	DataResult<List<Yikim>> maliyetiveIdsiDusukYikimlar();
	

}
