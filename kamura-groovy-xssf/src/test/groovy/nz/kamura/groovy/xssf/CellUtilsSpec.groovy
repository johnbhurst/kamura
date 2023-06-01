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

package nz.kamura.groovy.xssf

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import spock.lang.Specification

class CellUtilsSpec extends Specification {

  @Lazy XSSFWorkbook workbook = new XSSFWorkbook(this.getClass().getResourceAsStream("/cell_types.xlsx"))

  @Lazy XSSFSheet sheet = workbook.getSheet("Types")

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
    cachedFormulaResultType == null || cell.cachedFormulaResultType == cachedFormulaResultType
    where: "Cell descriptions and types are:"
    description             | type              | cachedFormulaResultType
    "Empty"                 | null              | null // How do we get CELL_TYPE_BLANK?
    "Blank string"          | CellType.STRING  | null
    "String"                | CellType.STRING  | null
    'String "true"'         | CellType.STRING  | null
    'String "false"'        | CellType.STRING  | null
    "String formula"        | CellType.FORMULA | CellType.STRING
    "Boolean true"          | CellType.BOOLEAN | null
    "Boolean false"         | CellType.BOOLEAN | null
    "Boolean formula true"  | CellType.FORMULA | CellType.BOOLEAN
    "Boolean formula false" | CellType.FORMULA | CellType.BOOLEAN
    "Zero"                  | CellType.NUMERIC | null
    "Integer"               | CellType.NUMERIC | null
    "Float"                 | CellType.NUMERIC | null
    "Integer formula"       | CellType.FORMULA | CellType.NUMERIC
    "Float formula"         | CellType.FORMULA | CellType.NUMERIC
    "Date"                  | CellType.NUMERIC | null
    "Date time"             | CellType.NUMERIC | null
    "Date formula"          | CellType.FORMULA | CellType.NUMERIC
    "Date time formula"     | CellType.FORMULA | CellType.NUMERIC
    "Error"                 | CellType.FORMULA | CellType.ERROR
  }

}
