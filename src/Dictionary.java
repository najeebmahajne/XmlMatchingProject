
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dictionary {
	 final String fileName="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\DICTIONARY\\dic.xlsx"; 
	HashMap <String,String> defenitions=new HashMap<String, String>();
	 
	
	public Dictionary() {
		defenitions.put("איילת שקד","أييلت شكيد");
		defenitions.put("","يولي يوئيل ادلشتاين");
		defenitions.put("שרת המשפטים","وزيرة العدل");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("","");
		defenitions.put("עבירת מין","اعتداء جنسي");
		defenitions.put("חיים כץ","حاييم كاتس");
		defenitions.put("שר העבודה הרווחה והשירותים החברתיים"," وزير العمل ، الرفاه والخدمات الاجتماعية");
		//defenitions.put("חברה","");
		defenitions.put("בית עלמין","مقبرة");
		defenitions.put("ממלא מקום שר הביטחון","ووكيل وزير الأمن");
		defenitions.put("שר הפנים","وزير الداخلية");
		defenitions.put("אריה מכלוף דרעי","أرييه مخلوف درعي");
		defenitions.put("בנימין נתניהו ראש הממשלה ","بنيامين نتنياهو رئيس الحكومة");
		defenitions.put("שירותים","خدمات");
		defenitions.put("בסעיףקטן","في الفقرة");
		defenitions.put("בסעיף","في الفقرة");
		defenitions.put("בפסקה","في البند");
		defenitions.put("במקום","بدلا من");
		defenitions.put("הדין","المحاكمات");
		defenitions.put("בטבת","طبيت");
		defenitions.put("התש \"ף","5780");
		defenitions.put("יבוא","يحل");
		defenitions.put("התשע \"ו","5776");
		defenitions.put("התשע \" ו","5776");
	     defenitions.put("הגדרות", "تعاريف");
	     defenitions.put("חוק", "قانون");
	     defenitions.put("הטבות", "االنتفاعات");
	     defenitions.put("השבת תמורה", "استعادة العوض");
	     defenitions.put("חבילת תיור", "رزمة سياحية");
	     defenitions.put("חניית ביניים","توقف لالستراحة");
	     defenitions.put("טיסה","رحلة طيران");
	     defenitions.put("טיסה שבוטלה","رحلة طيران ملغاة");
	     defenitions.put("יעד סופי","المحطة النهائية");
	     defenitions.put("כרטיס טיסה","تذكرة طيران");
	     defenitions.put("כרטיס טיסה חלופי","تذكرة طيران بديلة");
	     defenitions.put("מארגן","المنظم");
	     defenitions.put("מפעיל טיסה","المشغل الجوي");
	     defenitions.put("נותן שירותי סוכנות נסיעות","مقدم خدمات وكالة السفر");
	     defenitions.put("פיצוי כספי","التعويض المالي");
	     defenitions.put("עילה מזכה","علة االستحقاق");
	     defenitions.put("רשות שדות התעופה","سلطة المطارات");
	     defenitions.put("שירותי סוכנות נסיעות","خدمات وكالة السفر");
	     defenitions.put("שירותי סיוע","خدمات اضافية");
	     defenitions.put("השר","الوزير");
	     defenitions.put("בנימין נתניהו","بنيامين نتنياهو");
	     defenitions.put("יעקב נאמן","يعقوب نئيمان");
	     defenitions.put("שמעון פרס","شمعون بيرس");
	     defenitions.put("ראובן ריבלין","رؤبين ريفلين");
	     defenitions.put("ראש הממשלה","رئيس الحكومة");
	     defenitions.put("שר המשפטים","وزير العدل");
	     defenitions.put("נשיא המדינה","رئيس الدولة");
	     defenitions.put("יושב ראש הכנסת","رئيس الكنيست");
	     defenitions.put("הפרשי הצמדה וריבית","فروقات الربط والفائدة");
	     defenitions.put("עבודה ברכוש המשותף","العمل بالممتلكات المشتركة");
	     defenitions.put("תאריך כניסה לתוקף","حسب مدلولها");
	     defenitions.put("מחבר המסמך","كاتب الملف");
	     defenitions.put("רשומות","السجلات");
	     defenitions.put("עורך המסמך","محرر الملف");
	     defenitions.put("סיווג החוקים","تصنيف القوانين");
	     defenitions.put("כניסה לתוקף","تاريخ السريان");
	     defenitions.put("תכנית החיזוק","خطة التعزيز");
	     defenitions.put("הכנסת","الكنيست");
	     defenitions.put("יצחק אהרונוביץ","اسحاق اهارونوفيتش");
	     defenitions.put("ספר החוקים","كتاب القوانين");
	     defenitions.put("סמל מדינת ישראל","رمز دولة اسرائيل");
	     defenitions.put("תאריך פרסום המסמך","تاريخ نشر الملف");
	     defenitions.put("שלום שמחון","شالوم سمحون");
	     defenitions.put("יריב לוין","يريف لفين");
	     defenitions.put("שר התעשייה המסחר והתעסוקה","وزير الصناعة والتجارة والعمل");
	     defenitions.put("יולי יואל אדלשטיין","يولي يوئيل ادلشتاين");
	     defenitions.put("בצלאל סמוטריץ'","بتسلئيل سموتريتش");
	     defenitions.put("שר התחבורה והבטיחות בדרכים","وزير المواصلات والسلامة في الطرق");
	     defenitions.put("אמיר אוחנה","أمير اوحانا");
	     defenitions.put("שר המשפטים","وزير العدل");
	     defenitions.put("בנימין גנץ","بنيامين جانتس");
	     defenitions.put("אבי ניסנקורן","آفي نيسان كورن");
	     defenitions.put("השר לביטחון הפנים","");
	     defenitions.put("יובל שטייניץ","يوفال شتاينيتس");
	     defenitions.put("שר האנרגיה","وزير الطاقة");
	     defenitions.put("ישראל כץ","يسرائيل كاتس");
	     defenitions.put("שר האוצר","وزير المالية");
	     defenitions.put("סעיף","المادة");
	     defenitions.put("תחילה","بدء سريان");
	     defenitions.put("תחילתו","يبدأ سريان");
	     defenitions.put("עמוד","صفحة");
	     defenitions.put("תיקון","تعديل");
	     defenitions.put("בתמוז","تموز");
	     defenitions.put("התשמ \"ט	","5749");
	     defenitions.put("\"ח'","\"8");
	     defenitions.put("\"י\"ב","12");
	     defenitions.put("התש\"ף","");
	     defenitions.put("התשפ\"א","5781");
	     defenitions.put("התש\"ף","");
	     defenitions.put("","");
	     defenitions.put("","");
	     defenitions.put("","");
	     defenitions.put("","");
	     
		//try {
			//this.readDataFromExcel();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}
	public void setData(HashMap<String, String> data) {
		this.defenitions.putAll(data);
	}
	public void setData(String h,String a) {
		this.defenitions.put(h, a);
	}
	@SuppressWarnings("unused")
	private void readDataFromExcel() throws IOException {
		FileInputStream file =new FileInputStream(fileName);
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("sheet1");
		int rows=sheet.getLastRowNum();
		defenitions=new HashMap<String,String>();

		//reading data from excel to HashMap
		for (int r = 0; r <= rows; r++) {
			try {
				String key=sheet.getRow(r).getCell(0).getStringCellValue();
				String value=sheet.getRow(r).getCell(1).getStringCellValue();	
				defenitions.put(key, value);
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
		if(defenitions.containsKey(key))
			return defenitions.get(key);
		else 
			return null;
	}


}
