// Copyright 2016 John Hurst
// John Hurst (john.hurst@gmail.com)
// 2016-04-02

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

package nz.kamura.groovy.gdata

import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed

class GDataSpreadsheetReader {

  private static final URL SPREADSHEETS_URL =
    new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")

  private SpreadsheetService spreadsheetService

  GDataSpreadsheetReader(SpreadsheetService spreadsheetService) {
    this.spreadsheetService = spreadsheetService
  }

  GDataSpreadsheet getAt(String spreadsheetName) {
    SpreadsheetFeed feed = spreadsheetService.getFeed(SPREADSHEETS_URL, SpreadsheetFeed)
    SpreadsheetEntry entry = feed.entries.find {it.title.plainText == spreadsheetName}
    return new GDataSpreadsheet(spreadsheetService, entry)
  }

}
