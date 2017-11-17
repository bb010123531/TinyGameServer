package tiny.client.handler.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.tiny.net.core.AbstractChannelHandlerAdapter;
import org.tiny.net.log.TinyLogger;

import auto.proto.Enum.PROTO_KEY;
import auto.proto.RoleProto;
import auto.proto.RoleProto.C2SComplexTest.Option;
import io.netty.channel.ChannelHandlerContext;
import tools.ProtocolUtil;

public class C2SMessageInit extends AbstractChannelHandlerAdapter {

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		TinyLogger.LOG.debug("=====channelActive");
		// RoleProto.Role.Builder builder = RoleProto.Role.newBuilder();
		// builder.setId(1);
		// builder.setLevel(10);
		// builder.setNickName("first");
		
		Scanner sc = new Scanner(System.in);
		TinyLogger.LOG.error("等待Console输入指令(1/2)");
		while (sc.hasNextLine()) {
			int order = sc.nextInt();
			switch (order) {
			case 1:
				RoleProto.C2SRoleInfo.Builder builder = RoleProto.C2SRoleInfo.newBuilder();
				builder.setId(11);
				builder.setContent("Hello World 1");

				ctx.writeAndFlush(ProtocolUtil.toC2LMessage(PROTO_KEY.C2SRoleInfo_Key_VALUE, builder.build()));
				break;
			case 2:
				RoleProto.C2SComplexTest.Builder c2sComplexTest = RoleProto.C2SComplexTest.newBuilder();
				c2sComplexTest.setRoleId(5748L);
				c2sComplexTest.setNickName("Tiny");
				
				List<Option> options = new ArrayList<>(3);
				for (int i = 1 ; i <= 3 ; i++) {
					options.add(buildOption(i));
				}
				
				c2sComplexTest.addAllOptins(options);
				ctx.writeAndFlush(ProtocolUtil.toC2LMessage(PROTO_KEY.C2SComplexTest_Key_VALUE, c2sComplexTest.build()));
				break;
			}
		}
	}
	
	private Option buildOption(int key) {
		Option.Builder builder = Option.newBuilder();
		
		builder.setOptionType(key);
		switch (key) {
		case 1:
			builder.setHasValue(true);
			builder.setContent(" What A Pity");
			break;
		case 2:
			builder.setHasValue(true);
			builder.setContent(" What a beautiful day~ ");
			break;
		default:
			builder.setHasValue(false);
			builder.setContent(" nothing to do ");
			break;
		}
		return builder.build();
	}
	
	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {

	}
}
