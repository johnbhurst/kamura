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
import java.time.ZoneId

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

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
    if (i >= row.lastCellNum) {
      return null
    }
    else {
      Cell cell = row.getCell(i)
      switch (type) {
        case String: return makeString(cell)
        case int: return makeInt(cell)
        case BigDecimal: return makeBigDecimal(cell)
        case Date: return makeDate(cell)
        case LocalDate: return makeLocalDate(cell)
        case Cell: return cell
        case Object: return cell
        default: throw new IllegalStateException("Row ${row.rowNum}: invalid parameter type [${type}]")
      }
    }
  }

  static String makeString(Cell cell) {
    return cell.richStringCellValue.string
  }

  static int makeInt(Cell cell) {
    return cell.numericCellValue as int
  }

  static BigDecimal makeBigDecimal(Cell cell) {
    return cell.numericCellValue as BigDecimal
  }

  static Date makeDate(Cell cell) {
    return cell.dateCellValue
  }

  static LocalDate makeLocalDate(Cell cell) {
    return cell.dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
  }
}