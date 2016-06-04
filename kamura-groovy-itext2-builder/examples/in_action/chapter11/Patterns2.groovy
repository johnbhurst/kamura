@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Image
import com.lowagie.text.pdf.PatternColor
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.PdfPatternPainter

String resourcePath(String path) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter10/" + path
}

// Alternative version uses createPatternColor() mixins and Closures to paint patterns.
new IText2Builder(new FileOutputStream("build/examples/in_action/chapter11/Patterns2.pdf")).document() {
  PdfContentByte cb = writer.directContent
  PatternColor squares = cb.createPatternColor(15, 15) {square ->
    square.colorFill = new Color(0xFF, 0xFF, 0x00)
    square.colorStroke = new Color(0xFF, 0x00, 0x00)
    square.rectangle(5, 5, 5, 5)
    square.fillStroke()
  }
  PatternColor ellipses = cb.createPatternColor(15, 10, 20, 25) {ellipse ->
    ellipse.colorFill = new Color(0xFF, 0xFF, 0x00)
    ellipse.colorStroke = new Color(0xFF, 0x00, 0x00)
    ellipse.ellipse(2f, 2f, 13f, 8f)
    ellipse.fillStroke()
  }
  PatternColor circles = cb.createPatternColor(15, 15, 10, 20, Color.blue) {circle ->
    circle.circle(7.5f, 7.5f, 2.5f)
    circle.fill()
  }
  PatternColor lines = cb.createPatternColor(5, 10, null) {line ->
    line.lineWidth = 1
    line.moveTo(3, -1)
    line.lineTo(3, 11)
    line.stroke()
  }
  Image img = Image.getInstance(resourcePath("iTextLogo.gif"))
  PdfPatternPainter img_pattern = cb.createPattern(img.scaledWidth, img.scaledHeight, img.scaledWidth, img.scaledHeight)
  img_pattern.addImage(img, img.scaledWidth, 0f, 0f, img.scaledHeight, 0f, 0f)
  img_pattern.setPatternMatrix(1f, 0f, 0f, 1f, 60f, 60f)

  cb.setColorFill(squares)
  cb.rectangle(36, 716, 72, 72)
  cb.fillStroke()
  cb.setColorFill(ellipses)
  cb.rectangle(144, 716, 72, 72)
  cb.fillStroke()
  cb.setColorFill(circles)
  cb.rectangle(252, 716, 72, 72)
  cb.fillStroke()
  cb.setColorFill(lines)
  cb.rectangle(360, 716, 72, 72)
  cb.fillStroke()
  cb.setPatternFill(circles.painter, Color.red)
  cb.rectangle(470, 716, 72, 72)
  cb.fillStroke()

  cb.setPatternFill(lines.painter, Color.red)
  cb.rectangle(36, 608, 72, 72)
  cb.fillStroke()
  cb.setPatternFill(lines.painter, Color.green)
  cb.rectangle(144, 608, 72, 72)
  cb.fillStroke()
  cb.setPatternFill(lines.painter, Color.blue)
  cb.rectangle(252, 608, 72, 72)
  cb.fillStroke()
  cb.setPatternFill(lines.painter, Color.yellow)
  cb.rectangle(360, 608, 72, 72)
  cb.fillStroke()
  cb.setPatternFill(lines.painter, Color.black)
  cb.rectangle(470, 608, 72, 72)
  cb.fillStroke()

  cb.setPatternFill(img_pattern)
  cb.ellipse(36, 520, 360, 590)
  cb.fillStroke()
}
