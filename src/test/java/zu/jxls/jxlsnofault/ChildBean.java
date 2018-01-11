package zu.jxls.jxlsnofault;

/**
 * A bean with a name
 * @author Luciano Zu
 *
 */
public class ChildBean {

	private String name;

	public ChildBean() {
		super();
	}

	public ChildBean(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
