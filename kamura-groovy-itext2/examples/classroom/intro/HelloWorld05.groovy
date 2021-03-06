@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.BaseFont

def RESULT = "build/examples/classroom/intro/hello05.pdf"
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.withText {cb -> // BT
    cb.moveText(36, 806) // 36 806 Td
    cb.moveText(0, -18) // 0 -18 Td
    cb.setFontAndSize(bf, 12) // /F1 12 Tf
    cb.showText("Hello World") // (Hello World)Tj
  }
}
