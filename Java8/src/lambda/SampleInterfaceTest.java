package lambda;

import org.junit.Test;

public class SampleInterfaceTest {
			
	@Test
	public void testDefaultMethod(){
		
		ISubSample subSample = new SampleImpl();
		ISample sample = subSample;
		sample.defaultPrint("this is printed by default method");
				
		subSample.subPrint("text from ISubSample.");
		
		System.out.println("----------------------------------------------");
		String[] textArr = {"text as mext", "mext is text"};
		String[] subtextArr = {"mext", "as"};
		for(int i = 0; i < textArr.length; i++){
			boolean contains = subSample.contains(textArr[i], subtextArr[i], new StringAnalyzer() {				
				@Override
				public boolean operate(String source, String target) {
					return source.contains(target);
				}
			});
			subSample.subPrint(textArr[i] + " contains " + subtextArr[i] + ": " + contains);
		}
		
		System.out.println("----------------------------------------------");
		
		//With Java 8, a lambda expression can be substituted for an anonymous inner class.
		//For the sample above, by using lambda the sample becomes: 
		for(int i = 0; i < textArr.length; i++){
			boolean contains = subSample.contains(textArr[i], subtextArr[i], 
								(String text, String subtext) -> text.contains(subtext));
			subSample.subPrint(textArr[i] + " contains " + subtextArr[i] + ": " + contains);
		}
		
		System.out.println("----------------------------------------------");
		
		//Lambda expressions can be treated like variables.
		StringAnalyzer lambdaContains = (t, s) -> t.contains(s);
		boolean contains = subSample.contains("text", "xt", lambdaContains);
		System.out.println("text contains xt: " + contains);
		
		System.out.println("----------------------------------------------");
		
		
	}
}
