package com.app.baseLogic;

import java.util.ArrayList;

public class SortLogic {
	public Boolean verifyItemsSortOrder(ArrayList<String> list) {
		if (list == null || list.isEmpty())
			return false;

		if (list.size() == 1)
			return true;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(list.get(i - 1)) < 0)
				return false;
		}
		return true;
	}

}
