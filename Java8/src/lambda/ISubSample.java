package lambda;

/*
 * Interfaces can extend interfaces.
 * So now any class implementing ISubSample must implement all the methods of ISample in addition to the new method specified here.
 */
public interface ISubSample extends ISample {
	
	public void subPrint(String text);
	public boolean contains(String text, String subText, StringAnalyzer analyzer);
}
