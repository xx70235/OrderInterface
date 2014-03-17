package org.xjd.client;

import org.xjd.client.models.NormalProductFeasibilityModel;
import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NormalProductModifyServiceAsync {

	 public void modifyNormalProduct(NormalProductFeasibilityModel npf,AsyncCallback<String> callback);
}
