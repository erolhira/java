package lambda;

@FunctionalInterface
public interface StringAnalyzer {

	public boolean operate(String source, String target);
}
