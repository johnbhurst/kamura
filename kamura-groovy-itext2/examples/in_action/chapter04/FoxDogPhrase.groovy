@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Chunk
import com.lowagie.text.Font
import com.lowagie.text.pdf.PdfContentByte

def RESULT= "build/examples/in_action/chapter04/FoxDogPhrase.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  Font font = new Font(Font.COURIER, 10, Font.BOLD)
  font.color = new Color(0xFF, 0xFF, 0xFF)
  def background = new Color(0xa5, 0x2a, 0x2a)
  float superscript = 8.0f
  float subscript = -8.0f

  def chunks = {
    chunk(content: "Quick brown fox", font: font, textRise: superscript, background: background)
    chunk(content: " jumps over ", font: new Font())
    chunk(content: "the lazy dog.", font: new Font(Font.TIMES_ROMAN, 14, Font.ITALIC), textRise: subscript) {
      current.setUnderline(new Color(0xFF, 0x00, 0x00), 3.0f, 0.0f, -5.0f + subscript as float, 0.0f, PdfContentByte.LINE_CAP_ROUND)
    }
    chunk(" ")
  }

  10.times {
    phrase(30) {
      chunks()
    }
  }
  chunk(Chunk.NEWLINE)
  phrase(30) {
    chunks()
  }
  3.times {
    phrase(30) {
      chunks()
      current.add("\n")
    }
  }

}
