package org.xjd.client;

import java.util.Date;

import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

public class OrderInput extends Composite  {

	private static OrderInputUiBinder uiBinder = GWT
			.create(OrderInputUiBinder.class);
	
	@UiField ListBox cmOrderType;
	@UiField TextBox tbOrderName;
	@UiField TextBox tbRange;
	@UiField DateBox dtbStarttime;
	@UiField DateBox dtbEndtime;
	@UiField ListBox cmRangeScale;
	
	private OrderInputServiceAsync oiSev = GWT.create(OrderInputService.class);

	interface OrderInputUiBinder extends UiBinder<Widget, OrderInput> {
	}

	public OrderInput() {
		initWidget(uiBinder.createAndBindUi(this));
		cmOrderType.addItem("林业专题产品生产订单");
		cmOrderType.addItem("农业专题产品生产订单");
		cmOrderType.addItem("水资源专题产品生产订单");
		cmOrderType.addItem("矿产专题产品生产订单");
		cmOrderType.addItem("生态环境专题产品生产订单");
		
		cmRangeScale.addItem("青藏高原区");
		cmRangeScale.addItem("农牧交错区");
		
		DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
		dtbStarttime.setFormat(new DateBox.DefaultFormat(dateFormat));
		dtbEndtime.setFormat(new DateBox.DefaultFormat(dateFormat));
	}
	
	@UiHandler("btRegister")
	void handleClick(ClickEvent e)
	{
		OrderModel orderM = new OrderModel();
		orderM.setType(cmOrderType.getItemText(cmOrderType
				.getSelectedIndex()));
		
		Date date = dtbStarttime.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		orderM.setStartDate(date);
		date = dtbEndtime.getValue();
		dateString = DateTimeFormat.getMediumDateFormat().format(date);
		orderM.setEndDate(date);
		orderM.setRage(tbRange.getText());
		orderM.setName(tbOrderName.getText());
		orderM.setRageScale(cmRangeScale.getItemText(cmRangeScale.getSelectedIndex()));
		String taskId = orderM.generateTaskId();
		orderM.setTaskId(taskId);
		refreshOrderTable(orderM);
		
	}
	
	private void refreshOrderTable(OrderModel of) {
		// Initialize the service proxy.
		if (oiSev == null) {
			oiSev = GWT.create(OrderFetchService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
				Window.alert("网络异常，操作失败");
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Window.alert(result);
			}
		};

		// Make the call to the stock price service.
		oiSev.orderInput(of, callback);
	}


}
