package com.musictograph.gtm;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Computable {
	double coeffecients[];
	double derivative[];

	public Computable(String function, boolean polynomial) {
		if (polynomial) {
			List<String> terms = expandFunc(Arrays.asList(function.replace(" ", "").split("(?=\\b[+-])")));
			for (int c = 0; c < terms.size(); c++) {
				String term[] = terms.get(c).split("^");
				if (term.length > 1)
					coeffecients[c] = Double.parseDouble(terms.get(0).replace("x", "").replace("+", ""));
			}
			for (int i = 0; i < coeffecients.length - 1; i++)
				derivative[i] = coeffecients[i] * (coeffecients.length - i - 1);
		} else {
			// TODO: graph by increments of .25, find max and min (or one of
			// them)
			// TODO: and then use those values.
		}
	}

	public static List<String> expandFunc(List<String> simplifiedFunc) {
		List<String> expanded = new ArrayList<String>();

		// sort by degree
		for (int c = 0; c < expanded.size() - 1; c++) {
			if (expanded.get(c).split("^").length <= 1) {
				expanded.add(expanded.get(c));
				expanded.remove(expanded.get(expanded.size() - 1));
			} else if ((expanded.get(c).contains("^") && expanded.get(c + 1).contains("^")) && (Integer
					.parseInt(expanded.get(c).split("^")[1]) < Integer.parseInt(expanded.get(c + 1).split("^")[1]))) {
				String temp = expanded.get(c);
				expanded.set(c, expanded.get(c + 1));
				expanded.set(c + 1, temp);
			}
		}

		// find missing terms
		for (int c = 0; c < expanded.size() - 2; c++) {
			if ((Integer.parseInt(expanded.get(c).split("^")[1]) - 1) != Integer
					.parseInt(expanded.get(c + 1).split("^")[1])) {
				expanded.add(c + 1, "0x^" + expanded.get(c).split("^")[1]);
			}
		}
		return expanded;
	}

	// public static double minMax()
	// {
	//
	// }
	//
	// public static int check()
	// {
	//
	// }

	public String toString() {
		String ret = "Coeffecients: " + Arrays.toString(coeffecients) + "\n" + "Derivatives: "
				+ Arrays.toString(derivative);
		return ret;
	}

}
