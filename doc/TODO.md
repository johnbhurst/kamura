# TODO

## 2010-08-27

* Tests for all constructors on element factories, e.g. Image.
* Add factories for other Element classes: Anchor, Annotation, etc.  2010-09-01
* Documentation: builder node, creation and adding content.
* Documentation: document, writer properties.
* Documentation: event handling with closures.
* Documentation: initialization with closures.
* Documentation: notes about streaming, large objects.

## 2010-09-01

* More flexible tables: addCell(String)                              2010-09-05
* More flexible tables: completeRow() etc

## 2015-08-11

* Find free generic fonts to replace Windows fonts

## Admin

* Move iText2 examples to another project???
* jcenter project
* Register domain name?

## Design

Workbook reader currently named specifically for XSSF.
But it is dependent only on usermodel classes, and should work equally well for HSSF.

WorkbookReader.each(Sheet, Closure) as currently implemented allows "imperative" iteration over rows,
but does not support functional style such as .findAll, .collect etc.
Can we make it work automagically based on Groovy's support for .each()?

## Implementation

* hssf-groovy-builder
* hssf-groovy-reader
* xssf-groovy-builder
* xssf-groovy-reader
* itext5-groovy-builder

## Documentation

* README files in each project, with Overview, links
* USERGUIDE in each project
* BUILDING in each project
* Overall example script: migrate data from GData sheet to XLSX
* Overall example script: read data from XLSX, write to PDF

