package org.xjd.client;

import org.xjd.client.OrderFetch.OrderFetchUiBinder;
import org.xjd.client.models.NormalProductFeasibilityModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.CheckBox;

public class NormalProductFeasibility extends Composite {
	
	@UiField TextBox tbTaskId;
	@UiField TextBox tbName;
	@UiField TextBox tbProductCycle;
	@UiField TextBox tbFee;
	@UiField TextBox tbCoverRange;
	@UiField TextBox tbSpaceResolution;
	@UiField DateBox dtbStartDate;
	@UiField DateBox dtbEndDate;
	@UiField DateBox dtbProductDate;
	@UiField CheckBox cbNeedAssimilation;
	@UiField CheckBox cbNeedValidation;
	@UiField Button btConfirm;
	
	NormalProductFeasibilityModel originNpf;

	//共性产品生产需求修改服务（确认生产需求）
	private NormalProductModifyServiceAsync npmSvr = GWT.create(NormalProductModifyService.class);
	
	private static NormalProductFeasibilityUiBinder uiBinder = GWT
			.create(NormalProductFeasibilityUiBinder.class);

	interface NormalProductFeasibilityUiBinder extends
			UiBinder<Widget, NormalProductFeasibility> {
	}
	
	public NormalProductFeasibility() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public void showNormalProductFeasibilityInfo(NormalProductFeasibilityModel npf)
	{
		originNpf = npf;
		tbTaskId.setText(npf.getTaskID());
		tbTaskId.setEnabled(false);
		tbName.setText(npf.getNpm().getName());
		dtbProductDate.setValue(npf.getProductDate());
		tbProductCycle.setText(String.valueOf(npf.getProductCycle()));
		tbFee.setText(String.valueOf(npf.getFee()));
		tbCoverRange.setText(npf.getNpm().getCoverRange());
		tbSpaceResolution.setText(npf.getNpm().getSpaceResolution());
		dtbStartDate.setValue(npf.getNpm().getStartDate());
		dtbEndDate.setValue(npf.getNpm().getEndDate());
		cbNeedAssimilation.setValue(npf.isNeedAssimilation());
		cbNeedValidation.setValue(npf.isNeedValdation());
	}

	
	public void clearInfo()
	{
		tbTaskId.setText("");
		tbName.setText("");
		tbProductCycle.setText("");
		tbFee.setText("");
		tbCoverRange.setText("");
		tbSpaceResolution.setText("");
		cbNeedAssimilation.setValue(false);
		cbNeedValidation.setValue(false);
	}

	
	@UiHandler("btConfirm")
	void handleClick(ClickEvent e)
	{
		if(originNpf!=null)
		{
			NormalProductFeasibilityModel npf = new NormalProductFeasibilityModel();
			npf.setNpm(originNpf.getNpm());
			npf.getNpm().setName(tbName.getText());
			npf.getNpm().setCoverRange(tbCoverRange.getText());
			npf.getNpm().setStartDate(dtbStartDate.getValue());
			npf.getNpm().setEndDate(dtbEndDate.getValue());
			npf.setProductCycle(Integer.parseInt(tbProductCycle.getText()));
			npf.setFee(Float.parseFloat(tbFee.getText()));
			npf.setProductDate(dtbProductDate.getValue());
			npf.setNeedAssimilation(cbNeedAssimilation.getValue());
			npf.setNeedValdation(cbNeedValidation.getValue());
			modifyNormalProduct(npf);
		}
			
	}
	
	public void modifyNormalProduct(NormalProductFeasibilityModel npf)
	{
		if(npmSvr==null)
		{
		npmSvr = GWT.create(NormalProductModifyService.class);
		}
		
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
		
		npmSvr.modifyNormalProduct(npf, callback);
	}

	

}
