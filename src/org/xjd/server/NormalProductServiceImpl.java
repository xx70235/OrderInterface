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
import org.w3c.dom.NodeList;
import org.xjd.client.NormalProductService;
import org.xjd.client.OrderFetchService;
import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.NormalProductModel.ProductStatus;
import org.xjd.client.models.OrderModel;
import org.xjd.utils.CommonUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class NormalProductServiceImpl extends RemoteServiceServlet implements
		NormalProductService {

	private List<NormalProductModel> npList = new ArrayList<NormalProductModel>();

	@Override
	public NormalProductModel[] normalProductFetch(NormalProductModel np,
			boolean detailed) throws IllegalArgumentException {
		npList.clear();

		// TODO:调用课题3运营系统得共性产品检索接口normalProductQuery，以np中得信息为参数，返回为共性产品得信息列表xml文件
		// 解析返回得共性产品xml文件
		
		
		
		
		InputStream input;
		String npXMlDoc;
		if (!detailed) {
			
			//**********************************************************
			//测试用：当np的ID为0000或名称为test时，触发检索失败情况，进行共性产品生产需求的提交
			if(np.getName().equals("test"))
			{
				return npList.toArray(new NormalProductModel[0]);
			}
			//**********************************************************
			
			input = getServletContext().getResourceAsStream(
					"/WEB-INF/testData/NormalProducts.xml");

		} else {
			//**********************************************************
			//测试用：当np的ID为0000或名称为test时，触发检索失败情况，进行共性产品生产需求的提交
			if(np.getId().equals("0000"))
			{
				return npList.toArray(new NormalProductModel[0]);
			}
			//**********************************************************
			
			input = getServletContext().getResourceAsStream(
					"/WEB-INF/testData/NormalProducts.xml");
		}

		try {
			if (input == null) {
				System.out.println("找不到文件！");
				return null;
			}
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(input);
			// npXMlDoc = CommonUtils.inputStream2String(input);
			// Document messageDom = XMLParser.parse(npXMlDoc);
			// 根据示例，XML中每个info元素为一个共性产品信息，首先提取info元素

			Element rootElement = document.getRootElement();
			Iterator<Element> modulesIterator = rootElement.elements("info")
					.iterator();
			// rootElement.element("name");获取某一个子元素
			// rootElement.elements("name");获取根节点下子元素moudule节点的集合，返回List集合类型
			// rootElement.elements("module").iterator();把返回的list集合里面每一个元素迭代子节点，全部返回到一个Iterator集合中
			while (modulesIterator.hasNext()) {
				NormalProductModel opm = new NormalProductModel();
				Element moduleElement = modulesIterator.next();
				Element id = moduleElement.element("id");
				opm.setId(id.getText());

				Element name = moduleElement.element("name");
				opm.setName(name.getText());

				Element startDate = moduleElement.element("startdate");
				String sd = startDate.getText();
				Element endDate = moduleElement.element("enddate");
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
				opm.setStartDate(startT);
				opm.setEndDate(endT);

				Element coverScope = moduleElement.element("coverscope");
				opm.setCoverRange(coverScope.getText());

				Element spaceResolution = moduleElement
						.element("spaceresolution");
				opm.setSpaceResolution(spaceResolution.getText());

				Element timeResolution = moduleElement
						.element("timeresolution");
				opm.setTimeResolution(timeResolution.getText());

				Element precision = moduleElement.element("precision");
				opm.setPrecision(Float.parseFloat(precision.getText()));
				
				opm.setNpStatus(ProductStatus.Available);

				npList.add(opm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return npList.toArray(new NormalProductModel[0]);
	}

}