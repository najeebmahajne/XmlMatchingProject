import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFExtractionByAreas {

	private static String[] PDFFILESARRAY = new String[5];
	private static String[] BODYOUTPUTFILESARRAY = new String[5];
	private static String[] MARGINSOUTPUTFILESARRAY = new String[5];
	private static String[] COVERPAGEOUTPUTFILESARRAY = new String[5];
	private static String[] FOOTEROUTPUTFILESARRAY = new String[5];
	public static String[] XMLFILESARRAY = new String[5];

	public static void main(String args[]) throws IOException {

		String[] documentsNumbers = { "2790" };
		for (int docNumber = 0; docNumber < documentsNumbers.length; docNumber++) {

			int x = 0, y = 0, width = 0, height = 0, coverWidth = 0, marginsX = 0, marginsH = 0;
			String docNum = documentsNumbers[docNumber];

			XMLFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\booklet.xml";
			BODYOUTPUTFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\" + docNum + "BODYTEXT.txt";
			PDFFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\" + docNum + ".pdf";
			MARGINSOUTPUTFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\" + docNum + "MARGINSTEXT.txt";
			COVERPAGEOUTPUTFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\" + docNum + "COVERPAGETEXT.txt";
			FOOTEROUTPUTFILESARRAY[0] = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\"
					+ docNum + "\\" + docNum + "FOOTERTEXT.txt";
			for (int fileNum = 0; fileNum < 1; fileNum++) {

				try {
					PDDocument doc = PDDocument.load(new File(PDFFILESARRAY[fileNum]));
					PDDocumentInformation info = doc.getDocumentInformation();
					System.out.println("Page Count= " + doc.getNumberOfPages());
					System.out.println("Title = " + info.getTitle());
					System.out.println("Author = " + info.getAuthor());

					COSDocument info1 = doc.getDocument();
					System.out.println("StartXref = " + info1.getStartXref());
					System.out.println("Version = " + info1.getVersion());

					PDFTextStripperByArea stripper = new PDFTextStripperByArea(); // Retrieving text from PDF document
					stripper.setSortByPosition(true);

					int pagesNum = doc.getNumberOfPages();

					FileWriter myWriter2 = new FileWriter(new File(COVERPAGEOUTPUTFILESARRAY[fileNum]), true);

					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					factory.setIgnoringElementContentWhitespace(true);
					factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
					factory.setNamespaceAware(true);
					DocumentBuilder db = factory.newDocumentBuilder();
					Document xmlDoc = db.parse(new File(XMLFILESARRAY[0]));
					Node FRBRdate = xmlDoc.getElementsByTagName("FRBRdate").item(0);
					NamedNodeMap attr = FRBRdate.getAttributes();
					Node date = attr.getNamedItem("date");
					String attributeTXT = date.getTextContent();
					String datestr = attributeTXT.substring(0, 4);
					int dateInt = Integer.valueOf(datestr);
					if (dateInt > 2015) {
						x = 96;
						y = 146;
						width = 360;
						height = 560;
						coverWidth = 400;
						marginsX = 450;
						marginsH = 535;
					} else if (dateInt <= 2015) {
						x = 96;
						y = 146;
						width = 360;
						height = 560;
						coverWidth = 420;
						marginsX = 460;
						marginsH = 500;
					}

					PDPage coverPage = doc.getPage(0);
					// PDRectangle cropBox=coverPage.getCropBox();
					Rectangle coverRect = new Rectangle(x, y, coverWidth, height);
					stripper.addRegion("cover", coverRect);
					stripper.extractRegions(coverPage);
					String coverText = stripper.getTextForRegion("cover");
					String[] coverSentences = coverText.split("\r\n");
					for (int i = 0; i < coverSentences.length; i++) {
						if (coverSentences[i].contains("57") && coverSentences[i].contains("قانون")) {
							int indx = coverSentences[i].indexOf("57");
							if (indx != -1 && indx + 4 <= coverSentences[i].length()) {
								coverSentences[i] = coverSentences[i].substring(0, indx + 4);
							} else {

								coverSentences[i] = coverSentences[i].substring(0, indx);
							}
							coverSentences[i] = coverSentences[i].replaceAll("\\d+\\s...", "");

						} else {
							int indx1 = coverSentences[i].indexOf("20");
							if (indx1 != -1) {
								coverSentences[i] = coverSentences[i].substring(0, indx1);
							}
							coverSentences[i] = coverSentences[i].replaceAll("\\d+\\.+", "");

						}
					}
					for (int i = 0; i < coverSentences.length; i++) {
						if (coverSentences[i].isBlank())
							continue;
						myWriter2.write(coverSentences[i] + ".\n");
					}
					myWriter2.close();
					stripper.removeRegion("cover");

					for (int pageNum = 1; pageNum < pagesNum; pageNum++) {
						FileWriter myWriter = new FileWriter(new File(BODYOUTPUTFILESARRAY[fileNum]), true);
						FileWriter myWriter1 = new FileWriter(new File(MARGINSOUTPUTFILESARRAY[fileNum]), true);
						FileWriter myWriter3 = new FileWriter(new File(FOOTEROUTPUTFILESARRAY[fileNum]), true);

						Rectangle rect = new Rectangle(x, y, width, height);
						PDPage firstPage = doc.getPage(pageNum);
						firstPage.getCOSObject();
						Rectangle rect1 = new Rectangle(marginsX, 146, 42, marginsH);
						stripper.addRegion("content", rect);
						stripper.addRegion("margins", rect1);
						stripper.extractRegions(firstPage);
						String text = stripper.getTextForRegion("content");

						String[] bodySentences = text.split("[\\.|*|:|]");
						String[] footerSentences = new String[bodySentences.length];
						int footerIndex = 0;

						for (int i = 0; i < bodySentences.length; i++) {
							if (bodySentences[i].contains("­") || bodySentences[i].contains("12016")
									|| bodySentences[i].contains("12019")) {
								bodySentences[i] = bodySentences[i].replaceAll("­", " ");
								bodySentences[i] = bodySentences[i].replaceAll("12016", "2016");
								bodySentences[i] = bodySentences[i].replaceAll("12019", "2019");
							}
							if (bodySentences[i].contains("قانون")) {
								int index = bodySentences[i].indexOf("قانون");
								if (index == 0)
									bodySentences[i] = "";

							}
							if (bodySentences[i].contains("أقرته")) {
								if (i + 2 <= bodySentences.length - 1 && bodySentences[i + 2].contains("\r\n")) {
									String[] str = bodySentences[i + 2].split("\r\n");
									bodySentences[i] += "'" + bodySentences[i + 1] + str[0];
									bodySentences[i + 1] = "";
									bodySentences[i + 2] = "";
								}
								if (bodySentences[i].contains("\r\n")) {
									bodySentences[i] = bodySentences[i].replaceAll("\r\n", " ");
								}
								bodySentences[i] = bodySentences[i].replaceFirst("\\s+1\\s+", " ");
								footerSentences[footerIndex] = bodySentences[i];
								bodySentences[i] = "";
								;
								footerIndex++;

							}
							if (bodySentences[i].contains("بنيامين") && bodySentences[i].contains("رئيس الكنيست")) {
								bodySentences[i] = bodySentences[i].replaceAll("تاري خ", "تاريخ");
								bodySentences[i] = bodySentences[i].replaceAll("االنتخابات", "الانتخابات");
								int firstIndx = bodySentences[i].indexOf("بنيامين");
								int lastIndx = bodySentences[i].indexOf("رئيس الكنيست");
								if (lastIndx > firstIndx && lastIndx != -1 && firstIndx != -1)
									bodySentences[i] = bodySentences[i].substring(0, firstIndx)
											+ bodySentences[i].substring(lastIndx + 12);

							}

						}
						// BODY TEXT
						for (int i = 0; i < bodySentences.length; i++) {
							if (bodySentences[i].isBlank())
								continue;
							System.out.println(bodySentences[i]);
							myWriter.write(bodySentences[i] + ".\n");

						}
						// FOOTER TEXT
						for (int i = 0; i < footerSentences.length; i++) {
							if (footerSentences[i] == null)
								continue;
							if (footerSentences[i].contains("كتاب")) {
								int index = footerSentences[i].indexOf("كتاب");
								if (index != -1)
									footerSentences[i] = footerSentences[i].substring(0, index);

							}
							System.out.println(footerSentences[i]);
							myWriter3.write(footerSentences[i] + ".\n");

						}

						// System.out.println(text);

						// MARGIN TEXT
						String marginsText = stripper.getTextForRegion("margins");
						if (marginsText.isBlank() == false) {
							marginsText = marginsText.replaceAll("د ة", "دة");
							marginsText = marginsText.replaceAll("د ة", "دة");
							marginsText = marginsText.replaceAll("االنتخابا ت", "الانتخابات");
							marginsText = marginsText.replaceAll("الكنيس ت", "الكنيست");
							String regexMargins = "\r\n\\d+";
							Pattern pattern = Pattern.compile(regexMargins);

							// get a matcher object
							Matcher matcher = pattern.matcher(marginsText);
							ArrayList<String> arrli = new ArrayList<String>(5);
							while (matcher.find()) {
								// Prints the start index of the match.
								arrli.add(matcher.group());
								System.out.println("Match String start(): " + matcher.start());
							}
							for (int i = 0; i < arrli.size(); i++) {
								String str = arrli.get(i);
								String replacment = str.replaceAll("\r\n", " ");
								marginsText = marginsText.replaceAll(str, replacment);
							}

							String[] margins = marginsText.split("\r\n");

							for (int i = 0; i < margins.length; i++) {
								if (margins[i].isBlank())
									continue;

								System.out.println(margins[i]);

								myWriter1.write(margins[i] + ".\n");

							}
						}

						myWriter3.close();
						myWriter1.close();
						myWriter.close();

					}

				} catch (Exception exception) {
					System.out.println("not found");
				}
			}
		}

	}

}
