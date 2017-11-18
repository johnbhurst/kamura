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

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

import org.apache.commons.csv.CSVRecord

class PositionalRowArguments implements RowArguments {

  private List<Class> parameterTypes

  PositionalRowArguments(List<Class> parameterTypes) {
    this.parameterTypes = parameterTypes
  }

  @Override
  Object[] makeArguments(CSVRecord record) {
    List<String> values = record as List<String>
    return [values, parameterTypes].transpose().collect { String value, Class type ->
      return getVal(value, type)
    }
  }

  private static Object getVal(String value, Class type) {
    try {
      return tryGetVal(value, type)
    }
    catch (Exception ex) {
      throw new RuntimeException("Cannot extract $type from [$value]", ex) // TODO: Context?
    }
  }

  private static Object tryGetVal(String value, Class type) {
    switch (type) {
      case String: return value
      case int:
      case Integer: return Integer.parseInt(value)
      case BigDecimal: return new BigDecimal(value)
      case LocalDate: return LocalDate.parse(value)
      case LocalDateTime: return LocalDateTime.parse(value)
      case Date: return Date.from(LocalDateTime.parse(value).atZone(ZoneId.systemDefault()).toInstant())
      case Object: return value
      default: throw new IllegalArgumentException("Invalid parameter type [$type]")
    }
  }
}
