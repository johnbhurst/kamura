@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import com.lowagie.text.pdf.codec.GifImage

String imageLocation(String imageName) {
  System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName
}

new PDFBuilder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogAnimatedGif.pdf")).document() {
  paragraph("This is the animated gif added with Image.getInstance:")
  image(imageLocation("animated_fox_dog.gif"))
  GifImage img = new GifImage(imageLocation("animated_fox_dog.gif"))
  int frames = img.frameCount
  paragraph("There are $frames frames in the animated gif file.")
  for (i in 1..frames) {
    current.add(img.getImage(i))
  }
}
