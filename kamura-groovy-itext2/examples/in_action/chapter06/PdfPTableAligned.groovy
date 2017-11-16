@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Paragraph
import com.lowagie.text.Element

def RESULT= "build/examples/in_action/chapter06/PdfPTableAligned.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  def table = table(3, widthPercentage: 100) {
    cell(new Paragraph("header with colspan 3"), colspan: 3)
    cell("1.1"); cell("2.1"); cell("3.1")
    cell("1.2"); cell("2.2"); cell("3.2")
  }
  table.widthPercentage = 50
  table.horizontalAlignment = Element.ALIGN_RIGHT
  current.add(table)
  table.horizontalAlignment = Element.ALIGN_LEFT
  current.add(table)
}
