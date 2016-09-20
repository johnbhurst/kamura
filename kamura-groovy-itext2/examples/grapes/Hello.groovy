// Add in ~/.groovy/grapeConfig.xml:
//       <ibiblio name="skepticalhumorist01" root="http://skepticalhumorist.co.nz/artifactory/repo" m2compatible="true"/>
// This is required until this library gets into a public repo.

@Grab("org.kamura:kamura-groovy-itext2:latest.release")
import org.kamura.groovy.itext2.IText2Builder

new IText2Builder(new FileOutputStream("Hello.pdf")).document() {
  paragraph("Hello from iText.")
}
