@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Paragraph

def RESULT= "build/examples/in_action/chapter06/MyFirstPdfPTable.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  table(3) {
    cell(new Paragraph("header with colspan 3"), colspan: 3)
    cell("1.1"); cell("2.1"); cell("3.1")
    cell("1.2"); cell("2.2"); cell("3.2")
  }
}
