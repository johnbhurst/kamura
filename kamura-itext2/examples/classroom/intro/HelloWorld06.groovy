@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import com.lowagie.text.pdf.BaseFont

def RESULT = "build/examples/classroom/intro/hello06.pdf"
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
new PDFBuilder(new FileOutputStream(RESULT)).document() {
  writer.directContent.withText {cb ->
    cb.setTextMatrix(36, 788)
    cb.setFontAndSize(bf, 12)
    cb.showText("Hello World")
  }
}
