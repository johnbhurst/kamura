@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import com.lowagie.text.Font
import java.awt.Color

def RESULT= "build/examples/in_action/chapter04/FoxDogColor.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  phrase {
    chunk(content: "quick brown fox", font: new Font(Font.COURIER, 10, Font.BOLD, new Color(0xFF, 0xFF, 0xFF)), background: new Color(0xa5, 0x2a, 0x2a))
    chunk(" jumps over ")
    chunk(content: "the lazy dog", font: new Font(Font.TIMES_ROMAN, 14, Font.ITALIC), init: {it.setBackground(new Color(0xFF, 0x00, 0x00), 10, -30, 20, -10)})
  }
}
