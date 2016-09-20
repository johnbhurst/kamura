@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Chunk

def RESULT= "build/examples/in_action/chapter04/FoxDogScale.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  Chunk c = new Chunk("quick brown fox jumps over the lazy dog")
  float w = c.widthPoint
  paragraph("The width of the chunk: '") {
    current.add(c)
    chunk("' is $w points or ${w/72f as float} inches or ${w/72f*2.54f as float} cm.")
  }
  current.add(c)
  chunk(Chunk.NEWLINE)
  c.horizontalScaling = 0.5f
  current.add(c)
  current.add(c)
}
