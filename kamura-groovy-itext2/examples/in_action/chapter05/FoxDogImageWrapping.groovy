@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Image
import com.lowagie.text.Phrase

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageWrapping.pdf")).document() {
  Phrase p = new Phrase("Quick brown fox jumps over the lazy dog. ")
  image(imageLocation("foxdog.jpg"), alignment: Image.RIGHT | Image.TEXTWRAP)
  20.times {document.add(p)}
  image(imageLocation("foxdog.gif"), alignment: Image.MIDDLE | Image.UNDERLYING)
  30.times {document.add(p)}
}
