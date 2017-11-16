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

import org.apache.poi.ss.usermodel.Row

class MapRowArguments implements RowArguments {

  private Row headerRow

  MapRowArguments(Row headerRow) {
    this.headerRow = headerRow
  }

  @Override
  Object[] makeArguments(Row row) {
    if (row.lastCellNum != headerRow.lastCellNum) {
      throw new IllegalArgumentException("Row ${row.rowNum}: expecting ${headerRow.lastCellNum+1} cells, got ${row.lastCellNum+1}")
    }
    Map result = [:]
    for (i in 0..<row.lastCellNum) {
      // TODO: sanitise header strings to be Java Beans property names
      result[headerRow.getCell(i).richStringCellValue.string] = row.getCell(i)
    }
    return [result] as Object[]
  }

}
