package lms.model;

public class UIMenu {

	private String courseCode;
	private int active;
	private boolean showCourse;

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	public UIMenu(String courseCode, int active, boolean showCourse) {
		super();
		this.courseCode = courseCode;
		this.active = active;
		this.showCourse = showCourse;
	}

	/**
	 * @param courseCode
	 *            the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * @return the showCourse
	 */
	public boolean isShowCourse() {
		return showCourse;
	}

	/**
	 * @param showCourse
	 *            the showCourse to set
	 */
	public void setShowCourse(boolean showCourse) {
		this.showCourse = showCourse;
	}

}
