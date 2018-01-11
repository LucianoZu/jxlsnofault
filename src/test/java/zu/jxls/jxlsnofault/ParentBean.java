package zu.jxls.jxlsnofault;

/**
 * Just a Bean, Parent of two children
 * @author Luciano Zu
 *
 */
public class ParentBean {
	
	private String name = "No-One";
	
	private ChildBean firstChild = new ChildBean("Telemaco");
	private ChildBean secondChild = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ChildBean getFirstChild() {
		return firstChild;
	}
	public void setFirstChild(ChildBean firstChild) {
		this.firstChild = firstChild;
	}
	public ChildBean getSecondChild() {
		return secondChild;
	}
	public void setSecondChild(ChildBean secondChild) {
		this.secondChild = secondChild;
	}
}
