package com.bjwk.service.parent.nearman;

import java.util.List;

import com.bjwk.model.NearMan;
import com.bjwk.utils.DataWrapper;

public interface NearManService {

	DataWrapper<List<NearMan>> dearMan(NearMan dearMan);

}
