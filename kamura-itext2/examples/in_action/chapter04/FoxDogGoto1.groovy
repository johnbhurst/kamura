@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import com.lowagie.text.Font

def RESULT= "build/examples/in_action/chapter04/FoxDogGoto1.pdf"
new PDFBuilder(new FileOutputStream(RESULT)).document() {
  Font font = new Font()
  font.style = Font.UNDERLINE
  chunk(
    content: "Quick brown fox jumps over the lazy dog.",
    font: font,
    anchor: "http://en.wikipedia.org/wiki/The_quick_brown_fox_jumps_over_the_lazy_dog"
  )
}
