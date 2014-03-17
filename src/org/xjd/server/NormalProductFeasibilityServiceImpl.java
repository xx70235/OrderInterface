package org.xjd.server;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xjd.client.NormalProductFeasibilityService;
import org.xjd.client.models.NormalProductFeasibilityModel;
import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.NormalProductModel.ProductStatus;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class NormalProductFeasibilityServiceImpl extends RemoteServiceServlet implements NormalProductFeasibilityService  {

	@Override
	/***
	 * 提交共性产品需求至课题3，如果提交成功，继续调用可行性分析接口。（是否需要返回多个NPF？）
	 */
	public NormalProductFeasibilityModel submitNormalProduct(NormalProductModel npm){

		//TODO:调用课题3的共性产品生产需求提交接口，normalProductionSubmit，如果返回结果为成功，则继续调用课题3的共性产品
		//生产可行性分析获取接口normalProductFeasibilityAquire接口，返回可行性分析结果XML文件
		
		NormalProductFeasibilityModel npf = new NormalProductFeasibilityModel();
		
		InputStream input;
			input = getServletContext().getResourceAsStream(
					"/WEB-INF/testData/NormalProductFeasibility.xml");
		try {
			if (input == null) {
				System.out.println("找不到文件！");
				return null;
			}
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(input);

			Element rootElement = document.getRootElement();
			Element responseparamElement = rootElement.element("responseparam");
			
			Iterator<Element> modulesIterator = responseparamElement.elements("production")
					.iterator();
			while (modulesIterator.hasNext()) {
				
				Element moduleElement = modulesIterator.next();
				Element taskId = moduleElement.element("taskID");
				npf.setTaskID(taskId.getText());
				
				npf.setNpm(npm);
				
				Element proposal = moduleElement.element("proposal");
				String feabsiStr = proposal.attribute("isFeasibility").getText();
				boolean isFeasibility = Boolean.parseBoolean(feabsiStr);
				
				if(isFeasibility)
				{
					Element fee = proposal.element("fee");
					npf.setFee(Float.parseFloat(fee.getText()));
					
					Element productTime = proposal.element("productdate");
					Date productDate = null;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						productDate = formatter.parse(productTime.getText());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					npf.setProductDate(productDate);
					
					Element productCycle =  proposal.element("productcycle");
					npf.setProductCycle(Integer.parseInt(productCycle.getText()));
					
					Element needAssimilation = proposal.element("needAssimilation");
					npf.setNeedAssimilation(Boolean.parseBoolean(needAssimilation.getText()));
					
					Element needValidation = proposal.element("needValidation");
					npf.setNeedValdation(Boolean.parseBoolean(needValidation.getText()));
					
					npf.setFeasibility(true);
					
				}
				else
				{
					npf.setFeasibility(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return npf;
	}

}
