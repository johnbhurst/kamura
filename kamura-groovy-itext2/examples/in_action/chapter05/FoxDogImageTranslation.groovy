@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Image

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageTranslation.pdf")).document() {
  Image img = Image.getInstance(imageLocation("foxdog.jpg"))
  img.setAbsolutePosition(50, 600)
  document.add(img)
  80.times {phrase("Quick brown fox jumps over the lazy dog. ")}
  img.setAbsolutePosition(50, 300)
  document.add(img)
}
