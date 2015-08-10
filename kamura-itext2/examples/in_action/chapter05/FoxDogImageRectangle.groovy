@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import java.awt.Color
import com.lowagie.text.Image

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new PDFBuilder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageRectangle.pdf")).document() {
  image(imageLocation("foxdog.jpg"), border: Image.BOX, borderColor: new Color(0xFF, 0x00, 0x00), borderWidth: 5)
}
