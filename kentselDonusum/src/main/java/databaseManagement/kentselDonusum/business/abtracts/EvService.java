package databaseManagement.kentselDonusum.business.abtracts;

import java.util.List;

import org.springframework.data.repository.query.Param;

import databaseManagement.kentselDonusum.core.results.DataResult;
import databaseManagement.kentselDonusum.core.results.Result;
import databaseManagement.kentselDonusum.entities.Ev;

public interface EvService {
	
	
	
	DataResult<List<Ev>> evleriListele();
	
	DataResult<Ev> evAra(String apartmanIsmi);
	
	Result evEkle(Ev ev);

	Result evSil(Ev ev);
}
