package enums;

public enum Command {	
	
	GO("go") {
		@Override
		public void perform(String value) {
			System.out.println(this.param + " --> " + value);
		}
	};
	
	protected String param;
	
	Command(String param){
		this.param = param;
	}
	
	public abstract void perform(String value);
}
