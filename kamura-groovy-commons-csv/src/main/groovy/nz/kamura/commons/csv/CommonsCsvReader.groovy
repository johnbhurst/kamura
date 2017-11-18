// Copyright 2017
// John Hurst (john.b.hurst@gmail.com)
// 2017-11-17

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

package nz.kamura.commons.csv

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord

class CommonsCsvReader {

  Reader reader
  CSVFormat format

  CommonsCsvReader(Reader reader, CSVFormat format = CSVFormat.DEFAULT) {
    this.reader = reader
    this.format = format
  }

  void each(Closure closure) {
    RowArguments rowArguments = getRowArguments(closure)
    CSVParser parser = CSVParser.parse(reader, format)
    int lineNumber = 0
    parser.each { CSVRecord record ->
      if (lineNumber++) {
        Object[] arguments = rowArguments.makeArguments(record)
        tryCall(record, closure, arguments)
      }
    }
  }

  private static Object tryCall(CSVRecord record, Closure closure, Object[] arguments) {
    try {
      closure.call(*arguments)
    }
    catch (Exception ex) {
      throw new RuntimeException("Cannot apply closure on record", ex) // TODO: context?
    }
  }

  private static RowArguments getRowArguments(Closure closure) {
    Class[] parameterTypes = closure.parameterTypes
    if (!parameterTypes) {
      throw new IllegalArgumentException("Closure must have at least one parameter")
    }
    RowArguments rowArguments = parameterTypes.size() > 1 || (parameterTypes[0] != Object && parameterTypes[0] != Map) ?
      new PositionalRowArguments(parameterTypes as List<Class>) :
      new MapRowArguments()
    return rowArguments
  }

}
