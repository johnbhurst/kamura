# kamura-groovy-itext2-examples

John Hurst <john.b.hurst@gmail.com>
v0.0.9, 2023-04-28

This folder contains code to run "iText In Action" examples, both the original Java versions and the Groovy IText2Builder versions.

This folder is configured as a separate Gradle project from the main set of modules, because the setup is a bit complicated and depends on items that need to be downloaded separately and put into place. 

TODO: More explanation and instructions.

## Running tests

To run all of the tests, you need some prerequisites:

* iText examples (for data and images)
* Windows fonts

Get the iText examples from the SourceForge SVN repository, using a command like this:

``` bash
svn co  https://itext.svn.sourceforge.net/svnroot/itext/examples itext-examples
```

If you are running Windows, you should have the necessary fonts.
Otherwise, you need to get these files and place them in a directory on your system:

* arbli\_\_\_.ttf
* arialbd.ttf
* arialbi.ttf
* ariali.ttf
* arial.ttf
* comicbd.ttf
* comic.ttf
* msgothic.ttc

Finally, you might need to configure these locations in gradle.properties, e.g.:

```
pdfBuilderITextExamplesHome   = /home/user/kamura/kamura-groovy-itext2-examples/itext-examples
pdfBuilderWindowsFontLocation = /home/user/kamura/kamura-groovy-itext2-examples/windows-fonts
```

