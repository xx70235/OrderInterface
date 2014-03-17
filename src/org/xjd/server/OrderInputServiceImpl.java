package org.xjd.server;

import org.xjd.client.OrderInputService;
import org.xjd.client.models.OrderModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class OrderInputServiceImpl extends RemoteServiceServlet implements
		OrderInputService {

	@Override
	public String orderInput(OrderModel om) throws IllegalArgumentException {

		// 输入至数据库
		String result = "生产订单已下达，";
		if(orderRegister(om))
			result+="并已成功注册至课题3运营系统。";
		else
			result+="注册至课题3运营系统未成功，请稍后再试。";
		return result;
	}

	private boolean orderRegister(OrderModel om) {
		//调用课题3运营系统专题产品任务注册接口thematicProductRegister,根据注册情况返回Boolean值
		return true;
		
	}

}
