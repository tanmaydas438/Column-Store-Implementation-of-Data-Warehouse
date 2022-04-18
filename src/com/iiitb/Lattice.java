package com.iiitb;

public class Lattice {
	private String attr1="";
	private String attr2="";
	private String attr3="";
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attr1 == null) ? 0 : attr1.hashCode());
		result = prime * result + ((attr2 == null) ? 0 : attr2.hashCode());
		result = prime * result + ((attr3 == null) ? 0 : attr3.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lattice other = (Lattice) obj;
		if (attr1 == null) {
			if (other.attr1 != null)
				return false;
		} else if (!attr1.equals(other.attr1))
			return false;
		if (attr2 == null) {
			if (other.attr2 != null)
				return false;
		} else if (!attr2.equals(other.attr2))
			return false;
		if (attr3 == null) {
			if (other.attr3 != null)
				return false;
		} else if (!attr3.equals(other.attr3))
			return false;
		return true;
	}
	
}
