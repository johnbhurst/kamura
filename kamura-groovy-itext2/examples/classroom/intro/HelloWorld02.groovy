@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Document

def RESULT = "build/examples/classroom/intro/hello02.pdf"
Document.compress = false
new IText2Builder(new FileOutputStream(RESULT)).document() {
  paragraph("Hello World.")
}
