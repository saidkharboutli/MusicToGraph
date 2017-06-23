package com.musictograph.gtm.main;

import com.musictograph.gtm.Computable;

public class ComputableDriver {
	public static void main(String args[])
	{
		double dub[] = {1.0, 2.0, 1.0};
		Computable computable = new Computable(dub);
		System.out.println(computable.toString());
		System.out.println(computable.compute(13));
	}
}
