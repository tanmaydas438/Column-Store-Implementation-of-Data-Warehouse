package com.iiitb;

public class Lattice1 {
	private String attr1="";
	private String attr2="";
	private String attr3="";
	private String attr4="";
	private String attr5="";
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
	public String getAttr4() {
		return attr4;
	}
	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}
	public String getAttr5() {
		return attr5;
	}
	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attr1 == null) ? 0 : attr1.hashCode());
		result = prime * result + ((attr2 == null) ? 0 : attr2.hashCode());
		result = prime * result + ((attr3 == null) ? 0 : attr3.hashCode());
		result = prime * result + ((attr4 == null) ? 0 : attr4.hashCode());
		result = prime * result + ((attr5 == null) ? 0 : attr5.hashCode());
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
		Lattice1 other = (Lattice1) obj;
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
		if (attr4 == null) {
			if (other.attr4 != null)
				return false;
		} else if (!attr4.equals(other.attr4))
			return false;
		if (attr5 == null) {
			if (other.attr5 != null)
				return false;
		} else if (!attr5.equals(other.attr5))
			return false;
		return true;
	}
	
	
}
