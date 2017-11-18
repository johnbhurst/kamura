// Copyright 2017 Red Energy
// John Hurst (john.b.hurst@gmail.com)
// 2017-11-17

package nz.kamura.commons.csv

import spock.lang.Specification

class CommonsCsvReaderTest extends Specification {

  void "Given a stream of CSV data, when reading the fields, the correct types and values result"() {
    given:
    String csv = """
Item,Description,Quantity,Price
231,Widget,10,12.34
6224,Nut (Large),1,100
3887,Clip,1200,0.1
"""

    when:
    def vals = []
    new CommonsCsvReader(new StringReader(csv)).each {int item, String description, int quantity, BigDecimal price ->
      vals << [item: item, description: description, quantity: quantity, price: price]
    }

    then:
    vals == [
      [item: 231, description: "Widget", quantity: 10, price: 12.34],
      [item: 6224, description: "Nut (Large)", quantity: 1, price: 100.0],
      [item: 3887, description: "Clip", quantity: 1200, price: 0.1],
    ]
  }

}
