package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import org.fao.fi.commons.FigisException;

public class FaoAreaInterpreter {

	public FaoArea civiliseRawCode(String rawFaoAreaCode) {

		/**
		 * ^ means at the beginning of the string + for this number of loops & until the end.
		 */
		if (!rawFaoAreaCode.matches("^[a-zA-Z0-9.]+$")) {
			throw new FigisException("rawFaoAreaCode does not comply with  ^[a-zA-Z0-9.]+$ " + rawFaoAreaCode);
		}
		String codes[] = rawFaoAreaCode.split("\\.");
		FaoArea faoArea = new FaoArea();
		faoArea.setFaoAreaType(FaoAreaType.values()[codes.length - 1]);
		if (faoArea.getFaoAreaType() == null) {
			throw new FigisException("code is not parseble: " + rawFaoAreaCode);
		}
		faoArea.setValue(rawFaoAreaCode);
		return faoArea;
	}

	public boolean includes(String bigRawCode, String smallRawCode) {
		// todo the checks
		civiliseRawCode(bigRawCode);
		civiliseRawCode(smallRawCode);
		String codesBig[] = bigRawCode.split("\\.");
		String codesSmall[] = smallRawCode.split("\\.");
		boolean superiorOrEqual = codesBig.length <= codesSmall.length;
		if (superiorOrEqual) {
			// Also the value superiority needs to be checked.
			int i = 0;
			for (String bigCode : codesBig) {
				if (!codesSmall[i++].equals(bigCode)) {
					superiorOrEqual = false;
				}
			}
		}
		return superiorOrEqual;
	}

	public boolean intersects(String firstRawCode, String secondRawCode) {
		// todo the checks
		civiliseRawCode(firstRawCode);
		civiliseRawCode(secondRawCode);
		String codesFirst[] = firstRawCode.split("\\.");
		String codesSecond[] = secondRawCode.split("\\.");
		int smallestNumber = 0;
		if (codesFirst.length <= codesSecond.length) {
			smallestNumber = codesFirst.length;
		} else {
			smallestNumber = codesSecond.length;
		}
		boolean equal = true;
		for (int i = 0; i < smallestNumber; i++) {
			if (!codesFirst[i].equals(codesSecond[i])) {
				equal = false;
			}
		}
		return equal;
	}

}
