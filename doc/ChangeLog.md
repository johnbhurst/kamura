# Change Log


## REL-0.0.8 2023-04-28

* Stabilized features prior to some updates. 

## REL-00.00.07

* Test suite runs all example scripts.
* Added some Graphics2D DirectContent support.
* Added explicit property support for document, writer properties in builder.
* Added some PdfTemplate support.
* HelloWorld05 -> HelloWorld12 examples from iText examples.
* JFreeChart example from iText examples.
* PascalsTriangle example from iText examples.
* Made add/children handling more consistent and general.
* (Breaks compatibility with previous table cells.)
* Removed newPage() builder method, replace with document.newPage().
* Added new element factories:
  . Anchor
  . Chunk
* Removed withDirectContent(), withDirectContentUnder(); replaced with other approaches.
* Added PdfContentByte#withState(), withText().

## REL-00.00.06 2010-08-09

* Switched to iText 2.1.7 -- iText 5 Affero licence not suitable.
* Changed PDFBuilder constructor to require OutputStream.
* Added some iText examples made Groovy.

## REL-00.00.05 2010-08-02

* Added PdfWriterCategory: withDirectContent(), withDirectContentUnder().

## REL-00.00.04 2010-07-31

* Gradle publishes to Artifactory.

## REL-00.00.03 2010-07-27

* Removed debug printlns.

## REL-00.00.02 2010-07-23

* Improved images support.
* Added PdfPageEvent support via event closures.
* Added same JPdfUnit tests.
* Added Ivy upload/publish support.

## REL-00.00.01 2010-07-16

* First alpha release, can do paragraphs and tables.
* Started using in development at client site.

## REL-00.00.00 2010-07-16

* Tagging error?
