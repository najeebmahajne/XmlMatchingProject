
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dictionary {
	 final String fileName="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\DICTIONARY\\dic.xlsx"; 
	HashMap <String,String> data;
	
	
	public Dictionary() {
 
		try {
			this.readDataFromExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setData(HashMap<String, String> data) {
		this.data.putAll(data);
	}
	public void setData(String h,String a) {
		this.data.put(h, a);
	}
	private void readDataFromExcel() throws IOException {
		FileInputStream file =new FileInputStream(fileName);
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("sheet1");
		int rows=sheet.getLastRowNum();
		data=new HashMap<String,String>();

		//reading data from excel to HashMap
		for (int r = 0; r <= rows; r++) {
			try {
				String key=sheet.getRow(r).getCell(0).getStringCellValue();
				String value=sheet.getRow(r).getCell(1).getStringCellValue();	
				data.put(key, value);
			} catch (Exception e) {
				System.out.println("in line "+ r);
			}
		}
//		for (Map.Entry entry :data.entrySet()) {
//			try {
//				System.out.println(entry.getKey() +"  "+entry.getValue());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}	
		workbook.close();
	}
	public String getValue(String key) {
		if(data.containsKey(key))
			return data.get(key);
		else 
			return null;
	}


}
