@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Element
import com.lowagie.text.Paragraph

def RESULT= "build/examples/in_action/chapter06/PdfPTableVerticalCells.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  table([1, 4] as float[], widthPercentage: 30) {
    cell(new Paragraph("fox"), padding: 4, backgroundColor: Color.YELLOW, horizontalAlignment: Element.ALIGN_CENTER, rotation: 90)
    cell("The fox is a red/brown animal that is very quick and that jumps over dogs.")
    cell(new Paragraph("dog"), padding: 4, backgroundColor: Color.YELLOW, horizontalAlignment: Element.ALIGN_CENTER, rotation: 90)
    cell("The dog in the sentence 'quick brown fox jumps over the lazy dog' is a rather lazy animal.")
  }
}
