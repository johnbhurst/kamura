@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import java.awt.Graphics2D
import com.lowagie.text.pdf.PdfContentByte
import static com.lowagie.text.PageSize.A4

def RESULT = "build/examples/classroom/intro/hello10a.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.with {PdfContentByte cb ->
    cb.withGraphics(A4.width, A4.height) {Graphics2D graphics ->
      graphics.drawString("Hello World", 36, 54)
    }
  }
}
