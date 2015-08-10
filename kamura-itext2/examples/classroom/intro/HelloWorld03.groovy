@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.IText2Builder

def RESULT = "build/examples/classroom/intro/hello03.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document(
  initWriter: {setFullCompression()}
) {
  paragraph("Hello World.")
}
