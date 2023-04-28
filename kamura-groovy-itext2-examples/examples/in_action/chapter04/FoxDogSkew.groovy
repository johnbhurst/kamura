@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Chunk

def RESULT= "build/examples/in_action/chapter04/FoxDogSkew.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  paragraph {
    chunk("Quick brown fox", init: {it.setSkew(15, -30)})
    chunk(" jumps over ", init: {it.setSkew(15, 15)})
    chunk("the lazy dog.", init: {it.setSkew(-30, 15)})
  }
  3.times {chunk(Chunk.NEWLINE)}
  paragraph {
    chunk("Quick brown fox", init: {it.setSkew(45, 0)})
    chunk(" jumps over ")
    chunk("the lazy dog.", init: {it.setSkew(-45, 0)})
  }
  3.times {chunk(Chunk.NEWLINE)}
  paragraph {
    chunk("Quick brown fox", init: {it.setSkew(0, 25)})
    chunk(" jumps over ")
    chunk("the lazy dog.", init: {it.setSkew(0, -25)})
  }
}
