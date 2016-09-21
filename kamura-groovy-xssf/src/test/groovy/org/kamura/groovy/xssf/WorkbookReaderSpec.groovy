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
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import spock.lang.Specification

class WorkbookReaderSpec extends Specification {

  Workbook workbook

  void setup() {
    workbook = new XSSFWorkbook(this.getClass().getResourceAsStream("/eachrow_test.xlsx"))
  }

  static Date date(String s) {
    return Date.parse("yyyy-MM-dd", s)
  }

  void "Closure with typed parameters reads correct values and types"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) { String invoiceNumber, Date invoiceDate, BigDecimal amount ->
      results << [invoiceNumber: invoiceNumber, invoiceDate: invoiceDate, amount: amount]
    }
    then: "The expected values were seen"
    results == [
      [invoiceNumber: "A101", invoiceDate: date("2016-01-01"), amount: 123.45],
      [invoiceNumber: "A102", invoiceDate: date("2016-02-12"), amount: 132.54],
      [invoiceNumber: "A103", invoiceDate: date("2016-06-04"), amount: 10.99],
    ]
  }

  void "Closure with less parameters than cells reads as many cells as parameters"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) { String invoiceNumber, Date invoiceDate ->
      results << [invoiceNumber: invoiceNumber, invoiceDate: invoiceDate]
    }
    then: "The expected values are seen"
    results == [
      [invoiceNumber: "A101", invoiceDate: date("2016-01-01")],
      [invoiceNumber: "A102", invoiceDate: date("2016-02-12")],
      [invoiceNumber: "A103", invoiceDate: date("2016-06-04")],
    ]
  }

  void "Closure with more parameters than cells reads nulls for extra parameters"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) {String invoiceNumber, Date invoiceDate, BigDecimal amount, String description ->
      results << [invoiceNumber: invoiceNumber, invoiceDate: invoiceDate, amount: amount, description: description]
    }
    then: "The expected values were seen"
    results == [
      [invoiceNumber: "A101", invoiceDate: date("2016-01-01"), amount: 123.45, description: null],
      [invoiceNumber: "A102", invoiceDate: date("2016-02-12"), amount: 132.54, description: null],
      [invoiceNumber: "A103", invoiceDate: date("2016-06-04"), amount: 10.99, description: null],
    ]
  }

  void "Closure with Object parameters reads values as Cells"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) {invoiceNumber, invoiceDate, amount ->
      results << [invoiceNumber: invoiceNumber, invoiceDate: invoiceDate, amount: amount]
    }
    then: "The correct types and values were seen"
    results*.invoiceNumber*.cellType == [Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING]
    results*.invoiceDate*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.amount*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.invoiceNumber*.richStringCellValue*.string == ["A101", "A102", "A103"]
  }

  void "Closure with Cell parameters reads values as Cells"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) {Cell invoiceNumber, Cell invoiceDate, Cell amount ->
      results << [invoiceNumber: invoiceNumber, invoiceDate: invoiceDate, amount: amount]
    }
    then: "The correct types and values were seen"
    results*.invoiceNumber*.cellType == [Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING]
    results*.invoiceDate*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.amount*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.invoiceNumber*.richStringCellValue*.string == ["A101", "A102", "A103"]
  }

  void "Closure with single typed parameter on single column reads correct values and type"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("SingleColumn")
    def results = []
    WorkbookReader.each(sheet) {BigDecimal amount ->
      results << amount
    }
    then: "The expected values were seen"
    results == [145.67, 167.45, 11.95]
  }

  void "Closure with single untyped parameter reads row as Map"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) {row ->
      results << row
    }
    then: "The expected values were seen"
    results*.invoiceNumber*.cellType == [Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING]
    results*.invoiceDate*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.amount*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.invoiceNumber*.richStringCellValue*.string == ["A101", "A102", "A103"]
  }

  void "Closure with single Map parameter reads row as Map"() {
    when: "Iterate over rows"
    def sheet = workbook.getSheet("Data")
    def results = []
    WorkbookReader.each(sheet) {Map row ->
      results << row
    }
    then: "The expected values were seen"
    results*.invoiceNumber*.cellType == [Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_STRING]
    results*.invoiceDate*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.amount*.cellType == [Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_NUMERIC]
    results*.invoiceNumber*.richStringCellValue*.string == ["A101", "A102", "A103"]
  }

}