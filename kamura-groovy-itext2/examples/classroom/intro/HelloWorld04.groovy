@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.pdf.PdfWriter

def RESULT = "build/examples/classroom/intro/hello04.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document(
  initWriter: {setEncryption("hello".bytes, "world".bytes, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128)}
) {
  paragraph("Hello World.")
}
