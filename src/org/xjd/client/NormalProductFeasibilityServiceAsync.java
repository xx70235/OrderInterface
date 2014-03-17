package org.xjd.client;

import org.xjd.client.models.NormalProductFeasibilityModel;
import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NormalProductFeasibilityServiceAsync {

	 public void submitNormalProduct(NormalProductModel npm,AsyncCallback<NormalProductFeasibilityModel> callback);

}
