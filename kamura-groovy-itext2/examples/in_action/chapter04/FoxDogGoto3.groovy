@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Font
import com.lowagie.text.FontFactory

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter04/FoxDogGoto3.pdf")).document() {
  paragraph("The Quick brown fox wants to") {
    chunk(content: " jump over ", font: FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 255)), localGoto: "jump")
    chunk("the lazy dog.")
  }
  7.times {paragraph("blah, blah, blah")}
  paragraph("The fox") {
    chunk(content: " has jumped ", font: FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 255, 0)), localDestination: "jump")
    chunk("over the lazy dog")
  }
}
