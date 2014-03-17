package org.xjd.client;

import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("OrderFetch")
public interface OrderFetchService extends RemoteService {
	OrderModel[] orderFetch(OrderFetchModel of) throws IllegalArgumentException;
}
