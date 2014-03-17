package org.xjd.client;

import java.util.Date;
import java.util.List;

import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.OrderFetchModel;
import org.xjd.client.models.OrderModel;

import com.google.gwt.cell.client.ActionCell;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;

public class OrderFetch extends Composite {

	@UiField
	Label label1;
	@UiField
	ListBox cmProductType;
	@UiField
	DateBox dtbEndtime;
	@UiField
	Button btFetch;
	@UiField
	DateBox dtbStarttime;
	@UiField
	Label label2;
	@UiField
	Label label3;
	@UiField(provided=true) CellTable<OrderModel> tabOrderInfo = new CellTable<OrderModel>();

	
	private static OrderFetchUiBinder uiBinder = GWT
			.create(OrderFetchUiBinder.class);
	
	private OrderFetchServiceAsync ofSvr = GWT.create(OrderFetchService.class);

	interface OrderFetchUiBinder extends UiBinder<Widget, OrderFetch> {
	}

	public OrderFetch() {
		initWidget(uiBinder.createAndBindUi(this));
		cmProductType.addItem("林业专题产品生产订单");
		cmProductType.addItem("农业专题产品生产订单");
		cmProductType.addItem("水资源专题产品生产订单");
		cmProductType.addItem("矿产专题产品生产订单");
		cmProductType.addItem("生态环境专题产品生产订单");
		DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
		dtbStarttime.setFormat(new DateBox.DefaultFormat(dateFormat));
		dtbEndtime.setFormat(new DateBox.DefaultFormat(dateFormat));
		
		addTableCloumn();
	}



	@UiHandler("btFetch")
	void btConfirm(ClickEvent e) {
		// TODO:调用远程功能
		OrderFetchModel of = new OrderFetchModel();
		of.setOrderType(cmProductType.getItemText(cmProductType
				.getSelectedIndex()));
		Date date = dtbStarttime.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		of.setOrderStartTime(dateString);
		date = dtbEndtime.getValue();
		dateString = DateTimeFormat.getMediumDateFormat().format(date);
		of.setOrderEndTime(dateString);
		refreshOrderTable(of);
	}

	private void refreshOrderTable(OrderFetchModel of) {
		// Initialize the service proxy.
		if (ofSvr == null) {
			ofSvr = GWT.create(OrderFetchService.class);
		}

		// Set up the callback object.
		AsyncCallback<OrderModel[] > callback = new AsyncCallback<OrderModel[] >() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(OrderModel[]  result) {
				// TODO Auto-generated method stub
				updateOrderInfoTable(result);
			}
		};

		// Make the call to the stock price service.
		ofSvr.orderFetch(of, callback);
	}
	
	private void addTableCloumn()
	{
		
		// Create id column.
	    TextColumn<OrderModel> taskIdColumn = new TextColumn<OrderModel>() {
	      @Override
	      public String getValue(OrderModel om) {
	        return om.getTaskId();
	      }
	    };

	    // Create name column.
	    TextColumn<OrderModel> nameColumn = new TextColumn<OrderModel>() {
		      @Override
		      public String getValue(OrderModel om) {
		        return om.getName();
		      }
		    };
		    
		    // Create type column.
		    TextColumn<OrderModel> typeColumn = new TextColumn<OrderModel>() {
			      @Override
			      public String getValue(OrderModel om) {
			        return om.getType();
			      }
			    };
	    
	    // Create start date column.
	    TextColumn<OrderModel> startDateColumn = new TextColumn<OrderModel>() {
	      @Override
	      public String getValue(OrderModel om) {
	       return om.getStartDate().toString();
	      }
	    };
	    
	    // Create end date column.
	    TextColumn<OrderModel> endDateColumn = new TextColumn<OrderModel>() {
	      @Override
	      public String getValue(OrderModel om) {
	       return om.getEndDate().toString();
	      }
	    };
	    
	    // Create range column.
	    TextColumn<OrderModel> rangeColum = new TextColumn<OrderModel>() {
	      @Override
	      public String getValue(OrderModel om) {
	       return om.getRange();
	      }
	    };
	    
	    // Create time range scale column.
	    TextColumn<OrderModel> rangeScaleColumn = new TextColumn<OrderModel>() {
	      @Override
	      public String getValue(OrderModel om) {
	       return om.getRangeScale();
	      }
	    };
	    
	    
	    
//	    @SuppressWarnings("unchecked")
//		Column<NormalProductModel,ActionCell> actionColumn = new Column<NormalProductModel,ActionCell>(new ActionCell
//	    	    ("订购",new ActionCell.Delegate<NormalProductModel>()
//	    	    	    {
//
//	    	    			@Override
//	    	    			public void execute(NormalProductModel npm) {
//	    	    				bookNormalProduct(npm);
//	    	    			}
//	    	    	    	
//	    	    	    })){
//	    	
//
//			@Override
//			public ActionCell getValue(NormalProductModel object) {
//				return null;
//			}
//	    	
//	    };
	    
	    // Add the columns.
	    tabOrderInfo.addColumn(taskIdColumn, "TaskID");
	    tabOrderInfo.addColumn(nameColumn, "名称");
	    tabOrderInfo.addColumn(typeColumn, "订单类型");
	    tabOrderInfo.addColumn(startDateColumn, "开始时间");
	    tabOrderInfo.addColumn(endDateColumn, "结束时间");
	    tabOrderInfo.addColumn(rangeColum, "空间覆盖范围");
	    tabOrderInfo.addColumn(rangeScaleColumn, "区域");
	  
//	    tabNormalProduct.setVisible(false);
	}
	
	/***
	 * 更新共性产品检索表中的信息
	 * @param result 共性产品检索结果
	 */
	private void updateOrderInfoTable(OrderModel[] result)
	{
//		tabNormalProduct.setVisible(true);
		// Create a data provider.
	    ListDataProvider<OrderModel> dataProvider = new ListDataProvider<OrderModel>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(tabOrderInfo);

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<OrderModel> list = dataProvider.getList();
	    for (OrderModel om : result) {
	      list.add(om);
	    }
	}
	

	// @UiHandler("dtbStarttime")

}
