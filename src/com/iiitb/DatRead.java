package com.iiitb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

 

public class DatRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Utility util=new Utility();
		
		HashMap<Lattice,Integer> hm=new HashMap<>();
		//hm=util.getGroupBy("PRODUCTNAME", "CUSTOMERCITY", "SALESPERSONTYPE");
		hm=util.getGroupBy("PRODUCTNAME", "CUSTOMERCITY", "");
		Set<Lattice> hs=new HashSet<>();
		hs=hm.keySet();
		for(Lattice l:hs)
		{
			System.out.println(l.getAttr1());
			System.out.println(l.getAttr2());
			System.out.println(l.getAttr3());
			System.out.println(hm.get(l));
		}
		
		/*ArrayList<String> allRows=util.getAllRowIdsFromSales();
		HashMap<String,Integer> hm=new HashMap<>();
		for(String id:allRows)
		{
			String pk=util.getStringAttributeForParticularRowId(id, "PRODUCTKEY-fk");
			int sales=util.getIntegerAttributeForParticularRowId(id, "SALE_UNITS");
			if(hm.containsKey(pk))
				hm.put(pk,hm.get(pk)+sales);
			else
				hm.put(pk, sales);
		}
		
		Set<String> hs=new HashSet<>();
		hs=hm.keySet();
		for(String key:hs)
		{
			String rowId=util.getParticularRowIdForStringAttr("PRODUCTKEY", key);
			String name=util.getStringAttributeForParticularRowId(rowId, "PRODUCTNAME");
			System.out.print(name+":");
			System.out.print(hm.get(key));
			System.out.println();
		}*/
		/*ArrayList<String> arr=new ArrayList<>();
		arr.add("Tanmay");
		arr.add("Das");
		arr.add("Kumar");
		ArrayList<ArrayList<String>> res=new ArrayList<>();
		res=util.getAllCombinations(arr);
		System.out.println(res);
		System.out.println("1:"+util.getDimensionName("PRODUCTNAME"));
		System.out.println("1:"+util.getDimensionName("SALESPERSONTYPE"));*/
    }

	}

