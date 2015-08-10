@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder

def RESULT = "build/examples/classroom/intro/hello01.pdf"
new PDFBuilder(new FileOutputStream(RESULT)).document() {
  paragraph("Hello World.")
}
