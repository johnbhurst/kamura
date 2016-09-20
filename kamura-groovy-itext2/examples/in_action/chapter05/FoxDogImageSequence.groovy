@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Image

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

// TODO: figure out a way to avoid this duplicate code?
new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageNotInSequence.pdf")).document() {
  Image jpg = Image.getInstance(imageLocation("foxdog.jpg"))
  Image gif = Image.getInstance(imageLocation("dog.gif"))
  paragraph("image 1")
  document.add(jpg)
  paragraph("image 2")
  document.add(gif)
  paragraph("image 3")
  document.add(jpg)
  paragraph("image 4")
  document.add(gif)
  paragraph("image 5")
  document.add(jpg)
  paragraph("image 6")
  document.add(gif)
}

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageSequence.pdf")).document(
  initWriter: {setStrictImageSequence(true)}) {
  Image jpg = Image.getInstance(imageLocation("foxdog.jpg"))
  Image gif = Image.getInstance(imageLocation("dog.gif"))
  paragraph("image 1")
  document.add(jpg)
  paragraph("image 2")
  document.add(gif)
  paragraph("image 3")
  document.add(jpg)
  paragraph("image 4")
  document.add(gif)
  paragraph("image 5")
  document.add(jpg)
  paragraph("image 6")
  document.add(gif)
}
