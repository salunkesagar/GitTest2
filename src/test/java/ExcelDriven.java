import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {
	
	public ArrayList<String> getData(String testcase,String sheetname) throws IOException
	{
		
		ArrayList<String> a = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Sagar Salunke\\Documents\\DemoData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetname));
			{
			XSSFSheet sheet = workbook.getSheetAt(i);
			Iterator<Row> rows = sheet.rowIterator();
			Row firstrow = rows.next();
			Iterator<Cell>ce = firstrow.cellIterator();
			int k=0;
			int column = 0;
			while(ce.hasNext())
			{
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcase"))
					{
						column=k;
					}
					k++;
			}
			System.out.println(column);
			
			while(rows.hasNext())
			{
				Row r = rows.next();
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcase))
				{
					
					Iterator<Cell> cv = r.cellIterator();
					while(cv.hasNext())
					{
						DataFormatter df = new DataFormatter();
						
						
						//System.out.println(df.formatCellValue(cv.next())); 
						a.add(df.formatCellValue(cv.next()));
						//System.out.println(cv.next().getStringCellValue());
						
					}
					
					
				}
				
			}
			}
			
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	

	}

}
