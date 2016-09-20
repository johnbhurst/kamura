@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Image

def RESULT= "build/examples/in_action/chapter06/PdfPTableImages.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  table(1) {
    def table = current
    Image img = Image.getInstance(System.getProperty("itext.examples.home") + "/resources/in_action/chapter06/foxdog.jpg")
    cell("This image was added with addCell(Image); the image is scaled + there is the default padding of getDefaultCell.")
    table.addCell(img);
    cell("This image was added with addCell(PdfPCell); scaled, no padding")
    cell(image: img, fit: true)
    cell("This image was added with addCell(PdfPCell); not scaled")
    cell(image: img, fit: false)
  }
}
