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
		// 新建选项板
		PaletteRoot paletteRoot = new PaletteRoot();

		// 添加选项板中的模型选项
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}

	private static String	smallImagePath	= "/com/liusy/analysismodel/template/image/png16/";
	private static String	bigImagePath	= "/com/liusy/analysismodel/template/image/png32/";
	private static String	fileExt			= ".png";

	private static List<PaletteContainer> createCategories(PaletteRoot root) {
		List<PaletteContainer> categories = new ArrayList<PaletteContainer>();

		// 添加分组选项
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
		tool.setLabel("选择");
		entries.add(tool);
		root.setDefaultEntry(tool);

		ImageDescriptor connIcom = ImageDescriptor.createFromFile(PaletteFactory.class, smallImagePath + "conn" + fileExt);
		tool = new ConnectionCreationToolEntry(StringUtil.connectionName, "生成链接", null, connIcom, null);
		entries.add(tool);

		// entries.add(addEntry("开始", "生成一个开始节点", StartNode.class, "start"));
		entries.add(addEntry("结束", "生成一个结束节点", EndNode.class, "stop"));

		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createDataNodeDrawer() {

		// 添加组件分组页
		PaletteDrawer drawer = new PaletteDrawer("数据访问");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("表查询", "生成一个表查询节点", DataNode.class, "PRC"));
		entries.add(addEntry("多表联接查询", "生成一个联接表查询节点", MultiDataNode.class, "DBJ"));
		entries.add(addEntry("条件表查询", "生成一个条件表查询节点", ConditionQueryNode.class, "SQL"));
		entries.add(addEntry("参数表", "生成一个多参数表节点", ParametersNode.class, "RGE"));
		entries.add(addEntry("子查询", "生成一个图节点", SubDiagramNode.class, "MBL"));

		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createOperateNodesDrawer() {
		// 添加组件分组页
		PaletteDrawer drawer = new PaletteDrawer("数据转换");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("交集转换", "生成一个交集转换节点", IntersectionNode.class, "MRG"));
		entries.add(addEntry("合集转换", "生成一个合集转换节点", UnionNode.class, "JRW"));
		entries.add(addEntry("合集运算", "生成一个合集运算节点", UnionOperationNode.class, "SMG"));
		entries.add(addEntry("生成新列", "生成一个增加新数据列的节点", GenerateColumnNode.class, "GEN"));
		entries.add(addEntry("过滤记录", "生成一个节点过滤记录集", FilterNode.class, "FLT"));
		entries.add(addEntry("排序", "生成一个节点对记录集进行排序", SortNode.class, "SRT"));
		entries.add(addEntry("判断", "生成一个路径判断节点", JudgeNode.class, "JDG"));
		entries.add(addEntry("分组统计", "生成一个分组统计运算节点", SumNode.class, "AGG"));
		entries.add(addEntry("路径搜索", "生成一个路径搜索运算节点", PathSearchNode.class, "MAP"));
		entries.add(addEntry("同步对比", "生成一个同步对比运算节点", SyncContrastNode.class, "BFC"));
		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createResultNodesDrawer() {
		// 添加组件分组页
		PaletteDrawer drawer = new PaletteDrawer("界面配置");
		List<ToolEntry> entries = new ArrayList<ToolEntry>();

		entries.add(addEntry("查询配置", "生成一个查询界面配置节点", QueryInterfaceNode.class, "DLU"));
		entries.add(addEntry("详细信息配置", "生成一个详细信息界面配置节点", DetailQueryInterfaceNode.class, "WTL"));
		entries.add(addEntry("线型图配置", "生成一个线型图界面配置节点", LineChartInterfaceNode.class, "UPD"));
		entries.add(addEntry("统计配置", "生成一个统计界面配置节点", StatisticInterfaceNode.class, "GRP"));
		entries.add(addEntry("星形图配置", "生成一个星形图界面配置节点", StarChartInterfaceNode.class, "star"));
		entries.add(addEntry("网状图配置", "生成一个网状图界面配置节点", NetChartInterfaceNode.class, "PFP"));
		entries.add(addEntry("时序轨迹图配置", "生成一个时序轨迹图界面配置节点", TimeLineChartInterfaceNode.class, "LIP"));

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