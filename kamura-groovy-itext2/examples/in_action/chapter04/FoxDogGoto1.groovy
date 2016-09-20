@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Font

def RESULT= "build/examples/in_action/chapter04/FoxDogGoto1.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  Font font = new Font()
  font.style = Font.UNDERLINE
  chunk(
    content: "Quick brown fox jumps over the lazy dog.",
    font: font,
    anchor: "http://en.wikipedia.org/wiki/The_quick_brown_fox_jumps_over_the_lazy_dog"
  )
}
