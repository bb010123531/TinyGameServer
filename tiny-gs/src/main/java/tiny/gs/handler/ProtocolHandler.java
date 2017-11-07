package tiny.gs.handler;

import com.google.protobuf.InvalidProtocolBufferException;

import auto.proto.L2GMessageProto.L2GMessage;

public interface ProtocolHandler {
	public void process(L2GMessage l2g) throws InvalidProtocolBufferException;
}
