package lambda;

public class SampleImpl implements ISubSample {

	@Override
	public void subPrint(String text) {
		defaultPrint("subPrint: " + text);
	}

	@Override
	public boolean contains(String text, String subText, StringAnalyzer analyzer) {
		return analyzer.operate(text, subText);
	}
	
}
