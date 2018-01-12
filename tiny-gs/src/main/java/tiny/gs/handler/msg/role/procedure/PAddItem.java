package tiny.gs.handler.msg.role.procedure;

import java.util.Map;

import org.tiny.auto.bean.Role;
import org.tiny.auto.table.Roles;
import org.tiny.base.Procedure;
import org.tiny.net.log.TinyLogger;
import org.tiny.net.tool.CollectionUtil;

public class PAddItem extends Procedure{
	
	private String itemId;
	private int itemNum;
	
	public PAddItem(String itemId, int itemNum) {
		this.itemId = itemId;
		this.itemNum = itemNum;
	}
	
	@Override
	public boolean process() {
		
		long roleId = 0L;
		Role role = Roles.get(roleId);
		
		Map<String, Integer> map = role.getBag();
		
		TinyLogger.LOG.info("before " + map);
		if (itemNum < 0) {
			Integer have = map.get(itemId);
			if (have == null) {
				TinyLogger.LOG.error("donot have this item " + itemId);
				map.clear();
				return false;
			} else {
				if (have.intValue() < -itemNum) {
					TinyLogger.LOG.error("item not enough,  " + itemId);
					map.clear();
					return false;
				} 
			}
		}
		
		CollectionUtil.incrMapIntValue(map, itemId, itemNum);
		TinyLogger.LOG.info("end   " + map);
		return true;
	}

}
