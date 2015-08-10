@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import java.awt.Graphics2D
import static com.lowagie.text.PageSize.A4

def RESULT = "build/examples/classroom/intro/hello10.pdf"
new PDFBuilder(new FileOutputStream(RESULT)).document() {
  writer.directContent.with {cb ->
    Graphics2D graphics2D = cb.createGraphics(A4.width, A4.height)
    graphics2D.drawString("Hello World", 36, 54)
    graphics2D.dispose()
  }
}
