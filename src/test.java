import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER LAW BOOK NUMBER: \n");
		String input = sc.nextLine();
		String PDFDirectoryPath="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
				+ input;
		String XMLDirectoryPath="C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\ספר החוקים XML Hebrew\\ספר_החוקים-" + input;
		File PDFDirectory = new File(PDFDirectoryPath);
		File XMLDirectory = new File(XMLDirectoryPath);
		// Verify if it is a valid directory
		if (!(PDFDirectory.exists() && PDFDirectory.isDirectory())) {
			System.out.println(String.format("PDF Directory %s does not exist", PDFDirectory));
			return;
		}
		if (!(XMLDirectory.exists() && XMLDirectory.isDirectory())) {
			System.out.println(String.format("XML Directory %s does not exist", XMLDirectory));
			return;
		}

		FileFilter PDFFilefilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith(input) && file.getName().endsWith(".pdf")) {
					return true;
				}
				return false;
			}
		};

		FileFilter XMLFilefilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith("booklet") && file.getName().endsWith(".xml")) {
					return true;
				}
				return false;
			}
		};
		FileFilter CoverPageTXTFileFilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith(input + "COVERPAGE") && file.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};
		FileFilter BodyTXTFileFilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith(input + "BODY") && file.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};
		FileFilter FooterTXTFileFilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith(input + "FOOTER") && file.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};
		FileFilter MarginsTXTFileFilter = new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().startsWith(input + "MARGINS") && file.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};

		File[] PDFFiles = PDFDirectory.listFiles(PDFFilefilter);
		File[] XMLFiles = XMLDirectory.listFiles(XMLFilefilter);
		File[] CoverTextFile = PDFDirectory.listFiles(CoverPageTXTFileFilter);
		File[] BodyTextFile = PDFDirectory.listFiles(BodyTXTFileFilter);
		File[] FooterTextFile = PDFDirectory.listFiles(FooterTXTFileFilter);
		File[] MarginsTextFile = PDFDirectory.listFiles(MarginsTXTFileFilter);

		String PDFPath=null,XMLPath=null,CoverTextFilePath=null,BodyTextFilePath=null,FooterTextFilePath=null,MarginsTextFilePath=null;
		for (int i = 0; i < 1; i++) {
			PDFPath=PDFDirectoryPath+"\\"+PDFFiles[i].getName();
			XMLPath=XMLDirectoryPath+"\\"+XMLFiles[i].getName();
			CoverTextFilePath=PDFDirectoryPath+"\\"+CoverTextFile[i].getName();
			BodyTextFilePath=PDFDirectoryPath+"\\"+BodyTextFile[i].getName();
			FooterTextFilePath=PDFDirectoryPath+"\\"+FooterTextFile[i].getName();
			System.out.println(PDFPath);
			System.out.println(XMLPath);
			System.out.println(CoverTextFilePath);
			System.out.println(BodyTextFilePath);
			System.out.println(FooterTextFilePath);
			if (MarginsTextFile.length != 0) {
				MarginsTextFilePath=PDFDirectoryPath+"\\"+MarginsTextFile[i].getName();
				System.out.println(MarginsTextFilePath);
			} else {
				System.out.println("NO MARGINS FILE FOUND FOR THIS FILE !");
			}
		}
		/*
		 * for (File f: PDFFiles) { System.out.println(f.getName()); } for (File f:
		 * XMLFiles) { System.out.println(f.getName()); } for (File f: CoverTextFile) {
		 * System.out.println(f.getName()); } for (File f: BodyTextFile) {
		 * System.out.println(f.getName()); } for (File f: FooterTextFile) {
		 * System.out.println(f.getName()); } if(MarginsTextFile.length!=0) {
		 * System.out.println(MarginsTextFile[0]);}else {
		 * System.out.println("NO MARGINS FILE FOUND FOR THIS FILE !"); }
		 */
	}

}
