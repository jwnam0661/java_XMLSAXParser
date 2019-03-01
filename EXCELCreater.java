import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EXCELCreater {

	public EXCELCreater(XMLSAXParser parser) {
		
		// Workbook
		HSSFWorkbook xlsWB = new HSSFWorkbook(); // Excel 2007 以下
		// Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 以上
		
		// *** Sheet-------------------------------------------------
		// Sheet
		HSSFSheet sheet1 = xlsWB.createSheet();
		xlsWB.setSheetName(0, parser.systemName, HSSFWorkbook.ENCODING_UTF_16);
		
		// Font
		HSSFFont font = xlsWB.createFont();
		font.setFontName("ＭＳ Ｐゴシック");
		
		// カラム width
		sheet1.setColumnWidth((short)0, (short)5000);
		sheet1.setColumnWidth((short)1, (short)5000);
		sheet1.setColumnWidth((short)2, (short)10000);
		sheet1.setColumnWidth((short)3, (short)3000);
		
		// *** Style--------------------------------------------------
		// Cell
		HSSFCellStyle cellStyle = xlsWB.createCellStyle();
		
		// line変換
//		cellStyle.setWrapText(true);
		
		// Cell 색깔, 무늬 채우기
		// cellStyle.setFillForegroundColor(HSSFColor.LIME.index);
		// cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
        
        //------------------------------------------------------------
        HSSFRow row = null;
        HSSFCell cell = null;
		
		for (int i=0; i<parser.list.size(); i++) {
			
			row = sheet1.createRow(i+1);
			
			cell = row.createCell((short)0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(parser.list.get(i).systemName);
			
			cell = row.createCell((short)1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(parser.list.get(i).categoryName);
			
			cell = row.createCell((short)2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(parser.list.get(i).btnName);
			
			cell = row.createCell((short)3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(parser.list.get(i).training);
			
		}
        
		try {
			File xlsFile = new File("C:\\example\\File\\test.xls");
			FileOutputStream fileout = new FileOutputStream(xlsFile);
			xlsWB.write(fileout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
