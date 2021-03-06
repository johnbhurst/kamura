@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Font
import com.lowagie.text.pdf.PdfAction

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter04/FoxDogGoto4.pdf")).document() {
  chunk(
    content: "Quick brown fox jumps over the lazy dog.",
    font: new Font(Font.UNDEFINED, Font.UNDEFINED, Font.UNDERLINE),
    action: new PdfAction("http://en.wikipedia.org/wiki/The_quick_brown_fox_jumps_over_the_lazy_dog")
  )
}
