
public class State_trans {
	private String oldState;
	private char trans;
	private String newState;
	
	public String getOldState() {
		return oldState;
	}
	
	public void setOldState(String oldState) {
		this.oldState = oldState;
	}
	
	public char getTrans() {
		return trans;
	}
	
	public void setTrans(char trans) {
		this.trans = trans;
	}
	
	public String getNewState() {
		return newState;
	}
	
	public void setNewState(String newState) {
		this.newState = newState;
	}
	
	public State_trans(String oldState, char trans, String newState) {
		super();
		this.oldState = oldState;
		this.trans = trans;
		this.newState = newState;
	}
}
