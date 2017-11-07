package tools;

import com.google.protobuf.GeneratedMessageV3;

import auto.proto.C2LMessageProto.C2LMessage;
import auto.proto.L2GMessageProto.L2GMessage;

public class ProtocolUtil {
	
	public static C2LMessage toC2LMessage(int msgType, GeneratedMessageV3 message) {
		C2LMessage.Builder builder = C2LMessage.newBuilder();
		builder.setContentMsgType(msgType);
		builder.setContentMsg(message.toByteString());
		return builder.build();
	}
	
	public static L2GMessage toL2GMessage(String clientToLink, int msgType, com.google.protobuf.ByteString byteString) {
		L2GMessage.Builder builder = L2GMessage.newBuilder();
		builder.setClient2LinkChannelId(clientToLink);
		builder.setContentMsgType(msgType);
		builder.setContentMsg(byteString);
		return builder.build();
	}
}
