package tiny.gs.handler.msg.role.procedure;

import org.tiny.base.Procedure;

import org.tiny.auto.bean.Role;
import org.tiny.auto.table.Roles;

public class PUpdateRoleInfo extends Procedure{

	@Override
	public boolean process() {
		
		long roleId = 0L;
		Role role = Roles.get(roleId);
		
		role.setNickName("I am changed");
		return true;
	}
}
