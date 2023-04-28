# kamura-groovy-itext2

John Hurst <john.b.hurst@gmail.com>
v0.0.1, 2016-06-04

kamura-groovy-itext2 is a Groovy PDF builder that uses the [iText library](http://itextpdf.com) to generate PDF documents.

*Note:* As of the end of 2009, the iText 5.x code base switched to the [Affero General Public License](http://itextpdf.com/terms-of-use/index.php).
This license is likely to be unsuitable for many projects.

Thus, kamura-groovy-itext2 targets the iText 2.x code base, which uses the more liberal Mozilla Public License.
Note that the iText 5.x code base is source-incompatible with iText 2.x: package names have been changed from com.lowagie to com.itextpdf, classes have been moved/removed/changed.
Be sure to refer to the iText 2.x documentation when using kamura-groovy-itext2.

The project is inspired by the [groovy-pdf project](http://code.google.com/p/groovy-pdf), but contains all new code.

Currently the code is under development, and still in a very much alpha state.
However, we are already using it for some projects under development at a client.

We hope to follow up soon with:

* Documentation
* Samples
* More features
* More tests
* Maven artifacts

Here's a Hello World from pdf-builder:

``` groovy
@Grab("nz.kamura:kamura-groovy-itext2:0.0.1")
import nz.kamura.groovy.itext2.IText2Builder

new IText2Builder(new FileOutputStream("Hello.pdf")).document() {
  paragraph("Hello from iText.")
}
```
