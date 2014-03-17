package org.xjd.client;

import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NormalProductServiceAsync {

	void normalProductFetch(NormalProductModel np, boolean detailed,
			AsyncCallback<NormalProductModel[]> callback);

	



}
