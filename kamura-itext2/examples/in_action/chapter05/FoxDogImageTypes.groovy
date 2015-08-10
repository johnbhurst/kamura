@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder

new PDFBuilder(new FileOutputStream("build/examples/in_action/chapter05/FoxDogImageTypes.pdf")).document() {
  def myimage = {String imageName ->
    image(System.properties["itext.examples.home"] + "/resources/in_action/chapter05/" + imageName)
  }
  paragraph("foxdog.jpg")
  myimage("foxdog.jpg")
  paragraph("foxdog.gif")
  myimage("foxdog.gif")
  paragraph("foxdog.png")
  myimage("foxdog.png")
  document.newPage()
  paragraph("foxdog.tiff")
  myimage("foxdog.tiff")
  paragraph("foxdog.wmf")
  myimage("foxdog.wmf")
  paragraph("foxdog.bmp")
  myimage("foxdog.bmp")
}
