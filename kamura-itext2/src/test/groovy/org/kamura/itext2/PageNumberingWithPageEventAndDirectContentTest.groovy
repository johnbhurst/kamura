// Copyright 2014- John Hurst
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.kamura.itext2

import org.junit.Test
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfDocument
import com.lowagie.text.pdf.PdfWriter
import de.oio.jpdfunit.document.util.TextSearchType

class PageNumberingWithPageEventAndDirectContentTest extends AbstractPDFBuilderTestCase {

  BaseFont font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED)

  @Test
  public void testOk() {
    defaultBuilder.document(
      onEndPage: {PdfWriter writer, PdfDocument document ->
        writer.directContent.withText {PdfContentByte cb ->
          cb.setFontAndSize(font, 12)
          cb.setTextMatrix(document.left() as float, document.bottom() - 20 as float)
          cb.showText("Page " + writer.getPageNumber())
        }
      }
    ) {
      100.times {i ->
        paragraph("Paragraph $i.")
      }
    }
    defaultTester.with {
      assertContentContainsTextOnPage("Page 1", 1, TextSearchType.CONTAINS)
      assertContentContainsTextOnPage("Page 2", 2, TextSearchType.CONTAINS)
    }
  }
}
