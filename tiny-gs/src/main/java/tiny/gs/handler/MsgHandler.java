package tiny.gs.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MsgHandler {
	public String msgName();
	public int msgKey();
}
