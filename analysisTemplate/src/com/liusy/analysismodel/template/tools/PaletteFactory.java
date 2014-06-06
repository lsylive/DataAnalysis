package com.liusy.analysismodel.template.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysis.template.model.node.DataNode;
import com.liusy.analysis.template.model.node.DetailQueryInterfaceNode;
import com.liusy.analysis.template.model.node.EndNode;
import com.liusy.analysis.template.model.node.FilterNode;
import com.liusy.analysis.template.model.node.GenerateColumnNode;
import com.liusy.analysis.template.model.node.IntersectionNode;
import com.liusy.analysis.template.model.node.JudgeNode;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysis.template.model.node.MultiDataNode;
import com.liusy.analysis.template.model.node.NetChartInterfaceNode;
import com.liusy.analysis.template.model.node.ParametersNode;
import com.liusy.analysis.template.model.node.PathSearchNode;
import com.liusy.analysis.template.model.node.QueryInterfaceNode;
import com.liusy.analysis.template.model.node.SortNode;
import com.liusy.analysis.template.model.node.StarChartInterfaceNode;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;
import com.liusy.analysis.template.model.node.SubDiagramNode;
import com.liusy.analysis.template.model.node.SumNode;
import com.liusy.analysis.template.model.node.SyncContrastNode;
import com.liusy.analysis.template.model.node.TimeLineChartInterfaceNode;
import com.liusy.analysis.template.model.node.UnionNode;
import com.liusy.analysis.template.model.node.UnionOperationNode;
import com.liusy.analysis.template.model.util.StringUtil;

public class PaletteFactory {

	public static PaletteRoot createPalette() {
		// �½�ѡ���
		PaletteRoot paletteRoot = new PaletteRoot();

		// ���ѡ����е�ģ��ѡ��
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}

	private static String	smallImagePath	= "/com/liusy/analysismodel/template/image/png16/";
	private static String	bigImagePath	= "/com/liusy/analysismodel/template/image/png32/";
	private static String	fileExt			= ".png";

	private static List<PaletteContainer> createCategories(PaletteRoot root) {
		List<PaletteContainer> categories = new ArrayList<PaletteContainer>();

		// ��ӷ���ѡ��
		categories.add(createControlGroup(root));
		categories.add(createDataNodeDrawer());
		categories.add(createOperateNodesDrawer());
		categories.add(createResultNodesDrawer());
		return categories;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		ToolEntry tool = new SelectionToolEntry();
		tool.setLabel("ѡ��");
		entries.add(tool);
		root.setDefaultEntry(tool);

		ImageDescriptor connIcom = ImageDescriptor.createFromFile(PaletteFactory.class, smallImagePath + "conn" + fileExt);
		tool = new ConnectionCreationToolEntry(StringUtil.connectionName, "��������", null, connIcom, null);
		entries.add(tool);

		// entries.add(addEntry("��ʼ", "����һ����ʼ�ڵ�", StartNode.class, "start"));
		entries.add(addEntry("����", "����һ�������ڵ�", EndNode.class, "stop"));

		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createDataNodeDrawer() {

		// ����������ҳ
		PaletteDrawer drawer = new PaletteDrawer("���ݷ���");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("���ѯ", "����һ�����ѯ�ڵ�", DataNode.class, "PRC"));
		entries.add(addEntry("������Ӳ�ѯ", "����һ�����ӱ��ѯ�ڵ�", MultiDataNode.class, "DBJ"));
		entries.add(addEntry("�������ѯ", "����һ���������ѯ�ڵ�", ConditionQueryNode.class, "SQL"));
		entries.add(addEntry("������", "����һ���������ڵ�", ParametersNode.class, "RGE"));
		entries.add(addEntry("�Ӳ�ѯ", "����һ��ͼ�ڵ�", SubDiagramNode.class, "MBL"));

		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createOperateNodesDrawer() {
		// ����������ҳ
		PaletteDrawer drawer = new PaletteDrawer("����ת��");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("����ת��", "����һ������ת���ڵ�", IntersectionNode.class, "MRG"));
		entries.add(addEntry("�ϼ�ת��", "����һ���ϼ�ת���ڵ�", UnionNode.class, "JRW"));
		entries.add(addEntry("�ϼ�����", "����һ���ϼ�����ڵ�", UnionOperationNode.class, "SMG"));
		entries.add(addEntry("��������", "����һ�������������еĽڵ�", GenerateColumnNode.class, "GEN"));
		entries.add(addEntry("���˼�¼", "����һ���ڵ���˼�¼��", FilterNode.class, "FLT"));
		entries.add(addEntry("����", "����һ���ڵ�Լ�¼����������", SortNode.class, "SRT"));
		entries.add(addEntry("�ж�", "����һ��·���жϽڵ�", JudgeNode.class, "JDG"));
		entries.add(addEntry("����ͳ��", "����һ������ͳ������ڵ�", SumNode.class, "AGG"));
		entries.add(addEntry("·������", "����һ��·����������ڵ�", PathSearchNode.class, "MAP"));
		entries.add(addEntry("ͬ���Ա�", "����һ��ͬ���Ա�����ڵ�", SyncContrastNode.class, "BFC"));
		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createResultNodesDrawer() {
		// ����������ҳ
		PaletteDrawer drawer = new PaletteDrawer("��������");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("��ѯ����", "����һ����ѯ�������ýڵ�", QueryInterfaceNode.class, "DLU"));
		entries.add(addEntry("��ϸ��Ϣ����", "����һ����ϸ��Ϣ�������ýڵ�", DetailQueryInterfaceNode.class, "WTL"));
		entries.add(addEntry("����ͼ����", "����һ������ͼ�������ýڵ�", LineChartInterfaceNode.class, "UPD"));
		entries.add(addEntry("ͳ������", "����һ��ͳ�ƽ������ýڵ�", StatisticInterfaceNode.class, "GRP"));
		entries.add(addEntry("����ͼ����", "����һ������ͼ�������ýڵ�", StarChartInterfaceNode.class, "star"));
		entries.add(addEntry("��״ͼ����", "����һ����״ͼ�������ýڵ�", NetChartInterfaceNode.class, "PFP"));
		entries.add(addEntry("ʱ��켣ͼ����", "����һ��ʱ��켣ͼ�������ýڵ�", TimeLineChartInterfaceNode.class, "LIP"));

		drawer.addAll(entries);
		return drawer;
	}

	private static ToolEntry addEntry(String title, String toolTip, Class nodeClass, String imageName) {
		ImageDescriptor smallImage = ImageDescriptor.createFromFile(PaletteFactory.class, smallImagePath + imageName + fileExt);
		ImageDescriptor bigImage = ImageDescriptor.createFromFile(PaletteFactory.class, bigImagePath + imageName + fileExt);
		ToolEntry te = new CombinedTemplateCreationEntry(title, toolTip, nodeClass, new SimpleFactory(nodeClass), smallImage, bigImage);
		return te;
	}
}