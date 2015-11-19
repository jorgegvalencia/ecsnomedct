package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import db.DBConnector;

public class Dumper {
	private static final String NORM = "jdbc:mysql://localhost/norm";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	public static void main(String[] args) {
		writeExcel();
	}
	
	public static void writeExcel(){
		DBConnector db = new DBConnector(NORM, USER, PASS);
		String frec = "SELECT "
				+ "concept.name AS CONCEPT, "
				+ "concept.cui AS CUI, "
				+ "ec_concepts.concept_sctid AS SCTID, "
				+ "COUNT(ec_concepts.eligibility_criteria_id) AS FRECUENCY, "
				+ "concept.semantic_type AS TYPE "
				+ "FROM ec_concepts, concept "
				+ "WHERE  "
				+ "concept.sctid = ec_concepts.concept_sctid  "
				+ "GROUP BY concept_sctid "
				+ "ORDER BY FRECUENCY DESC "
				+ "LIMIT 0,100";
		
		FileOutputStream out = null;
		ResultSet rs = null;
		try {
			out = new FileOutputStream("results.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(); // create a new workbook
			XSSFSheet sheet = wb.createSheet(); // create a new sheet
			XSSFRow row = null; // declare a row object reference
			XSSFCell cell = null; // declare a cell object reference
			
			CreationHelper createHelper = wb.getCreationHelper();
			Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
		    Font hlink_font = wb.createFont();
			link.setAddress("'Main'!A1");
			
			// Styles
			Font f = wb.createFont();
			f.setBold(true);
			
			XSSFCellStyle headers = wb.createCellStyle();
			headers.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
			headers.setFillPattern(CellStyle.SOLID_FOREGROUND);
			headers.setFont(f);
			
			XSSFCellStyle hlink_style = wb.createCellStyle();
		    hlink_font.setUnderline(Font.U_SINGLE);
		    hlink_font.setColor(IndexedColors.BLUE.getIndex());
		    hlink_style.setFont(hlink_font);
			
			// Main sheet
			wb.setSheetName(0, "Main");
			rs = db.performQuery(frec);
			if(rs!=null){
				row = sheet.createRow(0);
				
				cell = row.createCell(0);
				cell.setCellStyle(headers);
				cell.setCellValue("CONCEPT");
				
				cell = row.createCell(1);
				cell.setCellStyle(headers);
				cell.setCellValue("CUI");
				
				cell = row.createCell(2);
				cell.setCellStyle(headers);
				cell.setCellValue("SCTID");
				
				cell = row.createCell(3);
				cell.setCellStyle(headers);
				cell.setCellValue("FRECUENCY");
				
				cell = row.createCell(4);
				cell.setCellStyle(headers);
				cell.setCellValue("TYPE");
				
				int rownum = 1;
				while(rs.next()){
					row = sheet.createRow(rownum);
					
					XSSFCell hyper;
					String concept = rs.getString("CONCEPT");
					String cui = rs.getString("CUI");
					String sctid = rs.getString("SCTID");
					int frecuency = rs.getInt("FRECUENCY");
					String type = rs.getString("TYPE");
					
					cell = row.createCell(0); cell.setCellValue(concept);
					hyper = cell;
					cell = row.createCell(1); cell.setCellValue(cui);
					cell = row.createCell(2); cell.setCellValue(sctid);
					cell = row.createCell(3); cell.setCellValue(frecuency);
					cell = row.createCell(4); cell.setCellValue(type);
					
					// Phrases sheets
					DBConnector db2 =  new DBConnector(NORM, USER, PASS);
					ResultSet rs2 = getPhrasesForConcept(sctid,db2);
					if(rs2!=null){
						XSSFSheet sheet2 = wb.createSheet(); // create a new sheet
						wb.setSheetName(rownum,  ""+rownum);
						row = sheet2.createRow(0);
						
						cell = row.createCell(0);
						cell.setCellValue("Return to main");
						cell.setCellStyle(hlink_style);
						cell.setHyperlink(link);
						
						row = sheet2.createRow(1);
						
						cell = row.createCell(0);
						cell.setCellStyle(headers);
						cell.setCellValue("PHRASE");
						
						cell = row.createCell(1);
						cell.setCellStyle(headers);
						cell.setCellValue(concept);
						
						int rownum2 = 2;
						while(rs2.next()){
							row = sheet2.createRow(rownum2);
							
							String phrase = rs2.getString("PHRASE");
							
							cell = row.createCell(0); cell.setCellValue(phrase);
							rownum2++;
						}
						sheet2.setColumnWidth(0,56000);
						sheet2.autoSizeColumn(1);
					}
					Hyperlink link2 = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
					link2.setAddress("'"+rownum+"'!A1");
					hyper.setCellStyle(hlink_style);
					hyper.setHyperlink(link2);
					
					db2.endConnector();
					rownum++;
					sheet.autoSizeColumn(0);
					sheet.autoSizeColumn(1);
					sheet.autoSizeColumn(2);
					sheet.autoSizeColumn(3);
					sheet.autoSizeColumn(4);
				}
			}
			wb.write(out);
			wb.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endConnector();
		}
	}
	
	private static ResultSet getPhrasesForConcept(String sctid, DBConnector db){
		String ecs_concept = "SELECT DISTINCT " //-- , concept.name AS TERM, concept.sctid AS SCTID -- , utterance AS UTT -- ,concept.sctid AS SCTID, name AS TERM 
				+ "phrase AS PHRASE "
				+ "FROM ec_concepts, eligibility_criteria, concept "
				+ "WHERE "
				+ "ec_concepts.eligibility_criteria_id = eligibility_criteria.id AND "
				+ "ec_concepts.clinical_trial_id = eligibility_criteria.clinical_trial_id AND "
				+ "ec_concepts.concept_sctid = concept.sctid  AND concept.sctid = \""+sctid+"\" AND NOT "
				+ "eligibility_criteria.inc_exc = 0 "
				+ "LIMIT 0,50;";
		ResultSet rs = null;
		if(db != null){
			rs = db.performQuery(ecs_concept);
		}
		return rs;
	}

	public void test(){
			/*XSSFWorkbook workbook;
			try {
				workbook = new XSSFWorkbook(new FileInputStream("frecuency_7835.xlsx"));
				int sheetIndex = 0;
				XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
				for (Row tempRow : sheet) {
					// print out the first three columns
					for (int column = 0; column < 5; column++) {
						Cell tempCell = tempRow.getCell(column);
						if (tempCell.getCellType() == Cell.CELL_TYPE_STRING) {
							System.out.print(tempCell.getStringCellValue() + "  ");
						}
					}
					System.out.println();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
	
			// create a new file
			FileOutputStream out = null;
			try {
				out = new FileOutputStream("workbook.xlsx");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// create a new workbook
			Workbook wb = new XSSFWorkbook();
			// create a new sheet
			Sheet s = wb.createSheet();
			// declare a row object reference
			Row r = null;
			// declare a cell object reference
			Cell c = null;
			
			/*
			 ********************************************************** FORMATING
			 */
	
			// create 3 cell styles
			CellStyle cs = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			CellStyle cs3 = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
	
			// create 2 fonts objects
			Font f = wb.createFont();
			Font f2 = wb.createFont();
	
			//set font 1 to 12 point type
			f.setFontHeightInPoints((short) 12);
			//make it blue
			f.setColor((short)HSSFColor.ORANGE.index);
			// make it bold
			//arial is the default font
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	
			//set font 2 to 10 point type
			f2.setFontHeightInPoints((short) 10);
			//make it red
			f2.setColor( (short)Font.COLOR_RED );
			//make it bold
			f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			
			XSSFWorkbook wb2 = new XSSFWorkbook();
			XSSFSheet sheet = wb2.createSheet();
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell( 0);
			cell.setCellValue("custom XSSF colors");
	
			XSSFCellStyle style1 = wb2.createCellStyle();
			style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
			    style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
	
			f2.setStrikeout( true );
	
			//set cell stlye
			cs.setFont(f);
			//set the cell format 
			cs.setDataFormat(df.getFormat("#,##0.0"));
	
			//set a thin border
			cs2.setBorderBottom(CellStyle.BORDER_THIN);
			//fill w fg fill color
			cs2.setFillPattern((short) CellStyle.SOLID_FOREGROUND);
			//set the cell format to text see DataFormat for a full list
			//cs2.setDataFormat(XSSFDataFormat.getBuiltinFormat("text"));
	
			// set the font
			cs2.setFont(f2);
			
			/*
			 ********************************************************** FORMATING
			 */
	
			// in case of plain ascii
			wb.setSheetName(0, "XSSF Test");
			// create a sheet with 30 rows (0-29)
			int rownum;
			for (rownum = (short) 0; rownum < 30; rownum++){
				// create a row
				r = s.createRow(rownum);
				// on every other row
	/*			if ((rownum+1 % 2) == 0){
					// make the row height bigger  (in twips - 1/20 of a point)
					r.setHeight((short) 0x249);
				}*/
				//r.setRowNum(( short ) rownum);
				// create 10 cells (0-9) (the += 2 becomes apparent later
				for (short cellnum = (short) 0; cellnum < 10; cellnum += 2){
					// create a numeric cell
					c = r.createCell(cellnum);
					// do some goofy math to demonstrate decimals
					c.setCellValue(rownum * 10000 + cellnum
							+ (((double) rownum / 1000)
									+ ((double) cellnum / 10000)));
	
					String cellValue;
	
					// create a string cell (see why += 2 in the
					c = r.createCell((short) (cellnum + 1));
	
					// on every other row
					if ((rownum % 2) == 0){
						// set this cell to the first cell style we defined
						c.setCellStyle(cs);
						// set the cell's string value to "Test"
						c.setCellValue( "Test" );
					}
					else{
						c.setCellStyle(cs2);
						// set the cell's string value to "\u0422\u0435\u0441\u0442"
						c.setCellValue( "\u0422\u0435\u0441\u0442" );
					}
	
					// make this column a bit wider
					s.setColumnWidth((short) (cellnum + 1), (short) ((50 * 8) / ((double) 1 / 20)));
				}
			}
	
			//draw a thick black border on the row at the bottom using BLANKS
			// advance 2 rows
			rownum++;
			rownum++;
	
			r = s.createRow(rownum);
	
			// define the third style to be the default
			// except with a thick black border at the bottom
			cs3.setBorderBottom(CellStyle.BORDER_THICK);
	
			//create 50 cells
			for (short cellnum = (short) 0; cellnum < 50; cellnum++){
				//create a blank type cell (no value)
				c = r.createCell(cellnum);
				// set it to the thick black border style
				c.setCellStyle(cs3);
			}
			//end draw thick black border
	
			// demonstrate adding/naming and deleting a sheet
			// create a sheet, set its title then delete it
			s = wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			//wb.removeSheetAt(1);
			//end deleted sheet
	
			// write the workbook to the output stream
			// close our file (don't blow out our file handles
			try {
				wb.write(out);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
