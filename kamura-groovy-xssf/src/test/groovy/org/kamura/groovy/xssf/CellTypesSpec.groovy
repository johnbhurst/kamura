// Copyright 2016
// John Hurst (john.b.hurst@gmail.com)
// 2016-06-04

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

package org.kamura.groovy.xssf

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import spock.lang.Specification

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING

class CellTypesSpec extends Specification {

  @Lazy Workbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream("/cell_types.xlsx"))

  @Lazy Sheet sheet = workbook.getSheet("Types")

  Cell cellFor(String description) {
    for (int i = 0; i <= sheet.lastRowNum; i++) {
      Row row = sheet.getRow(i)
      Cell descriptionCell = row.getCell(0)
      if (descriptionCell.stringCellValue == description) {
        return row.getCell(1)
      }
    }
    throw new RuntimeException("No cell found for description [$description]")
  }

  def "Cell Types"() {
    when:
    Cell cell = cellFor(description)
    then:
    cell == null && type == null || cell.cellType == type
    where: "Cell descriptions and types are:"
    description             | type
    "Empty"                 | null              // How do we get CELL_TYPE_BLANK?
    "Blank string"          | CELL_TYPE_STRING
    "String"                | CELL_TYPE_STRING
    'String "true"'         | CELL_TYPE_STRING
    'String "false"'        | CELL_TYPE_STRING
    "String formula"        | CELL_TYPE_FORMULA
    "Boolean true"          | CELL_TYPE_BOOLEAN
    "Boolean false"         | CELL_TYPE_BOOLEAN
    "Boolean formula true"  | CELL_TYPE_FORMULA
    "Boolean formula false" | CELL_TYPE_FORMULA
    "Zero"                  | CELL_TYPE_NUMERIC
    "Integer"               | CELL_TYPE_NUMERIC
    "Float"                 | CELL_TYPE_NUMERIC
    "Integer formula"       | CELL_TYPE_FORMULA
    "Float formula"         | CELL_TYPE_FORMULA
    "Date"                  | CELL_TYPE_NUMERIC
    "Date time"             | CELL_TYPE_NUMERIC
    "Date formula"          | CELL_TYPE_FORMULA
    "Date time formula"     | CELL_TYPE_FORMULA
    "Error"                 | CELL_TYPE_FORMULA // How do we get CELL_TYPE_ERROR?
  }

}
