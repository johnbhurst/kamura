@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import com.lowagie.text.Image

String resourcePath(String path) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter10/" + path
}

new PDFBuilder(new FileOutputStream("build/examples/in_action/chapter10/EyeInlineImage.pdf")).document() {
  Image eye = Image.getInstance(resourcePath("iTextLogo.gif"))
  eye.setAbsolutePosition(36, 780)
  writer.directContent.with {
    addImage(eye, true)
    addImage(eye, 271, -50, -30, 550, 100, 100, true)
  }
}
