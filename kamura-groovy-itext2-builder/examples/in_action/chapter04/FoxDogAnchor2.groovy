@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder
import com.lowagie.text.Chunk
import com.lowagie.text.Font
import com.lowagie.text.pdf.GrayColor

def RESULT= "build/examples/in_action/chapter04/FoxDogAnchor2.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  Font font = new Font(style: Font.UNDERLINE, color: new GrayColor(0.3f))
  paragraph("Quick brown ") {
    anchor(string: "fox", font: font, reference: "#fox")
    chunk(" jumps over the lazy ")
    anchor(string: "dog", font: font, reference: "#dog")
    chunk(".")
  }
  document.newPage()
  anchor("This is the FOX.", name: "fox")
  10.times {
    chunk(Chunk.NEWLINE)
  }
  anchor("This is the DOG.", name: "dog")
}
