package basics;

public class SuperClass {

	public SuperClass(){
		System.out.println("superclass default constructor");
	}
	
	public static void main(String args[]){ 
		new SubClass("");
		new SubClass.StaticNestedClass();
	}
	
}
