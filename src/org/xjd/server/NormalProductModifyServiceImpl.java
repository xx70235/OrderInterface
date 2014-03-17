package org.xjd.server;

import org.xjd.client.NormalProductFeasibilityService;
import org.xjd.client.NormalProductModifyService;
import org.xjd.client.models.NormalProductFeasibilityModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class NormalProductModifyServiceImpl extends RemoteServiceServlet implements NormalProductModifyService{

	@Override
	public String modifyNormalProduct(NormalProductFeasibilityModel npf) {
		//TODO:将npf打包为xml，调用课题3提供的共性产品更新接口normalProductionModify进行需求的确认。
		return "需求确认成功，等待共性产品生产完成。";
	}
	
	

}
