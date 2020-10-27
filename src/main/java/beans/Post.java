package beans;

/**
 * 部署のBeanクラス
 * @author yata1
 */
public class Post {
    /** 識別ID */
	private int id = 0;
	/** 部署名 */
	private String name = "";

	public static final int NAME_MAX_SIZE = 50;

	public Post() {}

	public Post(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
