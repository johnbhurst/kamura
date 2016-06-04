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

package org.kamura.xssf

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

class PositionalRowArguments implements RowArguments {

  private List<Class> parameterTypes

  PositionalRowArguments(List<Class> parameterTypes) {
    this.parameterTypes = parameterTypes
  }

  @Override
  Object[] makeArguments(Row row) {
    if (row.lastCellNum != parameterTypes.size()) {
      throw new IllegalArgumentException("Row ${row.rowNum}: expecting ${parameterTypes.size()} cells, got ${row.lastCellNum}")
    }
    List result = []
    parameterTypes.eachWithIndex { Class type, int i ->
      Cell cell = row.getCell(i)
      Object val = null
      switch (type) {
        case String: val = makeString(cell); break
        case BigDecimal: val = makeBigDecimal(cell); break
        case Date: val = makeDate(cell); break
        case Cell: val = cell; break
        case Object: val = cell; break
        default: throw IllegalStateException("Row ${row.rowNum}: invalid parameter type [${type}]")
      }
      result << val
    }
    return result as Object[]
  }

  static String makeString(Cell cell) {
    return cell.richStringCellValue.string
  }

  static BigDecimal makeBigDecimal(Cell cell) {
    return cell.numericCellValue as BigDecimal
  }

  static Date makeDate(Cell cell) {
    return cell.dateCellValue
  }
}
