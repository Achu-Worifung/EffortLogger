package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	
	private SimpleStringProperty projectName;
	public IntegerProperty id;
	private Integer colThree, col1, col2, col4, col5, col6, col7, col8, col9, col10, col11, col12,
	col13, col14, col15, col16, col17, col18, col19, col20, col21, col22, col23, col24, col25;
	
	
	public User(int id, String projectName, Integer colThree, Integer col1, Integer col2, Integer col4,
			Integer col5, Integer col6, Integer col7, Integer col8, Integer col9, Integer col10, Integer col11,
			Integer col12, Integer col13, Integer col14, Integer col15, Integer col16, Integer col17, Integer col18,
			Integer col19, Integer col20, Integer col21, Integer col22, Integer col23, Integer col24, Integer col25) {
		this.id = new SimpleIntegerProperty(id);
		this.projectName = new SimpleStringProperty(projectName);
		this.colThree = colThree;
		this.col1 = col1;
		this.col2 = col2;
		this.col4 = col4;
		this.col5 = col5;
		this.col6 = col6;
		this.col7 = col7;
		this.col8 = col8;
		this.col9 = col9;
		this.col10 = col10;
		this.col11 = col11;
		this.col12 = col12;
		this.col13 = col13;
		this.col14 = col14;
		this.col15 = col15;
		this.col16 = col16;
		this.col17 = col17;
		this.col18 = col18;
		this.col19 = col19;
		this.col20 = col20;
		this.col21 = col21;
		this.col22 = col22;
		this.col23 = col23;
		this.col24 = col24;
		this.col25 = col25;
		
	}
	
	public User(String projectName) {
		this.projectName = new SimpleStringProperty(projectName);
	}

	public User(String projectName, Integer colThree)
	{
		this.projectName = new SimpleStringProperty(projectName);
		this.colThree = colThree;
	}
	
	public String getProjectName() {
		return projectName.get();
	}
	
	public void setProjectName(String projectName) {
		this.projectName = new SimpleStringProperty(projectName);
	}

	public Integer getColThree() {
		return colThree;
	}
	
	public void setColThree(Integer colThree) {
		this.colThree = colThree;
	}

	public Integer getCol1() {
		return col1;
	}

	public void setCol1(Integer col1) {
		this.col1 = col1;
	}

	public Integer getCol2() {
		return col2;
	}

	public void setCol2(Integer col2) {
		this.col2 = col2;
	}

	public Integer getCol4() {
		return col4;
	}

	public void setCol4(Integer col4) {
		this.col4 = col4;
	}

	public Integer getCol5() {
		return col5;
	}

	public void setCol5(Integer col5) {
		this.col5 = col5;
	}

	public Integer getCol6() {
		return col6;
	}

	public void setCol6(Integer col6) {
		this.col6 = col6;
	}

	public Integer getCol7() {
		return col7;
	}

	public void setCol7(Integer col7) {
		this.col7 = col7;
	}

	public Integer getCol8() {
		return col8;
	}

	public void setCol8(Integer col8) {
		this.col8 = col8;
	}

	public Integer getCol9() {
		return col9;
	}

	public void setCol9(Integer col9) {
		this.col9 = col9;
	}

	public Integer getCol10() {
		return col10;
	}

	public void setCol10(Integer col10) {
		this.col10 = col10;
	}

	public Integer getCol11() {
		return col11;
	}

	public void setCol11(Integer col11) {
		this.col11 = col11;
	}

	public Integer getCol12() {
		return col12;
	}

	public void setCol12(Integer col12) {
		this.col12 = col12;
	}

	public Integer getCol13() {
		return col13;
	}

	public void setCol13(Integer col13) {
		this.col13 = col13;
	}

	public Integer getCol14() {
		return col14;
	}

	public void setCol14(Integer col14) {
		this.col14 = col14;
	}

	public Integer getCol15() {
		return col15;
	}

	public void setCol15(Integer col15) {
		this.col15 = col15;
	}

	public Integer getCol16() {
		return col16;
	}

	public void setCol16(Integer col16) {
		this.col16 = col16;
	}

	public Integer getCol17() {
		return col17;
	}

	public void setCol17(Integer col17) {
		this.col17 = col17;
	}

	public Integer getCol18() {
		return col18;
	}

	public void setCol18(Integer col18) {
		this.col18 = col18;
	}

	public Integer getCol19() {
		return col19;
	}

	public void setCol19(Integer col19) {
		this.col19 = col19;
	}

	public Integer getCol20() {
		return col20;
	}

	public void setCol20(Integer col20) {
		this.col20 = col20;
	}

	public Integer getCol21() {
		return col21;
	}

	public void setCol21(Integer col21) {
		this.col21 = col21;
	}

	public Integer getCol22() {
		return col22;
	}

	public void setCol22(Integer col22) {
		this.col22 = col22;
	}

	public Integer getCol23() {
		return col23;
	}

	public void setCol23(Integer col23) {
		this.col23 = col23;
	}

	public Integer getCol24() {
		return col24;
	}

	public void setCol24(Integer col24) {
		this.col24 = col24;
	}

	public Integer getCol25() {
		return col25;
	}

	public void setCol25(Integer col25) {
		this.col25 = col25;
	}
	
	
}