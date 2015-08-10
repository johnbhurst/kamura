@Grab("org.kamura:kamura-itext2:latest.release")
import org.kamura.itext2.PDFBuilder
import com.lowagie.text.Document

def RESULT = "build/examples/classroom/intro/hello02.pdf"
Document.compress = false
new PDFBuilder(new FileOutputStream(RESULT)).document() {
  paragraph("Hello World.")
}
