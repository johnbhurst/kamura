// Copyright 2016
// John Hurst (john.b.hurst@gmail.com)
// 2016-03-30

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

package org.kamura.hssf

import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

class HSSFWorkbookBuilder {
  private Workbook workbook = new HSSFWorkbook()
  private Sheet sheet
  private int rows

  Workbook workbook(Closure closure) {
    closure.delegate = this
    closure.call()
    workbook
  }

  void sheet(String name, Closure closure) {
    sheet = workbook.createSheet(name)
    rows = 0
    closure.delegate = this
    closure.call()
  }

  void row(values) {
    Row row = sheet.createRow(rows++ as int)
    values.eachWithIndex {value, col ->
      Cell cell = row.createCell(col)
      switch (value) {
        case Date: cell.setCellValue((Date) value); break
        case Double: cell.setCellValue((Double) value); break
        case BigDecimal: cell.setCellValue(((BigDecimal) value).doubleValue()); break
        default: cell.setCellValue(new HSSFRichTextString("" + value)); break
      }
    }
  }

}
