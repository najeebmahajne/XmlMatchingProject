

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
  
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
  
/**
* This is an example on how to get the x/y coordinates and size of each character in PDF
*/
public class GetCharLocationAndSize extends PDFTextStripper {
  
    public GetCharLocationAndSize() throws IOException {
    }
  
    /**
     * @throws IOException If there is an error parsing the document.
     */
    public static void main( String[] args ) throws IOException {
        PDDocument document = null;
        String fileName = "C:\\Users\\HP\\Desktop\\FINAL PROJECT 3RD YEAR\\PDF ARAB LAW BOOKS\\27877-2853-Done\\LawBooks\\2790\\2790.pdf";
        try {
            document = PDDocument.load( new File(fileName) );
            PDFTextStripper stripper = new GetCharLocationAndSize();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 1 );
            stripper.setEndPage( document.getNumberOfPages() );
  
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
        }
        finally {
            if( document != null ) {
                document.close();
            }
        }
    }
  
    /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode()+ " [(X=" + text.getXDirAdj() + ",Y=" +
                    text.getYDirAdj() + ") height=" + text.getHeightDir() + " width=" +
                    text.getWidthDirAdj() + "]");
        }
    }
}
