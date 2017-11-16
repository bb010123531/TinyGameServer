package tiny.auto.bean;
//auto-gen file, do not edit anyway

public class Role {

	private long roleId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String password = "";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private tiny.auto.bean.Friend girlFriend = new tiny.auto.bean.Friend();

	public tiny.auto.bean.Friend getGirlFriend() {
		return girlFriend;
	}

	public void setGirlFriend(tiny.auto.bean.Friend girlFriend) {
		this.girlFriend = girlFriend;
	}

	private java.util.List<tiny.auto.bean.Friend> friends = new java.util.ArrayList<>();

	public java.util.List<tiny.auto.bean.Friend> getFriends() {
		return friends;
	}

	public void setFriends(java.util.List<tiny.auto.bean.Friend> friends) {
		this.friends = friends;
	}

	private java.util.Map<Character, String> bag = new java.util.HashMap<>();

	public java.util.Map<Character, String> getBag() {
		return bag;
	}

	public void setBag(java.util.Map<Character, String> bag) {
		this.bag = bag;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Role [");
		sb.append("roleId=" + roleId);
		sb.append(", ");
		sb.append("id=" + id);
		sb.append(", ");
		sb.append("name=" + name);
		sb.append(", ");
		sb.append("age=" + age);
		sb.append(", ");
		sb.append("password=" + password);
		sb.append(", ");
		sb.append("girlFriend=" + girlFriend);
		sb.append(", ");
		sb.append("friends=" + friends);
		sb.append(", ");
		sb.append("bag=" + bag);
		sb.append("]");

		return sb.toString();
	}
}