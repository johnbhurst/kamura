@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import nz.kamura.groovy.itext2.PdfCategory
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfTemplate

def RESULT = "build/examples/classroom/intro/hello08.pdf"
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.withText {cb ->
    cb.setFontAndSize(bf, 12)
    cb.moveText(88.66f, 788)
    cb.showText("ld")
    cb.moveText(-22f, 0)
    cb.showText("Wor")
    cb.moveText(-15.33f, 0)
    cb.showText("llo")
  }
  writer.directContent.addTemplate(250, 25, 36, 781) {tmp ->
    tmp.withText {cb ->
      cb.setFontAndSize(bf, 12)
      cb.moveText(0, 7)
      cb.showText("He")
    }
  }
}
