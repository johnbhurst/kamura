@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Image

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageChunk.pdf")).document() {
  paragraph("Quick brown ") {
    chunk(image: Image.getInstance(imageLocation("fox.gif")), offsetX: 0, offsetY: -15)
    chunk(" jumps over the lazy ")
    chunk(image: Image.getInstance(imageLocation("dog.gif")), offsetX: 0, offsetY: -15)
    chunk(".")
  }
}
