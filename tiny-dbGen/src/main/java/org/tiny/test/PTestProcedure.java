package org.tiny.test;

import org.tiny.base.Procedure;

//import auto.bean.Role;
//import auto.table.Tables;

public class PTestProcedure extends Procedure{
	
	private long roleId;
	
	public PTestProcedure(long roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public boolean process() {
		
//		Role role = Tables.getInstance().getRole(roleId);
		
//		role.setNickName("new name");
		return true;
	}
}
