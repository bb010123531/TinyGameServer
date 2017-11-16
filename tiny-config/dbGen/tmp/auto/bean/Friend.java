package tiny.auto.bean;
//auto-gen file, do not edit anyway

public class Friend {

	private java.util.List<Long> friends = new java.util.ArrayList<>();

	public java.util.List<Long> getFriends() {
		return friends;
	}

	public void setFriends(java.util.List<Long> friends) {
		this.friends = friends;
	}

	private java.util.List<Long> applyList = new java.util.ArrayList<>();

	public java.util.List<Long> getApplyList() {
		return applyList;
	}

	public void setApplyList(java.util.List<Long> applyList) {
		this.applyList = applyList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Friend [");
		sb.append("friends=" + friends);
		sb.append(", ");
		sb.append("applyList=" + applyList);
		sb.append("]");

		return sb.toString();
	}
}