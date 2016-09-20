@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder
import java.awt.Color
import com.lowagie.text.Chunk
import com.lowagie.text.Element
import com.lowagie.text.Font
import com.lowagie.text.FontFactory
import com.lowagie.text.Image
import com.lowagie.text.List as ITextList
import com.lowagie.text.ListItem
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.ColumnText
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.PdfPTable

def FONT9 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9)
def FONT11 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)
def FONT11B = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)
def FONT14B = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)
def FONT14BC = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, new Color(255, 0, 0))
def FONT24B = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, Font.BOLD)

static Paragraph newParagraph(String s, Font f, float spacingBefore) {
  Paragraph p = new Paragraph(s, f)
  p.alignment = Element.ALIGN_JUSTIFIED
  p.spacingBefore = spacingBefore
  p
}

def RESULT= "build/examples/in_action/chapter07/ColumnWithAddElement.pdf"
new IText2Builder(new FileOutputStream(RESULT)).document(PageSize.A4) {
  float gutter = 20
  int numColumns = 2
  float fullWidth = document.right() - document.left()
  float columnWidth = (fullWidth - (numColumns - 1) * gutter) / numColumns
  def allColumns = [
    document.left(),
    document.left() + columnWidth + gutter as float
  ]
  PdfContentByte cb = writer.directContent
  ColumnText ct = new ColumnText(cb)
  ct.setLeading(0, 1.5f)
  ct.setSimpleColumn(document.left(), 0, document.right(), document.top())
  Phrase fullTitle = new Phrase("POJOs in Action", FONT24B)
  ct.addText(fullTitle)
  ct.go()
  Phrase subTitle = new Phrase("Developing Enterprise Applications with Lightweight Frameworks", FONT14B)
  ct.addText(subTitle)
  ct.go()
  float currentY = ct.YLine
  currentY -= 4
  cb.lineWidth = 1
  cb.moveTo(document.left(), currentY)
  cb.lineTo(document.right(), currentY)
  cb.stroke()
  ct.YLine = currentY
  ct.addText(new Chunk("Chris Richardson", FONT14B))
  ct.go()
  currentY = ct.YLine - 15 as float
  float topColumn = currentY
  for (k in 1..1) {
    float x = allColumns[k] - gutter / 2 as float
    cb.moveTo(x, topColumn)
    cb.lineTo(x, document.bottom())
  }
  cb.stroke()
  int currentColumn = 0
  ct.setSimpleColumn(allColumns[currentColumn], document.bottom(), allColumns[currentColumn] + columnWidth as float, currentY)
  Image img = Image.getInstance(System.getProperty("itext.examples.home") + "/resources/in_action/chapter07/8001.jpg")
  ct.addElement(img)
  ct.addElement(newParagraph("Key Data:", FONT14BC, 5))
  PdfPTable ptable = new PdfPTable(2)
  ptable.widths = [1, 2] as float[]
  ptable.defaultCell.paddingLeft = 4
  ptable.defaultCell.paddingTop = 0
  ptable.defaultCell.paddingBottom = 4
  ptable.addCell(new Phrase("Publisher:", FONT9))
  ptable.addCell(new Phrase("Manning Publications Co.", FONT9))
  ptable.addCell(new Phrase("ISBN:", FONT9))
  ptable.addCell(new Phrase("1932394583", FONT9))
  ptable.addCell(new Phrase("Price:", FONT9))
  ptable.addCell(new Phrase("\$44.95", FONT9))
  ptable.addCell(new Phrase("Page Count:", FONT9))
  ptable.addCell(new Phrase("450", FONT9))
  ptable.addCell(new Phrase("Pub Date:", FONT9))
  ptable.addCell(new Phrase("2005", FONT9))
  ptable.spacingBefore = 5
  ptable.widthPercentage = 100
  ct.addElement(ptable)
  ct.addElement(newParagraph("Description", FONT14BC, 15))
  ct.addElement(newParagraph(
          "In the past, developers built enterprise Java applications using EJB technologies that are excessively complex and difficult to use. Often EJB introduced more problems than it solved. There is a major trend in the industry towards using simpler and easier technologies such as Hibernate, Spring, JDO, iBATIS and others, all of which allow the developer to work directly with the simpler Plain Old Java Objects or POJOs. Now EJB version 3 solves the problems that gave EJB 2 a black eye--it too works with POJOs.",
          FONT11, 5))
  Paragraph p = new Paragraph()
  p.spacingBefore = 5
  p.alignment = Element.ALIGN_JUSTIFIED
  Chunk anchor = new Chunk("POJOs in Action", FONT11B)
  anchor.setAnchor("http://www.manning.com/books/crichardson")
  p.add(anchor)
  p.add(new Phrase(
          " describes the new, easier ways to develop enterprise Java applications. It describes how to make key design decisions when developing business logic using POJOs, including how to organize and encapsulate the business logic, access the database, manage transactions, and handle database concurrency. This book is a new-generation Java applications guide: it enables readers to successfully build lightweight applications that are easier to develop, test, and maintain.",
          FONT11))
  ct.addElement(p)
  ct.addElement(newParagraph("Inside the Book", FONT14BC, 15))
  ITextList list = new ITextList(List.UNORDERED, 15)
  ListItem li
  li = new ListItem("How to develop apps in the post EJB 2 world", FONT11)
  list.add(li)
  li = new ListItem("How to leverage the strengths and work around the weaknesses of: JDO, Hibernate, and EJB 3", FONT11)
  list.add(li)
  li = new ListItem("How to benefit by using aspects", FONT11)
  list.add(li)
  li = new ListItem("How to do test-driven development with lightweight frameworks", FONT11)
  list.add(li)
  li = new ListItem("How to accelerate the edit-compile-debug cycle", FONT11)
  list.add(li)
  ct.addElement(list)
  ct.addElement(newParagraph("About the Author...", FONT14BC, 15))
  ct.addElement(newParagraph(
          "Chris Richardson is a developer, architect and mentor with over 20 years of experience. He runs a consulting company that jumpstarts new development projects and helps teams that are frustrated with enterprise Java become more productive and successful. Chris has been a technical leader at a variety of companies including Insignia Solutions and BEA Systems. Chris holds a MA & BA in Computer Science from the University of Cambridge in England. He lives in Oakland, CA.",
          FONT11, 15))
  while (true) {
    int status = ct.go()
    if (!ColumnText.hasMoreText(status))
      break;
    // we run out of column. Let's go to another one
    ++currentColumn
    if (currentColumn >= allColumns.size()) {
      break
    }
    ct.setSimpleColumn(allColumns[currentColumn], document.bottom(), allColumns[currentColumn] + columnWidth as float, topColumn)
  }
}
