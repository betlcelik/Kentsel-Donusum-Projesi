package databaseManagement.kentselDonusum.core.results;

public class Result {
	private boolean success;
	private String message;
	
	public Result() {
		
	}
	public Result(boolean success) {
		this.success=success;
	}
	
	public Result(boolean success,String message) {
		this(success); // bu classtaki tek parametreli ctoru çağırdı
		this.message=message;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	
	
	
	

}
