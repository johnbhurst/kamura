@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Image

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageRectangle.pdf")).document() {
  image(imageLocation("foxdog.jpg"), border: Image.BOX, borderColor: new Color(0xFF, 0x00, 0x00), borderWidth: 5)
}
