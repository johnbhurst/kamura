@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.pdf.BaseFont

def RESULT = "build/examples/classroom/intro/hello06.pdf"
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.withText {cb ->
    cb.setTextMatrix(36, 788)
    cb.setFontAndSize(bf, 12)
    cb.showText("Hello World")
  }
}
