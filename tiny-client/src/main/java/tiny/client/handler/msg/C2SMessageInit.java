package tiny.client.handler.msg;

import java.util.Scanner;

import org.tiny.net.core.AbstractChannelHandlerAdapter;

import auto.proto.Enum.PROTO_KEY;
import auto.proto.RoleProto;
import io.netty.channel.ChannelHandlerContext;
import tools.ProtocolUtil;

public class C2SMessageInit extends AbstractChannelHandlerAdapter {

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		System.err.println("=====channelActive");
		// RoleProto.Role.Builder builder = RoleProto.Role.newBuilder();
		// builder.setId(1);
		// builder.setLevel(10);
		// builder.setNickName("first");
		
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNextLine()) {
			System.err.println("等待输入指令·~ ");
			int order = sc.nextInt();
			switch (order) {
			case 1:
				RoleProto.C2SRoleInfo.Builder builder = RoleProto.C2SRoleInfo.newBuilder();
				builder.setId(11);
				builder.setContent("Hello World 1");

				ctx.writeAndFlush(ProtocolUtil.toC2LMessage(PROTO_KEY.C2SRoleInfo_Key_VALUE, builder.build()));
				break;
			case 2:
				RoleProto.C2SRoleInfo.Builder builder2 = RoleProto.C2SRoleInfo.newBuilder();
				builder2.setId(11);
				builder2.setContent("Hello World 2");

				ctx.writeAndFlush(ProtocolUtil.toC2LMessage(PROTO_KEY.C2SRoleInfo_Key_VALUE, builder2.build()));
				break;
			}
		}
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {

	}
}
