
# Building

You must have Java installed. Any version from Java 8 onwards should be OK.
To build and run tests:

``` bash
./gradlew build
```

When a push is made to GitHub, a GitHub Action runs the above task and reports any issues.

# Releasing

Releases are generating using the GitHub command line client. 
A release is created in GitHub, and a GitHub Action runs this Gradle task to publish artifacts in GitHub Packages:

``` bash
./gradlew publish
```

The detailed steps are

1. Ensure all changes are committed and pushed, and that the build is green.
2. Ensure that ChangeLog.md is up to date.
3. Create a tag for the release.
4. Run `gh release create` to create the release.
5. Update the version in `build.gradle` to the next version.
