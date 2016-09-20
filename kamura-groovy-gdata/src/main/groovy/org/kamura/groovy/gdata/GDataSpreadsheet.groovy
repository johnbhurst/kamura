// Copyright 2016 John Hurst
// John Hurst (john.b.hurst@gmail.com)
// 2016-04-03

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

package org.kamura.groovy.gdata

import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.SpreadsheetEntry

class GDataSpreadsheet {

  private SpreadsheetService spreadsheetService
  private SpreadsheetEntry spreadsheetEntry

  GDataSpreadsheet(SpreadsheetService spreadsheetService, SpreadsheetEntry spreadsheetEntry) {
    this.spreadsheetService = spreadsheetService
    this.spreadsheetEntry = spreadsheetEntry
  }

  GDataWorksheet getAt(String worksheetName) {
    def worksheetEntry = spreadsheetEntry.worksheets.find { it.title.plainText == worksheetName }
    return new GDataWorksheet(spreadsheetService, worksheetEntry)
  }
}
