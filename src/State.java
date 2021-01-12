
public class State {
	private String beginState;//状态机的初始状态
	private String endState;//状态机的接收终止状态
	
	public String getBeginState() {
		return beginState;
	}
	
	public void setBeginState(String beginState) {
		this.beginState = beginState;
	}
	
	public String getEndState() {
		return endState;
	}

	public void setEndState(String endState) {
		this.endState = endState;
	}
	
	public State(String beginState, String endState) {
		super();
		this.beginState = beginState;
		this.endState = endState;
	}

	public State() {
		super();
	}
}
