package com.musictograph.gtm;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Computable {
	double coeffecients[];
	double derivative[];

	public Computable(String function, boolean polynomial) {
		if (polynomial) {
			List<String> terms = Arrays.asList(function.replace(" ", "").split("\r[+-]"));
			for (int c = 0; c < terms.size(); c++) {
				String term[] = terms.get(c).split("^");
				if (term.length > 1)
					coeffecients[c] = Double.parseDouble(terms.get(0).replace("x", ""));
			}

			for (int i = 0; i < coeffecients.length - 1; i++)
				derivative[i] = coeffecients[i] * (coeffecients.length - i - 1);

		} else {
			// TODO: graph by increments of .25, find max and min (or one of
			// them)
			// TODO: and then use those values.
		}
	}

	public static String expandFunc(String simplifiedFunc) {
		String expanded = "";

		return expanded;
	}

}
