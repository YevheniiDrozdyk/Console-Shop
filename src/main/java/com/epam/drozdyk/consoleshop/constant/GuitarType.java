package com.epam.drozdyk.consoleshop.constant;

/**
 * Holds guitar types.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 14 Mar 2017
 */
public enum GuitarType {
	ACOUSTIC, CLASSIC, ELECTRIC, BASS;

	public static GuitarType getGuitarType(int key) {
		return GuitarType.values()[key];
	}
}
