package tiny.gs.handler;

import com.google.protobuf.InvalidProtocolBufferException;

import tiny.gs.task.UserTask;

public interface ProtocolHandler {
	public void process(UserTask userTask) throws InvalidProtocolBufferException;
}
