@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import java.awt.Graphics2D
import static com.lowagie.text.PageSize.A4

def RESULT = "build/examples/classroom/intro/hello11.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  writer.directContent.with {cb ->
    Graphics2D graphics2D = cb.createGraphicsShapes(A4.width, A4.height)
    graphics2D.drawString("Hello World", 36, 54)
    graphics2D.dispose()
  }
}
