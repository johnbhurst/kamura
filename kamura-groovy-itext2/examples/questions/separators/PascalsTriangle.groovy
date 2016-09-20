@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.pdf.draw.VerticalPositionMark
import com.lowagie.text.PageSize;

pascal = [:].withDefault {i ->
  [:].withDefault {j ->
    if (i == 0 || i == 1) 1
    else if (j == 0 || j == i) 1
    else pascal[i-1][j-1] + pascal[i-1][j]
  }
}
def vp = new VerticalPositionMark()

def RESULT = "build/examples/questions/separators/PascalsTriangle.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document(pageSize: PageSize.A4.rotate()) {
  for (i in 0..20) {
    paragraph("") {
      chunk(vp)
      for (j in 0..i) {
        chunk("${pascal[i][j]}")
        chunk(vp)
      }
    }
  }
}
