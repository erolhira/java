package enums;

/*
 * The constructor may not be public or protected.
 * You may not instantiate an enum instance with new.
 */
public enum PowerState {
	
	OFF("State is OFF"), ON("State is ON"), SUSPEND("State is SUSPEND");
	
	private String description;
	
	private PowerState(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
