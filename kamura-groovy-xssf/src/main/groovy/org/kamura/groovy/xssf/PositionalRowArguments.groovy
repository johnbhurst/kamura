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
        case String: return makeString(cell)
        case int: return makeInt(cell)
        case Integer: return makeInteger(cell)
        case BigDecimal: return makeBigDecimal(cell)
        case Date: return makeDate(cell)
        case LocalDate: return makeLocalDate(cell)
        case Cell: return cell
        case Object: return cell
        default: throw new IllegalArgumentException("Invalid parameter type")
      }
    }
  }

  static String makeString(Cell cell) {
    switch (cell.cellType) {
      case Cell.CELL_TYPE_NUMERIC: return cell.numericCellValue as String
      case Cell.CELL_TYPE_STRING: return cell.stringCellValue
      case Cell.CELL_TYPE_FORMULA: return cell.stringCellValue
      case Cell.CELL_TYPE_BLANK: return ""
      case Cell.CELL_TYPE_BOOLEAN: return cell.booleanCellValue ? "true" : "false"
      case Cell.CELL_TYPE_ERROR: return "#ERROR: $cell.errorCellValue" // Possibly should throw exception
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static int makeInt(Cell cell) {
    Integer integerValue = makeInteger(cell)
    if (integerValue == null) {
      throw new IllegalArgumentException("Blank value")
    }
    return integerValue
  }

  static Integer makeInteger(Cell cell) {
    switch (cell.cellType) {
      case Cell.CELL_TYPE_NUMERIC: return cell.numericCellValue as Integer
      case Cell.CELL_TYPE_STRING: return cell.stringCellValue as Integer
      case Cell.CELL_TYPE_FORMULA: return cell.numericCellValue as Integer
      case Cell.CELL_TYPE_BLANK: return null
      case Cell.CELL_TYPE_BOOLEAN: return cell.booleanCellValue ? 1 : 0
      case Cell.CELL_TYPE_ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static BigDecimal makeBigDecimal(Cell cell) {
    switch (cell.cellType) {
      case Cell.CELL_TYPE_NUMERIC: return cell.numericCellValue as BigDecimal
      case Cell.CELL_TYPE_STRING: return cell.stringCellValue as BigDecimal
      case Cell.CELL_TYPE_FORMULA: return cell.numericCellValue as BigDecimal
      case Cell.CELL_TYPE_BLANK: return null
      case Cell.CELL_TYPE_BOOLEAN: return cell.booleanCellValue ? 1 : 0
      case Cell.CELL_TYPE_ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static Date makeDate(Cell cell) {
    switch (cell.cellType) {
      case Cell.CELL_TYPE_NUMERIC: return cell.dateCellValue
      case Cell.CELL_TYPE_STRING: throw new IllegalArgumentException("String value") // use cell formatting to convert?
      case Cell.CELL_TYPE_FORMULA: return cell.dateCellValue
      case Cell.CELL_TYPE_BLANK: return null
      case Cell.CELL_TYPE_BOOLEAN: throw new IllegalArgumentException("Boolean value")
      case Cell.CELL_TYPE_ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static LocalDate makeLocalDate(Cell cell) {
    return makeDate(cell)?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
  }
}
