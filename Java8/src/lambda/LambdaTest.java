package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class LambdaTest {

	public static void main(String[] args) {
		
		StringAnalyzer saContains = (String s, String t) -> s.contains(t);
		System.out.println(saContains.operate("java8", "8"));
		
		StringAnalyzer saEndsWith = (s, t) -> s.endsWith(t);		
		System.out.println(saEndsWith.operate("java8", "8"));
		
		Runnable r = () -> System.out.println("xyz");
		r.run();
		
		List<String> list = Arrays.asList(new String[]{"1", "2", "3", "4", "4"});
		list.forEach(s -> System.out.println(s));
		
		list.stream()
			.filter(s -> !s.equals("1"))
			.forEach(s -> System.out.println(s));
		
		//filter as predicate, same as lambda above
		Predicate<String> casedStrings = s -> !s.equals("1");
		list.stream() //source
		.filter(casedStrings).filter(s -> !s.equals("2")) //intermediate operation: Filter, Map, Peek
		.forEach(s -> System.out.println("predicate: " + s)); //Terminal Operation: forEach, count, sum, average, min, max, collect
		
		//
		Consumer<String> logger = t -> System.out.println("log: " + t);
		list.stream().forEach(logger);		
		
		//ToDoubleFunction
		ToDoubleFunction<String> dbl = t -> Double.parseDouble(t);
		list.forEach(t -> System.out.println(dbl.applyAsDouble(t)));		
		//
		
		Stream.of("s1", "s2").filter(e -> e.equals("s2")).forEach(logger);
		
		//map, peek
		list.stream().filter(t -> !t.equals("1"))
			.peek(logger) //peek runs consumer for each element in the stream
			.map(t -> Integer.parseInt(t) * 3) //map listedeki her elemani 3 ile carpip yeni bir stream olusturdu.
			.forEach(rs -> System.out.println("multplied by 3: " + rs));
	
		//findFirst
		Optional<String> first = list.stream()
			.peek(t -> System.out.println("stream not filtered: " + t))
			.filter(t -> !t.equals("1"))
			.peek(t -> System.out.println("stream filtered: " + t))
			.findFirst();
		
		if(first.isPresent()){
			logger.accept(first.get());
		}				
		
		System.out.println("asdasdasdasdadasdasd");
	}

}
