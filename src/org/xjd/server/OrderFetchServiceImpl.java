package org.xjd.server;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xjd.client.OrderFetchService;
import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;
import org.xjd.client.models.NormalProductModel.ProductStatus;
import org.xjd.shared.FieldVerifier;
import org.xjd.utils.CommonUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class OrderFetchServiceImpl extends RemoteServiceServlet implements
		OrderFetchService {

	private List<OrderModel> orderList = new ArrayList<OrderModel>();

	@Override
	public OrderModel[] orderFetch(OrderFetchModel of) throws IllegalArgumentException {
		orderList.clear();
		InputStream input;
		String npXMlDoc;
			input = getServletContext().getResourceAsStream(
					"/WEB-INF/testData/Orders.xml");
		try {
			if (input == null) {
				System.out.println("找不到文件！");
				return null;
			}
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(input);

			Element rootElement = document.getRootElement();
			Element responseParamElem = rootElement.element("responseParam");
			Iterator<Element> modulesIterator = responseParamElem.elements("production").iterator();
			
			while (modulesIterator.hasNext()) {
				OrderModel om = new OrderModel();
				Element moduleElement = modulesIterator.next();
				Element taskID = moduleElement.element("taskID");
				om.setTaskId(taskID.getText());

				Element name = moduleElement.element("name");
				om.setName(name.getText());
				
				Element type = moduleElement.element("type");
				om.setType(type.getText());

				Element startDate = moduleElement.element("startDate");
				String sd = startDate.getText();
				Element endDate = moduleElement.element("endDate");
				String ed = endDate.getText();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date startT = null;
				Date endT = null;
				try {
					startT = formatter.parse(sd);
					endT = formatter.parse(ed);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				om.setStartDate(startT);
				om.setEndDate(endT);

				Element range = moduleElement.element("range");
				om.setRage(range.getText());

				Element rangeScale = moduleElement
						.element("rangeScale");
				om.setRageScale(rangeScale.getText());

				orderList.add(om);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderList.toArray(new OrderModel[0]);
	}



}
