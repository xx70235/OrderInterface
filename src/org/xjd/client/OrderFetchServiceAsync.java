package org.xjd.client;

import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface OrderFetchServiceAsync {
	void orderFetch(OrderFetchModel of, AsyncCallback<OrderModel[]> callback)
			throws IllegalArgumentException;
}
