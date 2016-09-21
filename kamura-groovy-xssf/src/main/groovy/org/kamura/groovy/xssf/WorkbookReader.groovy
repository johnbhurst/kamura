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

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

class WorkbookReader {

  /**
   * Iterate over sheets in the workbook, applying the closure to each sheet.
   */
  static void each(Workbook workbook, Closure closure) {

  }

  /**
   * Iterate over rows in the sheet, applying the closure to each row.
   *
   * There are two styles for the closure.
   *
   * sheet.each {String invoiceNumber, Date invoiceDate, BigDecimal amount ->
   *   // do stuff with invoiceNumber, invoiceDate, amount
   * }
   *
   * when the closure has individual typed parameters,
   * it is passed the cells in the row, individually, converted to the appropriate types.
   *
   * sheet.each {row ->
   *   // do stuff with row.invoiceNumber, row.invoiceDate, row.amount
   * }
   *
   * when the closure has a single untyped parameter,
   * it is passed a map with the column names as keys and the cells as values (as type Cell).
   */
  static void each(Sheet sheet, Closure closure) {
    RowArguments rowArguments = getRowArguments(closure, sheet)
    for (i in 1..sheet.lastRowNum) {
      Row row = sheet.getRow(i)
      Object[] arguments = rowArguments.makeArguments(row)
      closure.call(*arguments)
    }
  }

  private static RowArguments getRowArguments(Closure closure, Sheet sheet) {
    Class[] parameterTypes = closure.parameterTypes
    if (!parameterTypes) {
      throw new IllegalArgumentException("Closure must have at least one parameter")
    }
    RowArguments rowArguments = parameterTypes.size() > 1 || (parameterTypes[0] != Object && parameterTypes[0] != Map) ?
      new PositionalRowArguments(parameterTypes as List<Class>) :
      new MapRowArguments(sheet.getRow(0))
    rowArguments
  }

}
