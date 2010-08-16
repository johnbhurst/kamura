package nz.co.skepticalhumorist.pdfbuilder

import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.pdf.PdfContentByte
import java.awt.Graphics2D
import com.lowagie.text.pdf.FontMapper

class PdfCategory {
  static void withDirectContent(PdfWriter writer, Closure closure) {
    doWithContentByte(writer.directContent, closure)
  }

  static void withDirectContentUnder(PdfWriter writer, Closure closure) {
    doWithContentByte(writer.directContentUnder, closure)
  }

  private static void doWithContentByte(PdfContentByte cb, Closure closure) {
    cb.saveState()
    try {
      closure.call(cb)
    }
    finally {
      cb.restoreState()
    }
  }

  static void withGraphics(PdfContentByte cb, float width, float height, Closure closure) {
    doWithGraphics(cb, cb.createGraphics(width, height), closure)
  }

  static void withGraphics(PdfContentByte cb, float width, float height, FontMapper fontMapper, Closure closure) {
    doWithGraphics(cb, cb.createGraphics(width, height, fontMapper), closure)
  }

  static void withGraphics(PdfContentByte cb, float width, float height, boolean convertImagesToJPEG, float quality, Closure closure) {
    doWithGraphics(cb, cb.createGraphics(width, height, convertImagesToJPEG, quality), closure)
  }

  static void withGraphics(PdfContentByte cb, float width, float height, FontMapper fontMapper, boolean convertImagesToJPEG, float quality, Closure closure) {
    doWithGraphics(cb, cb.createGraphics(width, height, fontMapper, convertImagesToJPEG, quality), closure)
  }

  static void withGraphicsShapes(PdfContentByte cb, float width, float height, Closure closure) {
    doWithGraphics(cb, cb.createGraphicsShapes(width, height), closure)
  }

  static void withGraphicsShapes(PdfContentByte cb, float width, float height, boolean convertImagesToJPEG, float quality, Closure closure) {
    doWithGraphics(cb, cb.createGraphicsShapes(width, height, convertImagesToJPEG, quality), closure)
  }

  private static void doWithGraphics(PdfContentByte cb, Graphics2D graphics, Closure closure) {
    try {
      if (closure.maximumNumberOfParameters > 1) {
        closure.call(cb, graphics)
      }
      else {
        closure.call(graphics)
      }
    }
    finally {
      graphics.dispose()
    }
  }

  // tentative ... either this or perhaps withDirectContentWithText() ?
  static void withText(PdfContentByte cb, Closure closure) {
    cb.beginText()
    try {
      closure.call(cb)
    }
    finally {
      cb.endText()
    }
  }
}
