package databaseManagement.kentselDonusum.core.results;


public class DataResult<T> extends Result {
	// hangi tür veri tipiyle çalışcağımız belli değil
	//<T> ile generic oluşturuyoruz 
	
	private T data;
	public DataResult(T data,boolean success, String message) {
		super(success, message);
		this.data=data;
	}
	
	public DataResult(T data,boolean success) {
		super(success);
		this.data=data;
	}
	
	public T getData() {
		
		return this.data;	
	}

	
	
	

}
