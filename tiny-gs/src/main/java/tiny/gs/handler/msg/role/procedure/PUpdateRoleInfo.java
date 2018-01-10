package tiny.gs.handler.msg.role.procedure;

import org.tiny.base.Procedure;

import tiny.auto.bean.Role;
import tiny.auto.table.Roles;

public class PUpdateRoleInfo extends Procedure{

	@Override
	public boolean process() {
		
		long roleId = -1L;
		Role role = Roles.get(roleId);
		
		return true;
	}
}
