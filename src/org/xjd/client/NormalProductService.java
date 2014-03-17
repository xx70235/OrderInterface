package org.xjd.client;

import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.OrderFetchModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("NormalProduct")

public interface NormalProductService extends RemoteService{
	NormalProductModel[] normalProductFetch(NormalProductModel np, boolean detailed) throws IllegalArgumentException;
}
