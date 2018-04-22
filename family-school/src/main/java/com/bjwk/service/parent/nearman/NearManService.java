package com.bjwk.service.parent.nearman;

import java.util.HashMap;
import java.util.List;

import com.bjwk.model.NearMan;
import com.bjwk.model.NearUsers;
import com.bjwk.utils.DataWrapper;

public interface NearManService {

	 DataWrapper<List<NearUsers>> dearMan(NearMan dearMan);

}
