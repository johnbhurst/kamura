@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Font
import com.lowagie.text.pdf.GrayColor

def RESULT= "build/examples/in_action/chapter04/FoxDogAnchor1.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  anchor(
    string: "Quick brown fox jumps over the lazy dog.",
    font: new Font(Font.UNDEFINED, Font.UNDEFINED, Font.UNDERLINE, new GrayColor(0.3f)),
    reference: "http://en.wikipedia.org/wiki/The_quick_brown_fox_jumps_over_the_lazy_dog"
  )
}
