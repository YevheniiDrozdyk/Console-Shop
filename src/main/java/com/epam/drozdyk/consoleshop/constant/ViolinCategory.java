package com.epam.drozdyk.consoleshop.constant;

/**
 * Holds violin categories.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 14 Mar 2017
 */
public enum ViolinCategory {
	ARTISANS, FACTORY;

	public static ViolinCategory getViolinCategory(int key) {
		return ViolinCategory.values()[key];
	}
}
