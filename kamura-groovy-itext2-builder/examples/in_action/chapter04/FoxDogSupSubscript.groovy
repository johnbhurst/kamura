@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.pdf.PdfContentByte

def RESULT= "build/examples/in_action/chapter04/FoxDogSupSubscript.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  String s = "quick brown fox jumps over the lazy dog"
  StringTokenizer st = new StringTokenizer(s, " ")
  float textrise = 6.0
  while (st.hasMoreTokens()) {
    chunk(st.nextToken(), textRise: textrise, init: {c ->
      c.setUnderline(new Color(0xC0, 0xC0, 0xC0), 0.2f, 0.0f, 0.0f, 0.0f, PdfContentByte.LINE_CAP_BUTT)
    })
    textrise -= 2.0
  }
}
