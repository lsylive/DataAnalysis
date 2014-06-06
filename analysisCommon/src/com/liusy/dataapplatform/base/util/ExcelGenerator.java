


//   ExcelGenerator.java

package com.liusy.dataapplatform.base.util;

import com.liusy.core.util.Const;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			ExcelSheetColProp

public class ExcelGenerator
{

	private static Log log = LogFactory.getLog(ExcelGenerator.class);

	public ExcelGenerator()
	{
	}

	public static void ReadExcelFile(String filename, ExcelSheetColProp prop)
		throws Exception
	{
		InputStream myxls = new FileInputStream(filename);
		try
		{
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);
			List columnValueList = new ArrayList();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			int pos = 0;
			for (Iterator rit = sheet.rowIterator(); rit.hasNext();)
				if (++pos < prop.getStartRow())
				{
					rit.next();
				} else
				{
					Map listMap = new HashMap();
					HSSFRow row = (HSSFRow)rit.next();
					int j = 0;
					for (int i = prop.getStartCol() - 1; i < prop.getColumnName().length; i++)
					{
						HSSFCell cell = row.getCell((short)i);
						String strCell = "";
						if (HSSFDateUtil.isCellDateFormatted(cell))
						{
							double d = cell.getNumericCellValue();
							java.util.Date date = HSSFDateUtil.getJavaDate(d);
							strCell = format.format(date);
						} else
						if (cell != null)
							switch (cell.getCellType())
							{
							case 0: // '\0'
								strCell = String.valueOf(cell.getNumericCellValue());
								break;

							case 1: // '\001'
								strCell = cell.getStringCellValue();
								break;

							case 4: // '\004'
								strCell = String.valueOf(cell.getBooleanCellValue());
								break;

							case 2: // '\002'
							case 3: // '\003'
							default:
								strCell = "";
								break;
							}
						listMap.put(prop.getHeaderName()[i], strCell);
						j++;
					}

					columnValueList.add(listMap);
				}

			prop.setColumnList(columnValueList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error(e);
			throw new Exception("�����ļ�ʧ��!");
		}
	}

	public static void ReadExcelFile(InputStream myxls, ExcelSheetColProp prop)
		throws Exception
	{
		HSSFWorkbook wb = new HSSFWorkbook(myxls);
		HSSFSheet sheet = wb.getSheetAt(0);
		List columnValueList = new ArrayList();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int pos = 0;
		for (Iterator rit = sheet.rowIterator(); rit.hasNext();)
			if (++pos < prop.getStartRow())
			{
				rit.next();
			} else
			{
				Map listMap = new HashMap();
				HSSFRow row = (HSSFRow)rit.next();
				int j = 0;
				int endpos = (prop.getStartCol() + prop.getColumnName().length) - 1;
				boolean ishasrecord = false;
				for (int i = prop.getStartCol() - 1; i < endpos; i++)
				{
					String type = prop.getColumnType()[j];
					HSSFCell cell = row.getCell((short)i);
					String strCell = "";
					if (cell != null)
						switch (cell.getCellType())
						{
						case 0: // '\0'
							double d;
							if (HSSFDateUtil.isCellDateFormatted(cell) || type.equals(String.valueOf(Const.FIELD_TYPE_DATE)))
							{
								d = cell.getNumericCellValue();
								java.util.Date date = HSSFDateUtil.getJavaDate(d);
								strCell = format.format(date);
								break;
							}
							if (type.equals(String.valueOf(Const.FIELD_TYPE_INTEGER)) || type.equals(String.valueOf(Const.FIELD_TYPE_INT)) || type.equals(String.valueOf(Const.FIELD_TYPE_DOUBLE)))
							{
								strCell = String.valueOf(cell.getNumericCellValue());
								break;
							}
							if (!type.equals(String.valueOf(Const.FIELD_TYPE_STRING)))
								break;
							d = cell.getNumericCellValue();
							String str1 = String.valueOf(Double.valueOf(d).intValue());
							if (str1 != null && !"".equals(str1.trim()))
								strCell = String.valueOf(Double.valueOf(d).intValue());
							break;

						case 1: // '\001'
							strCell = cell.getStringCellValue();
							break;

						case 4: // '\004'
							strCell = String.valueOf(cell.getBooleanCellValue());
							break;

						case 2: // '\002'
						case 3: // '\003'
						default:
							strCell = "";
							break;
						}
					if (strCell != null && !"".equals(strCell.trim()))
						ishasrecord = true;
					listMap.put(prop.getColumnName()[j], strCell);
					j++;
				}

				if (ishasrecord)
					columnValueList.add(listMap);
			}

		prop.setColumnList(columnValueList);
	}

	public static int ReadExcelFile(InputStream myxls, Map columnMap, String startRowStr, String startColStr, String endRowStr, String endColStr, ExcelSheetColProp prop)
	{
		int pos = 0;
		List columnValueList = new ArrayList();
		try
		{
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			List columnList = new ArrayList();
			List collist = new ArrayList();
			int startCol = Integer.parseInt(startColStr);
			int endCol = Integer.parseInt(endColStr);
			int startRow = Integer.parseInt(startRowStr);
			int endRow = Integer.parseInt(endRowStr);
			Iterator rit = sheet.rowIterator();
			while (rit.hasNext()) 
			{
				pos++;
				HSSFRow row = (HSSFRow)rit.next();
				if (pos == startRow)
				{
					for (int i = startCol - 1; i < endCol; i++)
					{
						HSSFCell cell = row.getCell((short)i);
						String cellName = cell.getStringCellValue();
						ColumnPoolObj column = (ColumnPoolObj)columnMap.get(cellName.toUpperCase());
						if (column == null)
							column = (ColumnPoolObj)columnMap.get(cellName.toLowerCase());
						if (column != null)
						{
							collist.add(Integer.valueOf(i));
							columnList.add(column);
						}
					}

					continue;
				}
				if (pos < startRow)
					continue;
				if (pos > endRow)
					break;
				Map listMap = new HashMap();
				boolean ishasrecord = false;
				for (int j = 0; j < collist.size(); j++)
				{
					int colpos = ((Integer)collist.get(j)).intValue();
					ColumnPoolObj column = (ColumnPoolObj)columnList.get(j);
					String type = column.getDataType();
					HSSFCell cell = row.getCell((short)colpos);
					String strCell = "";
					if (cell != null)
						switch (cell.getCellType())
						{
						case 0: // '\0'
							double d;
							if (HSSFDateUtil.isCellDateFormatted(cell) || type.equals(String.valueOf(Const.FIELD_TYPE_DATE)))
							{
								d = cell.getNumericCellValue();
								java.util.Date date = HSSFDateUtil.getJavaDate(d);
								strCell = format.format(date);
								break;
							}
							if (type.equals(Const.META_TYPE_NUMERIC))
							{
								strCell = String.valueOf(cell.getNumericCellValue());
								break;
							}
							if (!type.equals(Const.META_TYPE_STRING))
								break;
							d = cell.getNumericCellValue();
							String str1 = String.valueOf(Double.valueOf(d).intValue());
							if (str1 != null && !"".equals(str1.trim()))
								strCell = String.valueOf(Double.valueOf(d).intValue());
							break;

						case 1: // '\001'
							if (type.equals(Const.META_TYPE_NUMERIC))
							{
								strCell = cell.getStringCellValue();
							d = Double.valueOf(strCell).doubleValue();
								break;
							}
							if (type.equals(Const.META_TYPE_DATE))
							{
							 d = cell.getNumericCellValue();
								java.util.Date date = HSSFDateUtil.getJavaDate(d);
								break;
							}
							if (type.equals(Const.META_TYPE_STRING))
								strCell = cell.getStringCellValue();
							break;

						case 4: // '\004'
							strCell = String.valueOf(cell.getBooleanCellValue());
							break;

						case 2: // '\002'
						case 3: // '\003'
						default:
							strCell = "";
							break;
						}
					if (strCell != null && !"".equals(strCell.trim()))
						ishasrecord = true;
					listMap.put(column.getName(), strCell);
				}

				if (ishasrecord)
					columnValueList.add(listMap);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error(e);
		}
		prop.setColumnList(columnValueList);
		return prop.getColumnList().size();
	}

	public int getAvaiableRows(InputStream myxls, int startRow)
		throws Exception
	{
		HSSFWorkbook wb = new HSSFWorkbook(myxls);
		HSSFSheet sheet = wb.getSheetAt(0);
		int num = sheet.getLastRowNum();
		return num - startRow;
	}

	public static HSSFWorkbook GenerateExcelFile(ExcelSheetColProp prop, String suffix)
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		String sheetname = prop.getSheetName();
		if (suffix != null && suffix.length() > 0)
			sheetname = (new StringBuilder(String.valueOf(sheetname))).append("_").append(suffix).toString();
		HSSFSheet sheet = wb.createSheet(sheetname);
		String tempColNames[] = prop.getColumnName();
		if (tempColNames == null || tempColNames.length == 0)
		{
			List columnList = prop.getColumnList();
			if (columnList != null && columnList.size() > 0)
			{
				Map tempMap = (Map)columnList.get(0);
				if (tempMap != null && !tempMap.isEmpty())
				{
					Object temps[] = tempMap.keySet().toArray();
					String temp[] = new String[temps.length];
					System.arraycopy(((Object) (temps)), 0, temp, 0, temp.length);
					prop.setColumnName(temp);
				}
			}
		}
		GenerateHeader(sheet, wb, prop);
		FillColumns(wb, sheet, prop);
		return wb;
	}

	public static HSSFWorkbook GenerateExcelFile()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;
	}

	public static void addSheet(HSSFWorkbook wb, ExcelSheetColProp prop)
	{
		int sheetCount = wb.getNumberOfSheets();
		HSSFSheet sheet = wb.createSheet();
		String sheetName = prop.getSheetName();
		if (sheetName.length() > 30)
			sheetName = sheetName.substring(0, 30);
		wb.setSheetName(sheetCount, sheetName, (short)1);
		if (prop.getHeaderName() != null && prop.getHeaderName().length > 0)
		{
			HSSFRow cnHeader = sheet.createRow(prop.getStartRow());
			for (int i = 0; i < prop.getHeaderName().length; i++)
				createCell(wb, cnHeader, (short)i, (short)2, prop.getHeaderName()[i]);

		}
		HSSFRow enHeader = sheet.createRow(prop.getStartRow() + 1);
		for (int i = 0; i < prop.getColumnName().length; i++)
			createCell(wb, enHeader, (short)i, (short)2, prop.getColumnName()[i]);

		List resultSet = prop.getColumnList();
		if (resultSet != null && !resultSet.isEmpty())
		{
			for (int i = 0; i < resultSet.size(); i++)
			{
				Map map = (Map)resultSet.get(i);
				HSSFRow row = sheet.createRow(prop.getStartRow() + 2 + i);
				for (int j = 0; j < prop.getColumnName().length; j++)
				{
					String value = (String)map.get(prop.getColumnName()[j]);
					if (value == null)
						value = "";
					createCell(wb, row, (short)j, (short)0, value);
				}

			}

		}
	}

	private static void GenerateHeader(HSSFSheet targetsheet, HSSFWorkbook wb, ExcelSheetColProp prop)
	{
		HSSFRow hrow = targetsheet.createRow(0);
		if (prop.getHeaderName() != null && prop.getHeaderName().length > 0)
		{
			for (int i = 0; i < prop.getHeaderName().length; i++)
			{
				String values = prop.getHeaderName()[i];
				if (values != null && !"".equals(values))
				{
					HSSFCell cel = hrow.createCell((short)i);
					createCell(wb, hrow, (short)i, (short)0, values);
				}
			}

		}
		HSSFRow row = targetsheet.createRow(1);
		if (prop.getColumnName().length > 0)
		{
			for (int i = 0; i < prop.getColumnName().length; i++)
			{
				String values = prop.getColumnName()[i];
				if (values != null && !"".equals(values))
				{
					HSSFCell cel = row.createCell((short)i);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setAlignment((short)2);
					cel.setCellValue(values);
					cel.setCellStyle(cellStyle);
				}
			}

		}
	}

	private static void FillColumns(HSSFWorkbook wb, HSSFSheet targetsheet, ExcelSheetColProp prop)
	{
		if (prop.getColumnList().size() != 0)
		{
			int i = 0;
			for (Iterator it = prop.getColumnList().iterator(); it.hasNext();)
			{
				Map map = (HashMap)it.next();
				HSSFRow row1 = targetsheet.createRow((short)i + 2);
				for (int j = 0; j < prop.getColumnName().length; j++)
				{
					String columname = prop.getColumnName()[j];
					if (columname != null && !"".equals(columname))
					{
						Object valueobj = map.get(columname.toUpperCase());
						if (valueobj == null)
							valueobj = map.get(columname.toLowerCase());
						String value = "";
						if (valueobj != null)
							value = valueobj.toString();
						if (columname != null && !"".equals(columname))
							createCell(wb, row1, (short)j, (short)2, value);
					}
				}

				i++;
			}

		}
	}

	private static void createCell(HSSFWorkbook wb, HSSFRow row, short column, short align, String objvalue)
	{
		HSSFCell cell = row.createCell(column);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(align);
		cell.setCellStyle(cellStyle);
		cell.setEncoding((short)1);
		String value = "";
		if (objvalue != null)
			value = objvalue.toString();
		cell.setCellValue(value);
	}

	private static void createCell(HSSFWorkbook wb, HSSFRow row, short column, short align, double value)
	{
		HSSFCell cell = row.createCell(column);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(align);
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.0"));
		cellStyle.setAlignment(align);
		cell.setCellStyle(cellStyle);
		cell.setEncoding((short)1);
		cell.setCellValue(value);
	}

	private static void createCellDate(HSSFWorkbook wb, HSSFRow row, short column, short align, String value)
	{
		HSSFCell cell = row.createCell(column);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(align);
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd hh:mm:ss"));
		cellStyle.setAlignment(align);
		cell.setCellStyle(cellStyle);
		cell.setEncoding((short)1);
		cell.setCellValue(value);
	}

	private static int[] getExcelPosition(String startPos, String endPos)
	{
		int startPosLen = startPos.length();
		int endPosLen = endPos.length();
		int startRow = 0;
		int startCol = 0;
		int endRow = 0;
		int endCol = 0;
		for (int i = 0; i < startPosLen; i++)
		{
			if (isChar(startPos.charAt(i)))
			{
				startCol = startRow * 26 + getDigintalByChar(startPos.toUpperCase().charAt(i));
				continue;
			}
			startRow = Integer.parseInt(startPos.substring(i, startPos.length()));
			break;
		}

		for (int j = 0; j < endPosLen; j++)
		{
			if (isChar(endPos.charAt(j)))
			{
				endCol = endCol * 26 + getDigintalByChar(endPos.toUpperCase().charAt(j));
				continue;
			}
			endRow = Integer.parseInt(endPos.substring(j, endPos.length()));
			break;
		}

		return (new int[] {
			startCol, endCol, startRow, endRow
		});
	}

	private static boolean isChar(char str)
	{
		return Pattern.matches("[A-Z]", String.valueOf(str));
	}

	public static boolean isValidExcelInput(String str)
	{
		return Pattern.matches("[A-Z]+[0-9]+", str);
	}

	private static int getDigintalByChar(char str)
	{
		int startChar = 65;
		return (str - startChar) + 1;
	}

}
