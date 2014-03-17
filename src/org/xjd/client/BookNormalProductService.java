package org.xjd.client;

import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("BookNormalProduct")

public interface BookNormalProductService  extends RemoteService{

	
	public String bookNormalProduct(NormalProductModel npm);

	
}
