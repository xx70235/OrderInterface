package org.xjd.client;

import java.util.Date;
import java.util.List;

import org.xjd.client.models.NormalProductFeasibilityModel;
import org.xjd.client.models.NormalProductModel;
import org.xjd.client.models.OrderFetchModel;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;

public class NormalProduct extends Composite {

	private static NormalProductUiBinder uiBinder = GWT
			.create(NormalProductUiBinder.class);
	//共性产品查询服务
	private NormalProductServiceAsync npSvr = GWT.create(NormalProductService.class);
	//共性产品订购服务
	private BookNormalProductServiceAsync bnpSvr = GWT.create(BookNormalProductService.class);
	//共性产品生产需求提交服务（生产可行性反馈）
	private NormalProductFeasibilityServiceAsync npfSvr = GWT.create(NormalProductFeasibilityService.class);
	interface NormalProductUiBinder extends UiBinder<Widget, NormalProduct> {
	}


	@UiField ListBox cmNormalType;
	@UiField TextBox tbNormalName;
	@UiField DateBox dtbStarttime;
	@UiField DateBox dtbEndtime;
	@UiField TextBox tbRange;
	@UiField Button btNormalPQuery; 
	@UiField TextBox tbNormalID;
	@UiField TextBox tbNormalName1;
	@UiField Button btNormalPDetialQuery; 
	@UiField(provided=true) CellTable<NormalProductModel> tabNormalProduct = new CellTable<NormalProductModel>();
	@UiField NormalProductFeasibility normalProductFeasibilityPanel;
	
	public NormalProduct() {
		initWidget(uiBinder.createAndBindUi(this));
		
		cmNormalType.addItem("林业共性产品生产订单");
		cmNormalType.addItem("农业共性产品生产订单");
		cmNormalType.addItem("水资源共性产品生产订单");
		cmNormalType.addItem("矿产共性产品生产订单");
		cmNormalType.addItem("生态环境共性产品生产订单");
		DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
		dtbStarttime.setFormat(new DateBox.DefaultFormat(dateFormat));
		dtbEndtime.setFormat(new DateBox.DefaultFormat(dateFormat));
		addNormalProductOrderColumn();
		
	}
	
	@UiHandler("btNormalPQuery") 
	void handleClick1(ClickEvent e)
	{
		NormalProductModel npm = new NormalProductModel();
		npm.setType(cmNormalType.getItemText(cmNormalType
				.getSelectedIndex()));
		
		String name = tbNormalName.getText();
		npm.setName(name);
		
		Date date = dtbStarttime.getValue();
		npm.setStartDate(date);
		
		date = dtbEndtime.getValue();
		npm.setEndDate(date);
		
		String range = tbRange.getText();
		npm.setCoverRange(range);
		
		queryNP(npm, false);
		
		normalProductFeasibilityPanel.clearInfo();
	}


	@UiHandler("btNormalPDetialQuery") 
	void handleClick(ClickEvent e)
	{
		NormalProductModel npm = new NormalProductModel();
		String id = tbNormalID.getText();
		npm.setId(id);
		
		String name = tbNormalName.getText();
		npm.setName(name);
		
		queryNP(npm, true);
		normalProductFeasibilityPanel.clearInfo();
	}
	
	
	private void bookNormalProduct(NormalProductModel npm)
	{
		if (bnpSvr == null) {
			bnpSvr = GWT.create(BookNormalProductService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Window.alert(result.toString());
				
				
			}
		};

		// Make the call to the stock price service.
		bnpSvr.bookNormalProduct(npm, callback);
	}
	
	/**
	 * 提交共性产品生产需求至课题3，并得到可行性反馈
	 * @param npm
	 */
	private void getNormalProductFeasibility(NormalProductModel npm)
	{
		if (npfSvr == null) {
			npfSvr = GWT.create(NormalProductFeasibilityService.class);
		}
		
		// Set up the callback object.
		AsyncCallback<NormalProductFeasibilityModel> callback = new AsyncCallback<NormalProductFeasibilityModel>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			@Override
			public void onSuccess(NormalProductFeasibilityModel npf) {
				// TODO Auto-generated method stub
				if(npf.isFeasibility())
				{
				Window.alert("生产需求提交成功，请根据返回的生产可行性确认生产需求");
				normalProductFeasibilityPanel.showNormalProductFeasibilityInfo(npf);
				}
				else
				{
					Window.alert("无法满足生产需求！");
				}
				
			}
		};
		
		npfSvr.submitNormalProduct(npm, callback);
		
	}
	
	/***
	 * 调用服务请求课题3的共性产品检索接口，并刷新检索结果表
	 * @param npm 用户输入的共性产品检索信息
	 * @param detailed 是否是以详细信息的形式输入
	 */
	
	private void queryNP(final NormalProductModel npm, boolean detailed )
	{
		// Initialize the service proxy.
				if (npSvr == null) {
					npSvr = GWT.create(NormalProductService.class);
				}

				// Set up the callback object.
				AsyncCallback<NormalProductModel[]> callback = new AsyncCallback<NormalProductModel[]>() {
					public void onFailure(Throwable caught) {
						// TODO: Do something with errors.
					}

					@Override
					public void onSuccess(NormalProductModel[] result) {
						// TODO Auto-generated method stub
						
						if(result.length!=0)
						{
						Window.alert("查询成功！");
						updateCellTable(result);
						}
						else
						//原型中，当查询为详细查询，输入Id为0000时触发
						{
							Window.alert("无存档数据，向课题3提交该共性产品生产需求。");
							getNormalProductFeasibility(npm);
						}
					}
				};

				// Make the call to the stock price service.
				npSvr.normalProductFetch(npm,detailed, callback);
	}
	
	/***
	 * 初始化共性产品检索信息表
	 */
	private void addNormalProductOrderColumn()
	{
		// Create name column.
	    TextColumn<NormalProductModel> idColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	        return npm.getId();
	      }
	    };
//	    idColumn.setSortable(true);

	    // Create address column.
	    TextColumn<NormalProductModel> nameColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getName();
	      }
	    };
//	    nameColumn.setSortable(true);
	    
	    // Create start date column.
	    TextColumn<NormalProductModel> startDateColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getStartDate().toString();
	      }
	    };
	    
	    // Create end date column.
	    TextColumn<NormalProductModel> endDateColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getEndDate().toString();
	      }
	    };
	    
	    // Create space resolution column.
	    TextColumn<NormalProductModel> srColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getSpaceResolution();
	      }
	    };
	    
	    // Create time resolution column.
	    TextColumn<NormalProductModel> trColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getTimeResolution();
	      }
	    };
	    
	    // Create range resolution column.
	    TextColumn<NormalProductModel> rangeColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return npm.getCoverRange();
	      }
	    };
	    
	    // Create range precision column.
	    TextColumn<NormalProductModel> precisionColumn = new TextColumn<NormalProductModel>() {
	      @Override
	      public String getValue(NormalProductModel npm) {
	       return String.valueOf(npm.getPrecision());
	      }
	    };
	    
	    @SuppressWarnings("unchecked")
		Column<NormalProductModel,ActionCell> actionColumn = new Column<NormalProductModel,ActionCell>(new ActionCell
	    	    ("订购",new ActionCell.Delegate<NormalProductModel>()
	    	    	    {

	    	    			@Override
	    	    			public void execute(NormalProductModel npm) {
	    	    				bookNormalProduct(npm);
	    	    			}
	    	    	    	
	    	    	    })){
	    	

			@Override
			public ActionCell getValue(NormalProductModel object) {
				return null;
			}
	    	
	    };
	    
	    // Add the columns.
	    tabNormalProduct.addColumn(idColumn, "ID");
	    tabNormalProduct.addColumn(nameColumn, "名称");
	    tabNormalProduct.addColumn(startDateColumn, "开始时间");
	    tabNormalProduct.addColumn(endDateColumn, "结束时间");
	    tabNormalProduct.addColumn(srColumn, "空间分辨率");
	    tabNormalProduct.addColumn(trColumn, "时间分辨率");
	    tabNormalProduct.addColumn(rangeColumn, "空间覆盖范围");
	    tabNormalProduct.addColumn(precisionColumn, "产品精度");
	    tabNormalProduct.addColumn(actionColumn,"操作");
	    
//	    tabNormalProduct.setVisible(false);
	}
	
	/***
	 * 更新共性产品检索表中的信息
	 * @param result 共性产品检索结果
	 */
	private void updateCellTable(NormalProductModel[] result)
	{
		tabNormalProduct.setVisible(true);
		// Create a data provider.
	    ListDataProvider<NormalProductModel> dataProvider = new ListDataProvider<NormalProductModel>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(tabNormalProduct);

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<NormalProductModel> list = dataProvider.getList();
	    for (NormalProductModel npm : result) {
	      list.add(npm);
	    }
	}
	
}
