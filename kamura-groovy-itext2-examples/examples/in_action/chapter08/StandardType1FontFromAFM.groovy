@Grab("nz.kamura:kamura-groovy-itext2:latest.release")
import nz.kamura.groovy.itext2.IText2Builder
import com.lowagie.text.Font
import com.lowagie.text.pdf.BaseFont

new IText2Builder(new FileOutputStream("build/examples/in_action/chapter08/StandardType1FontFromAFM.pdf")).document() {
  BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED)
  System.out.println(bf.class.name)
  Font font = new Font(bf, 12)
  paragraph(string: "0123456789\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXZ", font: font)
}
