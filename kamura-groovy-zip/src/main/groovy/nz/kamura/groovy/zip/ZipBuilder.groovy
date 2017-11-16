// Copyright 2017- John Hurst
//
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

package nz.kamura.groovy.zip

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipBuilder {

  private ZipOutputStream zos

  ZipBuilder(OutputStream os) {
    this.zos = new ZipOutputStream(os)
  }

  ZipBuilder zip(Closure closure) {
    closure.delegate = this
    closure.call()
    zos.close()
    return this
  }

  void entry(Map<String, Object> props = [:], String name, Closure closure) {
    ZipEntry entry = new ZipEntry(name)
    props.each { String key, Object val -> entry[key] = val}
    zos.putNextEntry(entry)
    closure.call(new NonClosingOutputStream(zos))
  }

  // ZipEntry properties:
  //setComment(String)
  //setCompressedSize(long)
  //setCrc(long)
  //setExtra(byte[])
  //setMethod(int)
  //setSize(long)
  //setTime(long)

  private static class NonClosingOutputStream extends FilterOutputStream {
    NonClosingOutputStream(OutputStream os) {
      super(os)
    }

    @Override
    void close() throws IOException {
      // don't close
    }
  }
}
