// Copyright 2016 John Hurst
// John Hurst (john.b.hurst@gmail.com)
// 2016-09-22

package org.kamura.groovy.xssf

import java.time.LocalDate
import java.time.ZoneId

import org.apache.poi.ss.usermodel.Cell

class CellUtils {

  private CellUtils() {}

  static String cellString(Cell cell) {
    switch (cell.cellType) {
      case Cell.CELL_TYPE_NUMERIC: return numberToString(cell.numericCellValue)
      case Cell.CELL_TYPE_STRING: return cell.stringCellValue
      case Cell.CELL_TYPE_FORMULA: return cell.stringCellValue
      case Cell.CELL_TYPE_BLANK: return ""
      case Cell.CELL_TYPE_BOOLEAN: return cell.booleanCellValue ? "true" : "false"
      case Cell.CELL_TYPE_ERROR: return "#ERROR: $cell.errorCellValue" // Possibly should throw exception
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static String numberToString(double val) {
    return Math.rint(val) == val ? (Math.round(val) as String) : (val as String)
  }

  static int cellInt(Cell cell) {
    return Optional.ofNullable(cellInteger(cell)).orElseThrow {new IllegalArgumentException("Blank value")}
  }

  static Integer cellInteger(Cell cell) {
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

  static BigDecimal cellBigDecimal(Cell cell) {
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

  static Date cellDate(Cell cell) {
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

  static LocalDate cellLocalDate(Cell cell) {
    return cellDate(cell)?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
  }
}
