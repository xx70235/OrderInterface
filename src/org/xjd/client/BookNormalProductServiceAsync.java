package org.xjd.client;

import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BookNormalProductServiceAsync {

	void bookNormalProduct(NormalProductModel npm,
			AsyncCallback<String> callback);

}
