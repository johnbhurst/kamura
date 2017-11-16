// Copyright 2017- John Hurst
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

@Grab("com.lowagie:itext:2.1.7")
@Grab("org.apache.poi:poi:3.17")
@Grab("org.apache.poi:poi-ooxml:3.17")

@Grab("nz.kamura:kamura-groovy-itext2:0.0.2")
@Grab("nz.kamura:kamura-groovy-xssf:0.0.2")
@Grab("nz.kamura:kamura-groovy-zip:0.0.2")

import java.awt.Color
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.lowagie.text.Element
import com.lowagie.text.Font
import com.lowagie.text.Paragraph

import nz.kamura.groovy.xssf.WorkbookReader
import nz.kamura.groovy.itext2.IText2Builder
import nz.kamura.groovy.zip.ZipBuilder

Font HEADER_FONT = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.BLACK)
Font TH_FONT = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK)
Font TD_FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK)

NumberFormat Q_FORMAT = new DecimalFormat("#,##0")
NumberFormat P_FORMAT = new DecimalFormat("\$#,##0.00")

String inputFileName = args[0]
String outputFileName = inputFileName.replace("xlsx", "zip")

new File(inputFileName).withInputStream { is ->
  Workbook workbook = new XSSFWorkbook(is)
  new File(outputFileName).withOutputStream { zos ->
    new ZipBuilder(zos).zip {
      WorkbookReader.each(workbook.getSheet("index")) { String invoiceNumber, LocalDate invoiceDate, String customer ->
        println invoiceNumber
        Sheet sheet = workbook.getSheet(invoiceNumber)
        entry("${invoiceNumber}.pdf") { os ->
          new IText2Builder(os).document {
            def header = { String text -> paragraph(string: text, font: HEADER_FONT, alignment: Element.ALIGN_RIGHT, spacingAfter: -4f)}
            def hcell = { String text -> cell(new Paragraph(text, TH_FONT), horizontalAlignment: Element.ALIGN_CENTER)}
            def lcell = { String text -> cell(new Paragraph(text, TD_FONT), horizontalAlignment: Element.ALIGN_LEFT)}
            def rcell = { String text -> cell(new Paragraph(text, TD_FONT), horizontalAlignment: Element.ALIGN_RIGHT)}

            header("Invoice Number: " + invoiceNumber)
            header("Invoice Date: " + invoiceDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")))
            header(customer)
            header(" ")
            BigDecimal totalAmount = 0
            table([0.4f, 0.3f, 0.3f] as float[], widthPercentage: 100) {
              hcell("Description") ; hcell("Quantity") ; hcell("Price")
              WorkbookReader.each(sheet) { int id, String description, int quantity, BigDecimal price ->
                lcell(description) ; rcell(Q_FORMAT.format(quantity)) ; rcell(P_FORMAT.format(price))
                totalAmount += quantity * price
              }
            }
            header(" ")
            header("Total Amount Due: " + P_FORMAT.format(totalAmount))
          }
        }
      }
    }
  }
}
