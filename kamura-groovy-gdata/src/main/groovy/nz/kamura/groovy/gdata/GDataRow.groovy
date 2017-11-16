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

package nz.kamura.groovy.gdata

import com.google.gdata.data.spreadsheet.ListEntry

class GDataRow {

  private ListEntry entry

  GDataRow(ListEntry entry) {
    this.entry = entry
  }

//  String getAt(String columnHeader) {
//    return entry.customElements.getValue(columnHeader)
//  }

  @Override
  Object getProperty(String columnHeader) {
    return entry.customElements.getValue(columnHeader)
  }
}
