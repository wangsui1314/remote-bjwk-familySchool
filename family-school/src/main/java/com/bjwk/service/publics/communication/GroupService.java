package com.bjwk.service.publics.communication;

import com.bjwk.utils.DataWrapper;

public interface GroupService {

	DataWrapper<Void> cerateGroup(String token, String groupName, String type);

}
