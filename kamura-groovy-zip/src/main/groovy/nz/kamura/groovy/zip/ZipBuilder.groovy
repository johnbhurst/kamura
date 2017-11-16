// Copyright 2017 Red Energy
// John Hurst (john.b.hurst@gmail.com)
// 2017-11-17

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
