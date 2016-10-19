@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Paragraph

def RESULT= "build/examples/in_action/chapter06/PdfPTableMemoryFriendly.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  table(2, widthPercentage: 100, headerRows: 1) {
    cell(new Paragraph("Header 1"), grayFill: 0.7)
    cell(new Paragraph("Header 2"), grayFill: 0.7)
    for (row in 1..2000) {
      if (row % 50 == 50 - 1) {
        document.add(current)
        current.deleteBodyRows()
        current.skipFirstHeader = true
      }
      cell(new Paragraph("$row"))
      cell(new Paragraph("Quick brown fox jumps over the lazy dog."))
    }
  }
}
