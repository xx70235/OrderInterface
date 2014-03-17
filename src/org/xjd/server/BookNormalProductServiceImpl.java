package org.xjd.server;

import org.xjd.client.BookNormalProductService;
import org.xjd.client.NormalProductService;
import org.xjd.client.models.NormalProductModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BookNormalProductServiceImpl extends RemoteServiceServlet implements
BookNormalProductService {

	@Override
	public String bookNormalProduct(NormalProductModel npm) {
		// TODO:调用课题3提供的共性产品订购接口normalProductOrderCheck，以npm中的信息为参数
		return "该共性产品订购成功！";
	}

}
