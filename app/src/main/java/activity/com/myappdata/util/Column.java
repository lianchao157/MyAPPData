package activity.com.myappdata.util;

import java.util.ArrayList;
import java.util.List;

public class Column {

	public Column() {
	}
	private String name;

	List<String> values = new ArrayList();


	public Column(String name) {
		this.name = name;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get(int index) {
		return values.get(index);
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public void addValue(String value) {
		values.add(value);
	}

}
