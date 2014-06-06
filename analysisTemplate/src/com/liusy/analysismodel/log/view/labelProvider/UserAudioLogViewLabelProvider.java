package com.liusy.analysismodel.log.view.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysis.template.model.util.StringUtil;

public class UserAudioLogViewLabelProvider extends LabelProvider implements
ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
		ImportLog2 lonInfo = (ImportLog2) obj;
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
		case 9:
			return lonInfo.getResName();
		case 10:
			return lonInfo.getOptType();
		case 11:
			return StringUtil.timeStampToString(lonInfo.getOptTime());
		case 12:
			return lonInfo.getQueryCondi();
		case 13:
			return lonInfo.getOriginal();
		}
		return null;
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}


}
