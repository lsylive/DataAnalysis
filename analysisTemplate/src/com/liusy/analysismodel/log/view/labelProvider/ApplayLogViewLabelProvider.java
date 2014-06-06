package com.liusy.analysismodel.log.view.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import com.liusy.analysismodel.log.model.log.LogOperate;
import com.liusy.analysis.template.model.util.StringUtil;

public class ApplayLogViewLabelProvider  extends LabelProvider implements
ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
		LogOperate lonInfo = (LogOperate) obj;
		switch (columnIndex) {
		case 1:
			return lonInfo.getUserAccount();
		case 2:
			return lonInfo.getUserName();
		case 3:
			return lonInfo.getOrgName();
		case 4:
			return lonInfo.getDeptName();
		case 5:
			return lonInfo.getResName()==null?"":String.valueOf(lonInfo.getResName());
		case 6:
			return lonInfo.getOptType();
		case 7:
			return lonInfo.getIpAddress();
		case 8:
			return StringUtil.timeStampToString(lonInfo.getOptTime());
		case 9:
			return lonInfo.getQueryCondi();
		case 10:
			return lonInfo.getResName();
		}
		return null;
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}

}
