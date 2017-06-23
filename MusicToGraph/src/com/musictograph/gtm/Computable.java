package com.musictograph.gtm;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Computable {
	double coeffecients[];
	double derivative[];

	public Computable(double function[]) {
//		List<String> terms = expandFunc(
//				new LinkedList<String>(Arrays.asList(function.replace(" ", "").split("(?=\\b[+-])"))));
//		for (int c = 0; c < terms.size(); c++) {
//			String term[] = terms.get(c).split("\\^");
//			if (term.length > 1)
//			{
//				double coef;
//				String strCoef = terms.get(0).split("x")[0];
//				if(strCoef.equals("")) coef = 1;
//				else coef = Double.parseDouble(strCoef);
//				coeffecients[c] = coef;
//			}
//		}
		coeffecients = function;
		derivative = new double[coeffecients.length - 1];
		for (int i = 0; i < coeffecients.length - 1; i++)
			derivative[i] = coeffecients[i] * (coeffecients.length - i - 1);
	}
	
	 public double minMax()
	 {
		 if(coeffecients.length == 1)
		 {
			 return coeffecients[0];
		 }
		 if(derivative.length == 1)
		 {
			 return coeffecients[0];
		 }
		 
		if(derivative.length == 2)
		{
			double m = coeffecients[0];
			double b = coeffecients[1];
			return -b/m;
		}
		return -999.9;
	 }
		
	 public int check()
	 {
		if(coeffecients.length <= 1) {
			return 0;
		}
		double point = minMax();
		
		double pointPlus = point + 1;
		double pointMinus = point - 1;
		
		double pY = (derivative[1] * point) + derivative[0];
		double pPY = (derivative[1] * pointPlus) + derivative[0];
		double pMY = (derivative[1] * pointMinus) + derivative[0];
		
		if(pPY > 0 && 0 > pMY) return -1;
		else if (pMY > 0 && 0 > pPY) return 1;
		else return 0;
	 }
	
	public String toString() {
		String ret = "Coeffecients: " + Arrays.toString(coeffecients) + "\n" + "Derivatives: "
				+ Arrays.toString(derivative);
		return ret;
	}
	
	public double compute(double xVal)
	{
		double pY = (coeffecients[2] * Math.pow(xVal, 2)) + (coeffecients[1] * xVal) + coeffecients[0];
		return pY;
	}
	
//	public static List<String> expandFunc(LinkedList<String> simplifiedFunc) {
//		List<String> expanded = simplifiedFunc;
//
//		// sort by degree
//		for (int c = 0; c < expanded.size() - 1; c++) {
//			if (expanded.get(c).split("\\^").length <= 1) {
//				expanded.add(expanded.get(c));
//				expanded.remove(expanded.get(expanded.size() - 1));
//			} else if ((expanded.get(c).contains("^") && expanded.get(c + 1).contains("^"))
//					&& (Integer.parseInt(expanded.get(c).split("\\^")[1]) < Integer
//							.parseInt(expanded.get(c + 1).split("\\^")[1]))) {
//				String temp = expanded.get(c);
//				expanded.set(c, expanded.get(c + 1));
//				expanded.set(c + 1, temp);
//			}
//		}
//		// find missing terms
//		if (expanded.size() == 1 && expanded.get(0).contains("^")
//				&& Integer.parseInt(expanded.get(0).split("\\^")[1]) >= expanded.size()) {
//			int c = 0;
//			while (expanded.get(c).contains("x") && expanded.get(c).contains("^")) {
//				expanded.add(("0x^" + (Integer.parseInt(expanded.get(c).split("\\^")[1]) - 1)).replace("x^1", "x")
//						.replace("0x^0", "0"));
//				c++;
//			}
//			expanded.add("0");
//		} else {
//			for (int c = 0; c < expanded.size() - 1; c++) {
//				try {
//					int minusOne = Integer.parseInt(expanded.get(c).split("\\^")[1]) - 1;
//					if (minusOne != Integer
//							.parseInt(expanded.get(c + 1).split("\\^")[1])) {
//						expanded.add(c + 1, "0x^" + (Integer.parseInt(expanded.get(c).split("\\^")[1]) - 1));
//					}
//				} catch(ArrayIndexOutOfBoundsException e)
//				{
//					
//				}
//				
//			}
//		}
//		return expanded;
//	}
}
