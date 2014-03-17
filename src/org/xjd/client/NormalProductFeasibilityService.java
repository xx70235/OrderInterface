package org.xjd.client;

import org.xjd.client.models.NormalProductFeasibilityModel;
import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("NormalProductFeasibility")

public interface NormalProductFeasibilityService extends RemoteService{
	


 public NormalProductFeasibilityModel submitNormalProduct(NormalProductModel npm);


}
