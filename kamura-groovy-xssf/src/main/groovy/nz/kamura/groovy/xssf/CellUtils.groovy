// Copyright 2016 John Hurst
// John Hurst (john.b.hurst@gmail.com)
// 2016-09-22

package nz.kamura.groovy.xssf

import java.time.LocalDate
import java.time.ZoneId

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType

class CellUtils {

  private CellUtils() {}

  static String cellString(Cell cell) {
    switch (cell.cellType) {
      case CellType.NUMERIC: return numberToString(cell.numericCellValue)
      case CellType.STRING: return cell.stringCellValue
      case CellType.FORMULA: return cell.stringCellValue
      case CellType.BLANK: return ""
      case CellType.BOOLEAN: return cell.booleanCellValue ? "true" : "false"
      case CellType.ERROR: return "#ERROR: $cell.errorCellValue" // Possibly should throw exception
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
      case CellType.NUMERIC: return cell.numericCellValue as Integer
      case CellType.STRING: return cell.stringCellValue as Integer
      case CellType.FORMULA: return cell.numericCellValue as Integer
      case CellType.BLANK: return null
      case CellType.BOOLEAN: return cell.booleanCellValue ? 1 : 0
      case CellType.ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static BigDecimal cellBigDecimal(Cell cell) {
    switch (cell.cellType) {
      case CellType.NUMERIC: return cell.numericCellValue as BigDecimal
      case CellType.STRING: return cell.stringCellValue as BigDecimal
      case CellType.FORMULA: return cell.numericCellValue as BigDecimal
      case CellType.BLANK: return null
      case CellType.BOOLEAN: return cell.booleanCellValue ? 1 : 0
      case CellType.ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static Date cellDate(Cell cell) {
    switch (cell.cellType) {
      case CellType.NUMERIC: return cell.dateCellValue
      case CellType.STRING: throw new IllegalArgumentException("String value") // use cell formatting to convert?
      case CellType.FORMULA: return cell.dateCellValue
      case CellType.BLANK: return null
      case CellType.BOOLEAN: throw new IllegalArgumentException("Boolean value")
      case CellType.ERROR: throw new IllegalArgumentException("Error value")
      default: throw new IllegalArgumentException("Unsupported cell type $cell.cellType")
    }
  }

  static LocalDate cellLocalDate(Cell cell) {
    return cellDate(cell)?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
  }
}
