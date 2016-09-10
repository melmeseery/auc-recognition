/**
 *
 */
package tasks;

/**
 * @author Maha
 *
 */
public class TaskSettings<Obj> {
	private  String name;
	private Obj value;
	public Obj getValue() {
		return value;
	}
	public void setValue(Obj value) {
		this.value = value;
	}
	/**
	 *
	 */
	public TaskSettings() {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



}
