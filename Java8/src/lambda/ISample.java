package lambda;

public interface ISample {
	
	//interfaces can have constant fields
	public static final String SAMPLE_NAME = "SAMPLE INTERFACE";
	
	//Java 8 alows using default method implementations inside interfaced with default keyword
	public default void defaultPrint(String text){
		System.out.println(trim(text));
	}
	
	//Java 8 allows static helper methods in interfaces
	public static String trim(String text){
		String trimmed = null;
		if(text != null){
			trimmed = text.trim();
		}
		return trimmed;
	}
}
