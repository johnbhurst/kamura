@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Element
import com.lowagie.text.PageSize
import java.awt.Color

/**
 * This example repeats the one shown in MultiColumnIrregular, but using the onEndPage event to
 * draw the graphics on each page.
 * This way the text part is done very simply and naturally using the "normal" PDFBuilder way
 * of adding elements, rather than having to call document.add(mct) and mct.nextColumn()
 * in a loop.
 */
def RESULT= "build/examples/in_action/chapter07/MultiColumnIrregular2.pdf"
def INPUT = System.getProperty("itext.examples.home") + "/resources/in_action/chapter07/caesar.txt"
float diamondHeight = 400
float diamondWidth = 400
new IText2Builder(new FileOutputStream(RESULT)).document(PageSize.A4, onEndPage: { writer, document ->
  float centerX = (document.right() - document.left()) / 2 + document.left() as float
  float bodyHeight = document.top() - document.bottom() as float
  float diamondTop = document.top() - ((bodyHeight - diamondHeight) / 2f) as float
  writer.directContent.with {
    lineWidth = 5
    colorStroke = Color.GRAY
    moveTo(centerX, document.top())
    lineTo(centerX, document.bottom())
    stroke()
    moveTo(centerX, diamondTop)
    lineTo(centerX - (diamondWidth / 2) as float, diamondTop - (diamondHeight / 2) as float)
    lineTo(centerX, diamondTop - diamondHeight as float)
    lineTo(centerX + (diamondWidth / 2) as float, diamondTop - (diamondHeight / 2) as float)
    lineTo(centerX, diamondTop)
    colorFill = Color.GRAY
    fill()
  }
}) {
  multiColumnText(alignment: Element.ALIGN_JUSTIFIED, init: {mct ->
    float gutter = 10
    float bodyHeight = document.top() - document.bottom() as float
    float colMaxWidth = (document.right() - document.left() - (gutter * 2)) / 2f as float
    float diamondTop = document.top() - ((bodyHeight - diamondHeight) / 2f) as float
    float diamondInset = colMaxWidth - (diamondWidth / 2f) as float
    // setup column 1
    def left = [
      document.left(),
      document.top(),
      document.left(),
      document.bottom()
    ] as float[]
    def right = [
      document.left() + colMaxWidth,
      document.top(),
      document.left() + colMaxWidth,
      diamondTop,
      document.left() + diamondInset,
      diamondTop - diamondHeight / 2,
      document.left() + colMaxWidth,
      diamondTop - diamondHeight,
      document.left() + colMaxWidth,
      document.bottom()
    ] as float[]
    mct.addColumn(left, right)
    // setup column 2
    left = [
      document.right() - colMaxWidth,
      document.top(),
      document.right() - colMaxWidth,
      diamondTop,
      document.right() - diamondInset,
      diamondTop - diamondHeight / 2,
      document.right() - colMaxWidth,
      diamondTop - diamondHeight,
      document.right() - colMaxWidth,
      document.bottom()
    ] as float[]
    right = [
      document.right(),
      document.top(),
      document.right(),
      document.bottom()
    ] as float[]
    mct.addColumn(left, right)
  }) {
    new File(INPUT).eachLine {line ->
      phrase("$line\n")
    }
  }
}
