package common_method;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class get_data {
	public static ArrayList<String> getdataexcel(String testsheetname ,String testcasename) throws IOException
	{
		ArrayList<String> arrayData =new ArrayList<String>();
	
		// Step 1 locate excel data file by creating the object fileinputstream
		FileInputStream fis = new FileInputStream("C:\\Users\\WELCOME\\Documents\\testdata1.xlsx");
		
		//Step 2 create the object of XSSFWORKBOOK to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//step 3 access the desired sheet
		//step 3.1 fetch the count of sheets available in the excel file
		int countofsheet = workbook.getNumberOfSheets();
		
		//step 3.2 fetch the name of sheet and compare against desired sheet name
		for(int i=0; i<countofsheet;i++)
		{
			String Sheetname =workbook.getSheetName(i);
		if (Sheetname.equalsIgnoreCase(testsheetname))
		{
			//Step 4 Access the sheet and iterate through rows to fetch the column in which test case name column is found
			XSSFSheet sheet = workbook.getSheetAt(i);
			// Step 4 Create 
			Iterator<Row> Rows = sheet.iterator();
			Row firstrow = Rows.next();
			// Step 4.2 Create iterator for cells
			Iterator<Cell> Cells = firstrow.cellIterator();
			int j=0;
			int tc_column =0;
			
			//Step 4.3 Read the cell values of row no.1 to compare against the test case name
			while(Cells.hasNext())
			{
				Cell Cellvalue = Cells.next();
				if (Cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
				{
					tc_column= j;
				}
				j++;
					
			}
			//Step 5 fetch the data for designed test case
			while(Rows.hasNext())
			{
				Row dataRow = Rows.next();
				if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testcasename))
				{
					Iterator<Cell> dataCellValue = dataRow.cellIterator();
					while(dataCellValue.hasNext())
					{
						Cell dataofcell = dataCellValue.next();
						// to extract cell value by using try and catch method
						/*try
						{
						String testdata1 = dataofcell.getStringCellValue();
						arrayData.add(testdata1);
						
					}
						catch (IllegalStateException e)
						{
							int inttestData = (int) dataofcell.getNumericCellValue();
							String Stringtestdata1 =Integer.toString(inttestData);
							arrayData.add(Stringtestdata1);
						}*/
						//Method 2-- To extract the cell value by using if and else
						CellType datatype = dataofcell.getCellType();
						
						if (datatype.toString() == "NUMERIC")
						{
							int inttestdata = (int) dataofcell.getNumericCellValue();
							String Stringtestdata1 = Integer.toString(inttestdata);
							arrayData.add(Stringtestdata1);
						}
						else if (datatype.toString() == "STRING")
						{
							String testdata1 =dataofcell.getStringCellValue();
							arrayData.add(testdata1);
						}
						//Method 3 -- Extract the data by converting it into string
						
						//String testdata1 = dataCellValue.next().toString().replaceAll("\\.\\d+$","");
						//System.out.println(testdata1);
						
						//Method 4 - Extract the data by using dataformatter
						
					//	DataFormatter format = new DataFormatter();
						//String testdata11 = format.formatCellValue(dataofcell);
						//arrayData.add(testdata11);
						//System.out.println(testdata11);
						
				}
			}
			
					
		}
		}
	}
		return arrayData;
}
}

