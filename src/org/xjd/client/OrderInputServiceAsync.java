package org.xjd.client;

import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrderInputServiceAsync {
	
	void orderInput(OrderModel om, AsyncCallback<String> callback)
			throws IllegalArgumentException;

}
