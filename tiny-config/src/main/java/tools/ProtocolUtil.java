package tools;

import com.google.protobuf.GeneratedMessageV3;

import auto.proto.C2LMessageProto.C2LMessage;

public class ProtocolUtil {
	
	public static C2LMessage toC2LMessage(int msgType, GeneratedMessageV3 message) {
		C2LMessage.Builder builder = C2LMessage.newBuilder();
		builder.setContentMsgType(msgType);
		builder.setContentMsg(message.toByteString());
		return builder.build();
	}
	
	
}
