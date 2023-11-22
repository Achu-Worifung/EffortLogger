package definitions;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	
	public SimpleStringProperty projectName;
	public IntegerProperty id;
	public String colThree, col1, col2, col4, col5, col6, col7, col8, col9, col10, col11, col12,
	col13, col14, col15, col16, col17, col18, col19, col20, col21, col22, col23, col24, col25;
	public List<String> col;//switch from each colm to individual list
	
	
	public User(int id, String projectName,List<Integer> numbers) {
		this.id = new SimpleIntegerProperty(id);
		this.projectName = new SimpleStringProperty(projectName);
		//converting int list to string
		col = new ArrayList<>();
		for (Integer num:numbers)
		{
			this.col.add(num.toString());
		}
		//setting the col
		this.col1 = col.size() > 0 ? col.get(0) : "";
		this.col2 = col.size() > 1 ? col.get(1) : "";
		this.colThree = col.size() > 2 ? col.get(2) : "";
		this.col4 = col.size() > 3 ? col.get(3) : "";
		this.col5 = col.size() > 4 ? col.get(4) : "";
		this.col6 = col.size() > 5 ? col.get(5) : "";
		this.col7 = col.size() > 6 ? col.get(6) : "";
		this.col8 = col.size() > 7 ? col.get(7) : "";
		this.col9 = col.size() > 8 ? col.get(8) : "";
		this.col10 = col.size() > 9 ? col.get(9) : "";
		this.col11 = col.size() > 10 ? col.get(10) : "";
		this.col12 = col.size() > 11 ? col.get(11) : "";
		this.col13 = col.size() > 12 ? col.get(12) : "";
		this.col14 = col.size() > 13 ? col.get(13) : "";
		this.col15 = col.size() > 14 ? col.get(14) : "";
		this.col16 = col.size() > 15 ? col.get(15) : "";
		this.col17 = col.size() > 16 ? col.get(16) : "";
		this.col18 = col.size() > 17 ? col.get(17) : "";
		this.col19 = col.size() > 18 ? col.get(18) : "";
		this.col20 = col.size() > 19 ? col.get(19) : "";
		this.col21 = col.size() > 20 ? col.get(20) : "";
		this.col22 = col.size() > 21 ? col.get(21) : "";
		this.col23 = col.size() > 22 ? col.get(22) : "";
		this.col24 = col.size() > 23 ? col.get(23) : "";
		this.col25 = col.size() > 24 ? col.get(24) : "";

//		this.colThree = colThree;
//		this.col1 = col1;
//		this.col2 = col2;
//		this.col4 = col4;
//		this.col5 = col5;
//		this.col6 = col6;
//		this.col7 = col7;
//		this.col8 = col8;
//		this.col9 = col9;
//		this.col10 = col10;
//		this.col11 = col11;
//		this.col12 = col12;
//		this.col13 = col13;
//		this.col14 = col14;
//		this.col15 = col15;
//		this.col16 = col16;
//		this.col17 = col17;
//		this.col18 = col18;
//		this.col19 = col19;
//		this.col20 = col20;
//		this.col21 = col21;
//		this.col22 = col22;
//		this.col23 = col23;
//		this.col24 = col24;
//		this.col25 = col25;
		
	}
	
	public String getColThree() {
		return colThree;
	}

	public void setColThree(String colThree) {
		this.colThree = colThree;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCol10() {
		return col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public String getCol11() {
		return col11;
	}

	public void setCol11(String col11) {
		this.col11 = col11;
	}

	public String getCol12() {
		return col12;
	}

	public void setCol12(String col12) {
		this.col12 = col12;
	}

	public String getCol13() {
		return col13;
	}

	public void setCol13(String col13) {
		this.col13 = col13;
	}

	public String getCol14() {
		return col14;
	}

	public void setCol14(String col14) {
		this.col14 = col14;
	}

	public String getCol15() {
		return col15;
	}

	public void setCol15(String col15) {
		this.col15 = col15;
	}

	public String getCol16() {
		return col16;
	}

	public void setCol16(String col16) {
		this.col16 = col16;
	}

	public String getCol17() {
		return col17;
	}

	public void setCol17(String col17) {
		this.col17 = col17;
	}

	public String getCol18() {
		return col18;
	}

	public void setCol18(String col18) {
		this.col18 = col18;
	}

	public String getCol19() {
		return col19;
	}

	public void setCol19(String col19) {
		this.col19 = col19;
	}

	public String getCol20() {
		return col20;
	}

	public void setCol20(String col20) {
		this.col20 = col20;
	}

	public String getCol21() {
		return col21;
	}

	public void setCol21(String col21) {
		this.col21 = col21;
	}

	public String getCol22() {
		return col22;
	}

	public void setCol22(String col22) {
		this.col22 = col22;
	}

	public String getCol23() {
		return col23;
	}

	public void setCol23(String col23) {
		this.col23 = col23;
	}

	public String getCol24() {
		return col24;
	}

	public void setCol24(String col24) {
		this.col24 = col24;
	}

	public String getCol25() {
		return col25;
	}

	public void setCol25(String col25) {
		this.col25 = col25;
	}

	public List<String> getCol() {
		return col;
	}

	public void setCol(List<String> col) {
		this.col = col;
	}

	public void setProjectName(SimpleStringProperty projectName) {
		this.projectName = projectName;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public User(String projectName) {
		this.projectName = new SimpleStringProperty(projectName);
	}

	
	
	public String getProjectName() {
		return projectName.get();
	}
	
	public void setProjectName(String projectName) {
		this.projectName = new SimpleStringProperty(projectName);
	}

	
	 public int getId() {
	        return id.get();
	    }

	    public void setId(int id) {
	        this.id.set(id);
	    }
	
	
}