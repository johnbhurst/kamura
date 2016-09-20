@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.Element

def RESULT = "build/examples/classroom/intro/hello07.pdf"
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.withText {cb ->
    cb.setFontAndSize(bf, 12)
    cb.showTextAligned(Element.ALIGN_LEFT, "Hello World", 36, 788, 0)
  }
}
