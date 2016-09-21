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

import java.time.LocalDate

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

import static org.kamura.groovy.xssf.CellUtils.cellBigDecimal
import static org.kamura.groovy.xssf.CellUtils.cellDate
import static org.kamura.groovy.xssf.CellUtils.cellInteger
import static org.kamura.groovy.xssf.CellUtils.cellLocalDate
import static org.kamura.groovy.xssf.CellUtils.cellString
import static org.kamura.groovy.xssf.CellUtils.cellInt

class PositionalRowArguments implements RowArguments {

  private List<Class> parameterTypes

  PositionalRowArguments(List<Class> parameterTypes) {
    this.parameterTypes = parameterTypes
  }

  @Override
  Object[] makeArguments(Row row) {
    List result = []
    parameterTypes.eachWithIndex { Class type, int i ->
      Object val = getVal(row, i, type)
      result << val
    }
    return result as Object[]
  }

  private static Object getVal(Row row, int i, Class type) {
    try {
      return tryGetVal(i, row, type)
    }
    catch (Exception ex) {
      throw new RuntimeException("Sheet ${row.sheet.sheetName}: Cannot extract $type from row $row.rowNum, cell $i", ex)
    }
  }

  private static Object tryGetVal(int i, Row row, Class type) {
    if (i >= row.lastCellNum) {
      return null
    }
    else {
      Cell cell = row.getCell(i)
      if (cell == null) {
        return null
      }
      switch (type) {
        case String: return cellString(cell)
        case int: return cellInt(cell)
        case Integer: return cellInteger(cell)
        case BigDecimal: return cellBigDecimal(cell)
        case Date: return cellDate(cell)
        case LocalDate: return cellLocalDate(cell)
        case Cell: return cell
        case Object: return cell
        default: throw new IllegalArgumentException("Invalid parameter type")
      }
    }
  }


}
