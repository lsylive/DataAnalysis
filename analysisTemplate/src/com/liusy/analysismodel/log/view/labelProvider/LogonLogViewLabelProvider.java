package com.liusy.analysismodel.log.view.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysis.template.model.util.StringUtil;

public class LogonLogViewLabelProvider  extends LabelProvider implements
ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
//		return getText(obj);
		LogonLog lonInfo = (LogonLog) obj;
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
			return StringUtil.dateToString(lonInfo.getLogonTime());
		case 6:
			return lonInfo.getIpAddress();
		case 7:
			return StringUtil.dateToString(lonInfo.getLogonOutTime());
		case 8:
			return lonInfo.getResult();
		}
		return null;
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}

}
