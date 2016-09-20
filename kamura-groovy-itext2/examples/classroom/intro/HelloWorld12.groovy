@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder

def RESULT = "build/examples/classroom/intro/hello12.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document() {
  image(filename: "examples/classroom/intro/hello.png", init: { it.scaleToFit(595, 842); it.setAbsolutePosition(0, 0) })
}
