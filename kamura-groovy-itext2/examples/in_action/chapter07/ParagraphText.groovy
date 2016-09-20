@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.PageSize
import com.lowagie.text.Element

def RESULT= "build/examples/in_action/chapter07/ParagraphText.pdf"
def INPUT = System.getProperty("itext.examples.home") + "/resources/in_action/chapter07/caesar.txt"
new IText2Builder(new FileOutputStream(RESULT)).document(PageSize.A4) {
  new File(INPUT).eachLine {line ->
    paragraph("    $line", alignment: Element.ALIGN_JUSTIFIED)
  }
}
