package org.xjd.client;

import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("OrderInput")
public interface OrderInputService extends RemoteService{

	String orderInput(OrderModel om) throws IllegalArgumentException;
}
