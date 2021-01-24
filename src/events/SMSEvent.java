package events;

import com.bezirk.middleware.messages.Event;

public class SMSEvent extends Event {

	private static final long serialVersionUID = 1L;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SMSEvent(String msg) {
		super();
		this.msg = msg;
	}
}