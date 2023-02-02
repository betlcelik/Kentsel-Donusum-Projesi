package databaseManagement.kentselDonusum.business.abtracts;

import java.util.List;


import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.Ev;
import databaseManagement.kentselDonusum.entities.KonutSahibi;


public interface KonutSahibiService {
	
	public DataResult<List<String>> IsmeGoreEvleriniGetir(String isim);
	public DataResult<List<KonutSahibi>> konutSahipleriniListele();
	public Result konutSahibiEkle(KonutSahibi konutSahibi);
	DataResult<List<Integer>> birdenFazlaEviOlankonutSahipleriniListele();
}
