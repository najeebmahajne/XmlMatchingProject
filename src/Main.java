import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import com.simplileam.mavenproject.JaccardDistance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	

	public Scanner myReader = new Scanner(System.in);

	static HashMap<String, String> noMatchingFoundDictionary = new HashMap<String, String>();

	public static void main(String[] args) throws FileNotFoundException {

		
		Dictionary defenitions = new Dictionary();
		
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

		String PDFPath=null,XMLPath=null,CoverTextFilePath=null,BodyTextFilePath=null,FooterTextFilePath=null,MarginsTextFilePath=null,OutputFile=null;
		for (int i = 0; i < 1; i++) {
			OutputFile=PDFDirectoryPath+"\\"+input+"Arabic.xml";
			PDFPath=PDFDirectoryPath+"\\"+PDFFiles[i].getName();
			XMLPath=XMLDirectoryPath+"\\"+XMLFiles[i].getName();
			CoverTextFilePath=PDFDirectoryPath+"\\"+CoverTextFile[i].getName();
			BodyTextFilePath=PDFDirectoryPath+"\\"+BodyTextFile[i].getName();
			FooterTextFilePath=PDFDirectoryPath+"\\"+FooterTextFile[i].getName();
			
			if (MarginsTextFile.length != 0) {
				MarginsTextFilePath=PDFDirectoryPath+"\\"+MarginsTextFile[i].getName();
			} else {
				System.out.println("NO MARGINS FILE FOUND FOR THIS FILE !");
				System.out.println("============================================================================");
			}
		}
		
		
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setIgnoringElementContentWhitespace(true);
				factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
				factory.setNamespaceAware(true);
				DocumentBuilder db = factory.newDocumentBuilder();
				Document firstDoc = db.parse(new File(XMLPath));
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				DOMSource source = new DOMSource(firstDoc);
				File myObj = new File(OutputFile);
				StreamResult sresult = new StreamResult(myObj);
				firstDoc.getDocumentElement().normalize();
				System.out.println("Root Element :" + firstDoc.getDocumentElement().getNodeName());
				System.out.println("------");

				// COVERPAGE TEXT PROCCESSING
				PDFTextProccessing coverPageText = new PDFTextProccessing(CoverTextFilePath, 'A');
				coverPageText.Normalization();

				Node componentInfoTag = firstDoc.getElementsByTagName("componentInfo").item(0);
				Node SECONDcomponentInfoTag = firstDoc.getElementsByTagName("componentInfo").item(1);

				// FIRST+SECOND COMPONENT CHILDS LIST
				NodeList list = componentInfoTag.getChildNodes();
				NodeList SECONDlist = SECONDcomponentInfoTag.getChildNodes();

				// collectionBody Tag
				Node collectionBodyTag = firstDoc.getElementsByTagName("collectionBody").item(0);
				NodeList thirdList = collectionBodyTag.getChildNodes();

				// First two componentsdata Tags change , because they have the same value in
				// each document in all documents
				// +docTitle Tag
				for (int current = 0; current < list.getLength(); current++) {
					Node componentDataTag = list.item(current);
					Node secondcomponentDataTag = SECONDlist.item(current);
					Node collectionBodyTagChild = thirdList.item(current);
					if (componentDataTag.hasAttributes()) {
						NamedNodeMap attr = componentDataTag.getAttributes();
						Node node = attr.getNamedItem("showAs");
						NamedNodeMap secondattr = secondcomponentDataTag.getAttributes();
						Node secondnode = secondattr.getNamedItem("showAs");
						NamedNodeMap thirdattr = collectionBodyTagChild.getAttributes();
						Node thirdnode = thirdattr.getNamedItem("showAs");
						if (node != null) {

							String attributeTXT = node.getTextContent();
							translationAlgorithm(attributeTXT, coverPageText, node, 0f);
							secondnode.setTextContent(node.getTextContent());
							thirdnode.setTextContent(node.getTextContent());
						}
					}
				}

				// the third <componentInfo> tag - different text sources
				Node secondcomponentInfoTag = firstDoc.getElementsByTagName("componentInfo").item(2);
				NodeList secondList = secondcomponentInfoTag.getChildNodes();
				for (int current = 0; current < secondList.getLength(); current++) {
					Node componentDataTag = secondList.item(current);
					if (componentDataTag.hasAttributes()) {
						NamedNodeMap attr = componentDataTag.getAttributes();
						Node node = attr.getNamedItem("showAs");
						if (node != null) {
							String attributeTXT = node.getTextContent();
							System.out.println(componentDataTag.getLocalName() + " 'showAs' attribute text");
							dictionaryCheck(attributeTXT, defenitions, node);
						}
					}
				}
				String breakLine = "============================================================================";
				System.out.println(breakLine);
				System.out.println();

				// <organization> Tag text change
				Node organizationTag = firstDoc.getElementsByTagName("organization").item(0);
				dictionaryCheck(organizationTag.getFirstChild().getTextContent(), defenitions, organizationTag);
				System.out.println(breakLine);
				System.out.println();
				// <docType> Tag text change
				Node docTypeTag = firstDoc.getElementsByTagName("docType").item(0);
				dictionaryCheck(docTypeTag.getFirstChild().getTextContent(), defenitions, docTypeTag);
				System.out.println(breakLine);
				System.out.println();

				coverPageText.Normalization();
				// <tocHeading>
				NodeList tocHeadingList = firstDoc.getElementsByTagName("tocHeading");
				for (int i = 1; i < tocHeadingList.getLength(); i++) {
					Node tocHeadingTag = tocHeadingList.item(i);
					String tocHeadingText = tocHeadingTag.getTextContent();
					tocHeadingText = tocHeadingText.replaceAll("התש\"ף", "");
					boolean dictionarySubstituted = dictionaryCheck(tocHeadingText, defenitions, tocHeadingTag);
					if (dictionarySubstituted) {
						continue;
					} else {
						translationAlgorithm(tocHeadingText, coverPageText, tocHeadingTag, 0f);

					}

				}

				
				
				
				
				
				
				// all <refernces> tag attr text change

				NodeList referencesList = firstDoc.getElementsByTagName("references");
				for (int num = 0; num < referencesList.getLength(); num++) {
					Node secondReferencesTag = firstDoc.getElementsByTagName("references").item(num);
					NodeList secondReferencesTagChilds = secondReferencesTag.getChildNodes();

					for (int current = 0; current < secondReferencesTagChilds.getLength(); current++) {
						Node secondReferencesTagChild = secondReferencesTagChilds.item(current);

						if (secondReferencesTagChild.hasAttributes()) {
							NamedNodeMap attr = secondReferencesTagChild.getAttributes();
							Node node = attr.getNamedItem("showAs");

							if (node != null) {
								System.out.println("* REFFERENCES TAG 'showAs' Attribute txt subtitute * ");
								System.out.println();
								String attributeTXT = node.getTextContent();
								dictionaryCheck(attributeTXT, defenitions, node);

							}
						}
					}
				}

				// <publication> tag showas attr text change
				Node publicationTag = firstDoc.getElementsByTagName("publication").item(0);
				NamedNodeMap publicationTagAttributes = publicationTag.getAttributes();
				Node node = publicationTagAttributes.getNamedItem("showAs");
				System.out.println("* PUBLICATION TAG 'showAs' Attribute txt subtitute * ");
				System.out.println();
				String attributeTXT = node.getTextContent();
				dictionaryCheck(attributeTXT, defenitions, node);

				System.out.println(breakLine);

				// <signature> Tags list - Childs txt subtitute
				System.out.println("* SIGNITURES CHILDS TEXT SUBSTITUTION *");
				NodeList signatureTags = firstDoc.getElementsByTagName("signature");

				for (int current = 0; current < signatureTags.getLength(); current++) {
					Node signatureTag = signatureTags.item(current);
					if (signatureTag.getNodeType() == Node.ELEMENT_NODE) {
						Node temp = signatureTags.item(current).getFirstChild().getNextSibling();
						Node Persontag = temp.getFirstChild().getNextSibling();
						Node Roletag = Persontag.getNextSibling().getNextSibling();

						String PersontagStr = Persontag.getTextContent();
						String RoletagStr = Roletag.getTextContent();
						System.out.println(breakLine);
						System.out.println("Signature Number - " + current);
						System.out.println();
						System.out.println("<PERSON> TXT : " + PersontagStr);
						System.out.println("<Role> TXT : " + RoletagStr);
						System.out.println();
						dictionaryCheck(PersontagStr, defenitions, Persontag);
						dictionaryCheck(RoletagStr, defenitions, Roletag);

					}
				}

				System.out.println(breakLine);

				System.out.println(breakLine);

				// BODY TEXT PROCCESSING
				PDFTextProccessing bodyText = new PDFTextProccessing(BodyTextFilePath, 'A');
				bodyText.Normalization();
				coverPageText.Normalization();

				// intro
				NodeList introTagList = firstDoc.getElementsByTagName("intro");
				if (introTagList.getLength() > 0) {

					for (int i = 0; i < introTagList.getLength(); i++) {
						Node temp = introTagList.item(i);
						NodeList introTagChildren = introTagList.item(i).getChildNodes();

						for (int j = 0; j < introTagChildren.getLength(); j++) {
							Node child = introTagChildren.item(j);
							if (child.getNodeType() == Node.ELEMENT_NODE) {
								if (child.getChildNodes().getLength() > 1) {
									Node firstTextNode = child.getFirstChild();
									String text = firstTextNode.getTextContent();
									// be carful
									if (text.isBlank() == false) {
										int beginIndex = bodyText.sentences[i].indexOf("–");
										int endIndex = bodyText.sentences[i].indexOf("\u060C");
										if (beginIndex < endIndex && beginIndex != -1 && endIndex != -1) {
											String PDFSentence = bodyText.sentences[i].substring(beginIndex + 1,
													endIndex);
											// hay als6r bjoz tm7ah 335
											bodyText.sentences[i] = bodyText.sentences[i].substring(endIndex + 1);
											System.out.println("Arabic PDF Sentence : " + PDFSentence);
											CheckMatchingDistance(text, PDFSentence, firstTextNode, bodyText);
											System.out.println();

										}

										// last Text child
										if (endIndex > 0) {
											Node secondTextNode = child.getLastChild();
											String text2 = secondTextNode.getTextContent();
											int nextSplitterIndex = bodyText.sentences[i].indexOf("-", endIndex);
											if (nextSplitterIndex == -1)
												nextSplitterIndex = bodyText.sentences[i].indexOf("–", 0);
											if (nextSplitterIndex + 1 > endIndex) {
												String PDFSentence2 = bodyText.sentences[i].substring(endIndex,
														nextSplitterIndex + 1);
												System.out.println("Arabic PDF Sentence : " + PDFSentence2);
												CheckMatchingDistance(text2, PDFSentence2, secondTextNode, bodyText);
												int nextSplitterIndex1 = bodyText.sentences[i].indexOf("-",
														nextSplitterIndex + 1);
												if (nextSplitterIndex1 != -1) {
													bodyText.sentences[i] = bodyText.sentences[i]
															.substring(nextSplitterIndex1);
												} else {
													continue;
												}
											} else {
												String PDFSentence2 = bodyText.sentences[i].substring(0,
														nextSplitterIndex + 1);
												System.out.println("Arabic PDF Sentence : " + PDFSentence2);
												CheckMatchingDistance(text2, PDFSentence2, secondTextNode, bodyText);
												bodyText.sentences[i] = bodyText.sentences[i]
														.substring(nextSplitterIndex + 1);

											}
										}
									}

								} else {
									Node pNode = child.getFirstChild();
									String text1 = pNode.getTextContent();
									int beginIndex1 = bodyText.sentences[0].indexOf("–");
									String PDFText = bodyText.sentences[0].substring(0, beginIndex1 + 1);
									System.out.println("Arabic PDF Sentence : " + PDFText);
									CheckMatchingDistance(text1, PDFText, pNode, bodyText);
									bodyText.sentences[0] = bodyText.sentences[0].substring(beginIndex1 + 1);
								}
							}
						}
					}

				}

				// <content> P CHILD TEXT substitute
				NodeList contentTagList = firstDoc.getElementsByTagName("content");

				for (int current = 0; current < contentTagList.getLength(); current++) {
					Node contentTag = contentTagList.item(current);
					NodeList contentTagChilds = contentTag.getChildNodes();

					for (int i = 0; i < contentTagChilds.getLength(); i++) {
						Node contentChildern = contentTagChilds.item(i);
						if (contentChildern.getNodeType() == Node.ELEMENT_NODE && current % 2 == 0) {
							if (contentChildern.getChildNodes().getLength() > 1
									&& contentChildern.getChildNodes().item(1).getLocalName() != "def") {
								// first text child
								Node firstTextNode = contentChildern.getFirstChild();
								String text = firstTextNode.getTextContent();
								if (text.isBlank() || text.length() <= 2)
									continue;
								int beginIndex = bodyText.sentences[current].indexOf("–");
								int endIndex = bodyText.sentences[current].indexOf("\u060C");
								if (beginIndex < endIndex && beginIndex != -1 && endIndex != -1) {
									String PDFSentence = bodyText.sentences[current].substring(beginIndex + 1,
											endIndex);

									System.out.println("Arabic PDF Sentence : " + PDFSentence);
									CheckMatchingDistance(text, PDFSentence, firstTextNode, bodyText);
									System.out.println();

								} else {
									String PDFSentence = bodyText.sentences[current].substring(0);
									System.out.println("Arabic PDF Sentence : " + PDFSentence);
									CheckMatchingDistance(text, PDFSentence, firstTextNode, bodyText);
									System.out.println();
								}

								// last Text child
								if (endIndex > 0) {
									Node secondTextNode = contentChildern.getLastChild();
									String text2 = secondTextNode.getTextContent();
									if (current + 1 < bodyText.sentences.length
											&& endIndex < bodyText.sentences.length) {

										String PDFSentence2 = bodyText.sentences[current + 1].substring(endIndex);
										System.out.println("Arabic PDF Sentence : " + PDFSentence2);
										CheckMatchingDistance(text2, PDFSentence2, secondTextNode, bodyText);
										System.out.println();

										bodyText.sentences[current + 1] = "";
										break;
									} else {
										String PDFSentence2 = bodyText.sentences[current].substring(endIndex);
										System.out.println("Arabic PDF Sentence : " + PDFSentence2);
										CheckMatchingDistance(text2, PDFSentence2, secondTextNode, bodyText);

										System.out.println();

										bodyText.sentences[current] = "";
										break;
									}

								}

							} else if (contentChildern.getChildNodes().getLength() == 1) {
								Node pNode = contentChildern.getFirstChild();
								String text1 = pNode.getTextContent();
								if (current < bodyText.sentences.length) {
									int beginIndex1 = bodyText.sentences[current].indexOf(".");
									if (beginIndex1 == -1)
										beginIndex1 = bodyText.sentences[current].indexOf("،");
									String PDFText = bodyText.sentences[current].substring(0, beginIndex1 + 1);
									System.out.println("Arabic PDF Sentence : " + PDFText);
									CheckMatchingDistance(text1, PDFText, pNode, bodyText);
									if (beginIndex1 == -1) {
										translationAlgorithm(text1, bodyText, pNode, 0.75f);
									}

									// bodyText.sentences[current]="";
								}
							}

						} else if (contentChildern.getNodeType() == Node.ELEMENT_NODE && current % 2 != 0) {
							Node pNode = contentChildern.getFirstChild();
							String PDFText = "";
							String text1 = pNode.getTextContent();
							text1 = text1.replaceAll("\n", "");
							if (text1.isBlank())
								continue;
							int beginIndex1 = 0;
							if (current == bodyText.sentences.length) {
								beginIndex1 = bodyText.sentences[current - 1].indexOf("–");
							} else {
								beginIndex1 = bodyText.sentences[current].indexOf("–");
							}
							try {
								if (beginIndex1 + 1 > bodyText.sentences[current].length()) {
									PDFText = bodyText.sentences[current].substring(beginIndex1 + 1);
								} else if (beginIndex1 > bodyText.sentences[current].length()) {
									PDFText = bodyText.sentences[current].substring(beginIndex1);
								} else {
									PDFText = bodyText.sentences[current].substring(0);
								}
								System.out.println("Arabic PDF Sentence : " + PDFText);
								CheckMatchingDistance(text1, PDFText, pNode, bodyText);
								bodyText.sentences[current] = "";
							} catch (Exception exception) {
								System.out.println("**SUBTITUTE FAILED**");
							}
						}
					}

				}

				PDFTextProccessing footerText = new PDFTextProccessing(FooterTextFilePath, 'A');
				footerText.Normalization();
				coverPageText.Normalization();

				// ARRAY TO STORE DATES FOR CHANGING ODD PLACES AUTHOURIALNOTE ** RIGHT
				// <doctitle>
				String[] oddPlacesAuthourialNote = new String[footerText.sentences.length];

				// <docTitle> Tag node and even places authourial notes text substitute
				NodeList docTitleTagList = firstDoc.getElementsByTagName("docTitle");
				for (int current = 0; current < docTitleTagList.getLength(); current++) {
					Node docTitleTag = docTitleTagList.item(current);
					NodeList docTitleTagChilds = docTitleTag.getChildNodes();
					for (int i = 0; i < docTitleTagChilds.getLength(); i++) {
						Node childNode = docTitleTagChilds.item(i);
						if (childNode.getNodeType() == Node.TEXT_NODE) {
							String childText = childNode.getTextContent();
							translationAlgorithm(childText, coverPageText, childNode, 0.72f);
						} else if (childNode.getNodeType() == Node.ELEMENT_NODE) {
							Node pText = childNode.getFirstChild().getFirstChild();
							String childText = pText.getTextContent();
							if (childText.contains(";")) {
								int index = childText.indexOf(";");
								childText = childText.substring(0, index);

							}

							String PDFText = footerText.sentences[current];
							if (PDFText.contains("ك'ق")) {
								PDFText = PDFText.replaceAll("ك'ق", "ك.ق");
								int index = PDFText.indexOf("ك.ق");
								oddPlacesAuthourialNote[current] = PDFText.substring(index);
								PDFText = PDFText.substring(0, index);

							}

							System.out.println("Arabic PDF Sentence : " + PDFText);
							CheckMatchingDistance(childText, PDFText, pText, footerText);

						}
					}

				}

				// authorialNote
				// (ONLY ODD PLACES,EVEN PLACES SUBSTITUTED IN THE PREVIOUS LOOP WITH DOCTITLE)
				// List
				NodeList authourialNoteList = firstDoc.getElementsByTagName("authorialNote");

				int oddPlacesIndex = 0;
				// JUMP BY 2 EACH TIME TO SKIP EVEN PLACES -STARTING FROM 1
				for (int current = 1; current < authourialNoteList.getLength(); current += 2) {
					Node authourialNoteElement = authourialNoteList.item(current);
					Node textNode = authourialNoteElement.getFirstChild().getFirstChild();
					if (textNode.getNodeType() == Node.TEXT_NODE) {
						String text = textNode.getTextContent();
						String PDFText = oddPlacesAuthourialNote[oddPlacesIndex];
						System.out.println("Arabic PDF Sentence : " + PDFText);
						if (PDFText != null) {
							CheckMatchingDistance(text, PDFText, textNode, footerText);
							oddPlacesIndex++;
						}
					}

				}

				
				
				
				
				// Margins TEXT PROCCESSING
				PDFTextProccessing marginsText = null;
				try {
					marginsText = new PDFTextProccessing(MarginsTextFilePath, 'A');
					marginsText.Normalization();
				} catch (Exception exception) {
					System.out.println("==================NO MARGINS=====================");
				}

				if (marginsText != null) {
					// heading Tags
					NodeList headingTagsList = firstDoc.getElementsByTagName("heading");
					for (int current = 0; current < headingTagsList.getLength(); current++) {
						Node headingTag = headingTagsList.item(current);
						NodeList headingTagChilds = headingTag.getChildNodes();
						for (int i = 0; i < headingTagChilds.getLength(); i++) {
							Node headingChildern = headingTagChilds.item(i);
							if (headingChildern.getNodeType() == Node.ELEMENT_NODE) {
								Node textNode = headingChildern.getFirstChild();
								String text = textNode.getTextContent();
								if (current < marginsText.sentences.length) {
									String PDFText = marginsText.sentences[current];
									System.out.println("Arabic PDF Sentence : " + PDFText);
									CheckMatchingDistance(text, PDFText, textNode, marginsText);
									marginsText.sentences[current] = "";
								}
							}
						}

					}
				}

				System.out.println("Words that Failed Finding a match in Dictionary :");
				System.out.println();
				noMatchingFoundDictionary.entrySet().forEach(entry -> {
					System.out.println(entry.getKey() + " " + entry.getValue());
				});

				transformer.transform(source, sresult);
				System.out.println("DOCUMENT SAVED!");
				System.out.println();

			}

			catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

	static int calculate(String x, String y) {
		int[][] dp = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			for (int j = 0; j <= y.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
							dp[i - 1][j] + 1, dp[i][j - 1] + 1);
				}
			}
		}

		return dp[x.length()][y.length()];
	}

	public static int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	public static int min(int... numbers) {
		return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
	}

	// removing numbers
	public static String removeAllDigit(String str) {
		// Converting the given string
		// into a character array
		char[] charArray = str.toCharArray();
		String result = "";

		// Traverse the character array
		for (int i = 0; i < charArray.length; i++) {

			// Check if the specified character is not digit
			// then add this character into result variable
			if (!Character.isDigit(charArray[i])) {
				result = result + charArray[i];
			}
		}

		// Return result
		return result;
	}


	@SuppressWarnings("unused")
	private static String removeVowels(String hebString) {
		String newString = "";
		for (int j = 0; j < hebString.length(); j++) {
			char c = hebString.charAt(j);
			if (hebString.charAt(j) < 1425 || c > 1479)
				newString = newString + c;
		}
		return newString;
	}

	public static PDFTextProccessing translationAlgorithm(String XMLTXT, PDFTextProccessing ArabicText, Node node,
			Float jaccardThreshhold) throws FileNotFoundException, IOException {
		TrainedTokenizer tok = new TrainedTokenizer();
		Dictionary defenitions = new Dictionary();
		XMLTagTextProccessing str1 = new XMLTagTextProccessing(XMLTXT);
		str1.sentencePrint();
		str1.tokensTranslation();
		int counter = 0;
		if (jaccardThreshhold == 0)
			jaccardThreshhold = 0.85f;
		for (int i = 0; i < str1.translatedTokens.length; i++) {

			String token = null;
			if (defenitions.defenitions.containsKey(str1.tokens[i])) {

				token = defenitions.defenitions.get(str1.tokens[i]);

				str1.TranslatedSentence1 = str1.TranslatedSentence1.replaceAll(str1.translatedTokens[i], token);
				str1.translatedTokens[i] = token;
				counter++;
				String withoutNums = removeAllDigit(str1.sentence);
				String[] arrWithoutNums = tok.tokenize(withoutNums);
				if (arrWithoutNums.length == counter) {
					System.out.println("Successfully Changed By Dictionary");
					System.out.println("************************");
					node.setTextContent(token);
					return ArabicText;
				}
			} else {
				token = str1.translatedTokens[i];
			}

			token = token.replaceAll("ال", "");

			boolean found = false;
			for (int j = 0; j < ArabicText.sentences.length; j++) {
				boolean result = ArabicText.sentences[j].contains(token);
				if (result) {
					if (ArabicText.sentences[j].contains("المادة") && token == "قانون")
						break;
					/*
					 * if(ArabicText.sentences[j].length()/3<=(str1.tokens.length)||ArabicText.
					 * sentences[j].length()>=(str1.tokens.length*3)) { //System.out.
					 * println("* HUGE LENGTH DIFFRENCE BETWEEN SENTENCES - KEEP GOING *"); //break;
					 * }
					 */
					int index = ArabicText.sentences[j].indexOf(token);
					float JaccardThreshold = 0.85f;
					if (i > 0) {
						String token1 = str1.translatedTokens[i - 1];
						int index1 = ArabicText.sentences[j].indexOf(token1);
						if (index1 != -1)
							index = ArabicText.sentences[j].indexOf(token1);
					}

					int endIndex = index + str1.translatedTokens.length;
					String text = ArabicText.sentences[j];

					System.out.print("Arab PDF Sentence : ");
					text = text.replaceAll("-", " ");
					String[] Arabictokens = tok.tokenize(text);
					String[] newArabictokens = Arrays.copyOfRange(Arabictokens, 0, endIndex);
					String sentence = String.join(" ", newArabictokens);
					sentence = sentence.replaceAll("null", "");
					sentence = sentence.replaceAll("–", "");
					sentence = sentence.replaceAll(":", "");
					sentence = sentence.replaceAll("\\.", "");
					System.out.println(sentence);
					str1.TranslatedSentence1 = str1.TranslatedSentence1.replaceAll("،", "");
					str1.TranslatedSentence1 = str1.TranslatedSentence1.replaceAll("'", "");
					str1.TranslatedSentence1 = str1.TranslatedSentence1.replaceAll("התשע״ב", "");
					String sentenceWithoutNumbers = removeAllDigit(sentence);
					sentenceWithoutNumbers = sentenceWithoutNumbers.replaceAll("-", "");
					String translatedSentenceWithoutNumbers = removeAllDigit(str1.TranslatedSentence1);
					sentenceWithoutNumbers = sentenceWithoutNumbers.replaceAll("اا", "ا");
					sentenceWithoutNumbers = sentenceWithoutNumbers.replaceAll("بك", "ك");
					sentenceWithoutNumbers = sentenceWithoutNumbers.replaceAll("لل", "ل");
					sentenceWithoutNumbers = sentenceWithoutNumbers.replaceAll("عات", "ع");
					sentence = sentence.replaceAll("اا", "ا");
					sentence = sentence.replaceAll("بك", "ك");
					sentence = sentence.replaceAll("لل", "ل");
					sentence = sentence.replaceAll("عات", "ع");
					translatedSentenceWithoutNumbers = translatedSentenceWithoutNumbers.replaceAll("الكف", "كف");
					translatedSentenceWithoutNumbers = translatedSentenceWithoutNumbers.replaceAll("إ", "ا");
					translatedSentenceWithoutNumbers = translatedSentenceWithoutNumbers.replaceAll("S", "");
					translatedSentenceWithoutNumbers = translatedSentenceWithoutNumbers.replaceAll("T", "");

					int lavveDiff = calculate(sentenceWithoutNumbers, translatedSentenceWithoutNumbers);
					int maxLength = Math.max(sentenceWithoutNumbers.length(),
							translatedSentenceWithoutNumbers.length());

					String translatedSentenceWithNumbers = str1.TranslatedSentence1;
					translatedSentenceWithNumbers = translatedSentenceWithNumbers.replaceAll("الكف", "كف");
					translatedSentenceWithNumbers = translatedSentenceWithNumbers.replaceAll("إ", "ا");

					int lavveDiff1 = calculate(sentence, translatedSentenceWithNumbers);
					int maxLength1 = Math.max(sentence.length(), translatedSentenceWithNumbers.length());

					// without numbers ratio
					float ratio = (float) lavveDiff / maxLength;

					// with numbers ratio
					float ratio1 = (float) lavveDiff1 / maxLength1;

					// between with numbers and without numbers
					int lavveDiff2 = calculate(sentence, translatedSentenceWithoutNumbers);
					int maxLength2 = Math.max(sentence.length(), translatedSentenceWithoutNumbers.length());
					float ratio2 = (float) lavveDiff2 / maxLength2;

					double jaccard = JaccardDistance.Jaccard2(sentenceWithoutNumbers, translatedSentenceWithoutNumbers);
					double jaccard1 = JaccardDistance.Jaccard2(sentence, translatedSentenceWithNumbers);
					double jaccard2 = JaccardDistance.Jaccard2(sentence, translatedSentenceWithoutNumbers);
					double jaccardMin = Math.min(jaccard, jaccard1);
					jaccardMin = Math.min(jaccardMin, jaccard2);

					float betterRatio1 = Math.min(ratio, ratio1);
					float betterRatio = Math.min(betterRatio1, ratio2);
					// str.TranslatedSentence1.replaceAll(")", "");System.out.println("Arabic
					// sentence in PDF: "+sentenceWithoutNumbers);
					if (betterRatio == ratio || betterRatio == ratio2) {
						System.out.println("Arabic translated sentence from XML: " + translatedSentenceWithoutNumbers);

					} else {
						System.out.println("Arabic translated sentence from XML: " + translatedSentenceWithNumbers);

					}

					// System.out.println("**WITHOUT NUMBERS**");
					System.out.println("Sentence Length: " + maxLength);
					System.out.println("LavenRatio : " + betterRatio);
					System.out.println("JaccardRatio : " + jaccardMin);

					if (betterRatio > 0.65f && jaccardMin > JaccardThreshold) {
						System.out.println("Changing Failed");
						break;
					}

					node.setTextContent(ArabicText.sentences[j]);
					System.out.println("Changed Succusfully With : " + ArabicText.sentences[j]);
					ArabicText.sentences[j] = "";

					List<String> stringList = new ArrayList<String>();
					Collections.addAll(stringList, sentence, str1.TranslatedSentence1);
					JaccardDistance.JaccardWeight(stringList);
					System.out.println(JaccardDistance.weightMap);
					System.out.println("============================================================================");
					JaccardDistance.weightMap = new HashMap<String, Double>();
					found = true;
					break;
				} else {
					continue;
				}

			}
			if (found)
				break;

		}

		return ArabicText;
	}

	public static boolean dictionaryCheck(String text, Dictionary dictionary, Node node) {

		if (dictionary.containsKey(text)) {
			node.setTextContent(dictionary.getValue(text));
			if (node.getLocalName() != null) {
				System.out.println("<" + node.getLocalName() + ">" + " Succuessfully SUBSTITUTED : " + text
						+ " - *WITH* - " + dictionary.getValue(text));
				System.out.println();
				return true;
			} else {
				System.out.println("TEXT Succuessfully Subtitute");
				System.out.println();
				return true;
			}
		} else {
			if (node.getLocalName() != null) {
				if (node.getLocalName() == "tocHeading") {
					System.out.println("<" + node.getLocalName() + ">" + " SUBTITUE FAILED [ " + text
							+ " ] NOT IN DICTIONARY - TRYING TRANSLATION ALGORITHM");
					System.out.println();
					return false;
				} else {
					noMatchingFoundDictionary.put(node.getLocalName(), text);
					System.out.println(
							"<" + node.getLocalName() + ">" + " SUBTITUE FAILED [ " + text + " ] NOT IN DICTIONARY");
					System.out.println();
					return false;
				}
			} else {
				System.out.println("SUBTITUE FAILED [ " + text + " ] NOT IN DICTIONARY");
				System.out.println();
				return false;
			}
		}
	}

	public static PDFTextProccessing CheckMatchingDistance(String XMLTXT, String PDFText, Node node,
			PDFTextProccessing text) throws FileNotFoundException, IOException {
		if (PDFText.isBlank()) {
			System.out.println("**BLANK PDF TEXT - ERROR WITH DIVIDING/PROCCESSING THE TEXT**");
			return text;
		}
		PDFText = PDFText.replaceAll("بد ال", "بدلا");
		PDFText = PDFText.replaceAll("\"", "");
		PDFText = PDFText.replaceAll("10", "في اكتوبر");
		PDFText = PDFText.replaceAll("1156", "156");

		XMLTagTextProccessing str1 = new XMLTagTextProccessing(XMLTXT);
		str1.TranslatedSentence1 = str1.TranslatedSentence1.replaceAll("\"", "");
		str1.tokensTranslation();
		if (str1.TranslatedSentence1.contains("من اليوم") || str1.TranslatedSentence1.contains("TZ")) {
			str1.TranslatedSentence1 = str1.TranslatedSentence1.replace("TZ", "");
			str1.TranslatedSentence1 = str1.TranslatedSentence1.replace("اليوم", "يوم");
		}
		System.out.println("Translated Sentence : " + str1.TranslatedSentence1);
		str1.sentencePrint();
		float JaccardThreshold = 0.85f;

		int lavveDiff = calculate(str1.TranslatedSentence1, PDFText);
		int maxLength = Math.max(str1.TranslatedSentence1.length(), PDFText.length());
		float ratio = (float) lavveDiff / maxLength;
		double jaccard = JaccardDistance.Jaccard2(PDFText, str1.TranslatedSentence1);
		System.out.println("Sentence Length: " + maxLength);
		System.out.println("LavenRatio : " + ratio);
		System.out.println("JaccardRatio : " + jaccard);

		if (ratio > 0.8f && jaccard > JaccardThreshold || jaccard == 1) {
			System.out.println("Changing Failed");
			PDFText = PDFText.replaceAll("في اكتوبر", "10");
			return text;

		} else {
			PDFText = PDFText.replaceAll("في اكتوبر", "10");
			node.setTextContent(PDFText);
			System.out.println("Changed Succusfully With : " + PDFText);
			List<String> stringList = new ArrayList<String>();
			Collections.addAll(stringList, PDFText, str1.TranslatedSentence1);
			JaccardDistance.JaccardWeight(stringList);
			System.out.println(JaccardDistance.weightMap);
			System.out.println("============================================================================");
			JaccardDistance.weightMap = new HashMap<String, Double>();

			return text;
		}

	}

}