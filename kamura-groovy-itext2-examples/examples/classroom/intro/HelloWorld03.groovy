@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder

def RESULT = "build/examples/classroom/intro/hello03.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document(
  initWriter: {setFullCompression()}
) {
  paragraph("Hello World.")
}
