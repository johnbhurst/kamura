// Copyright 2016 John Hurst
// John Hurst (john.b.hurst@gmail.com)
// 2016-04-14

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

package nz.kamura.google.oauth2.example

import com.google.api.client.auth.oauth2.Credential
import com.google.api.services.drive.DriveScopes
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.ListFeed
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed
import com.google.gdata.data.spreadsheet.WorksheetEntry

/**
 * Sample program to illustrate obtaining OAuth2 credential for accessing Google services.
 */
class Main {

  static void main(String[] args) {
    CliBuilder cli = new CliBuilder(usage: "kamura-google-oauth2-example --authorizationCodeFlow|--serviceAccount [options] applicationName spreadsheetName worksheetName",
                                    header: "Show Google Drive Spreadsheets authentication/authorization with OAuth2.\nOptions:")
    cli.h(longOpt: "help", args: 0, "Help")
    cli.a(longOpt: "authorizationCodeFlow", args: 0, "Use GoogleAuthorizationCodeFlow")
    cli.s(longOpt: "serviceAccount", args: 0, "Use Service Account")
    cli._(longOpt: "clientId", args: 1, argName: "client id", "Client ID")
    cli._(longOpt: "clientSecret", args: 1, argName: "client secret", "Client secret")
    cli._(longOpt: "accountId", args: 1, argName: "account id", "Service Account ID")
    cli._(longOpt: "user", args: 1, argName: "user", "Service Account user")
    cli._(longOpt: "privateKeyId", args: 1, argName: "id", "Private Key ID")
    cli._(longOpt: "p12File", args: 1, argName: "file", "P12 key file")
    cli._(longOpt: "pemFile", args: 1, argName: "file", "PEM key file")
    cli._(longOpt: "privateKeyFile", args: 1, argName: "file", "Service Account Private Key file")

    OptionAccessor options = cli.parse(args)
    if (options.help || (!options.authorizationCodeFlow && !options.serviceAccount)) {
      cli.usage()
      System.exit(1)
    }

    CredentialFactory credentialFactory = options.authorizationCodeFlow ?
      createAuthorizationCodeFlowCredentialFactory(options) :
      createServiceAccountCredentialFactory(options)

    Credential credential = credentialFactory.createCredential()

    SpreadsheetService spreadsheetService = new SpreadsheetService("OAuth_sample")
    spreadsheetService.setOAuth2Credentials(credential)
    URL feedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
    SpreadsheetFeed feed = spreadsheetService.getFeed(feedUrl, SpreadsheetFeed)
    SpreadsheetEntry spreadsheet = feed.entries.find { it.title.plainText == "Sample" }
    WorksheetEntry worksheet = spreadsheet.worksheets.find {it.title.plainText == "Invoice"}
    ListFeed invoice = spreadsheetService.getFeed(worksheet.listFeedUrl, ListFeed)
    invoice.entries.each {entry ->
      println(entry.customElements.getValue("Amount"))
    }
  }

  static CredentialFactory createAuthorizationCodeFlowCredentialFactory(OptionAccessor options) {
    GoogleAuthorizationCodeFlowCredentialFactory result = new GoogleAuthorizationCodeFlowCredentialFactory()
    if (options.clientId) {
      result.clientId = options.clientId
    }
    if (options.clientSecret) {
      result.clientSecret = options.clientSecret
    }
    result.scopes = [DriveScopes.DRIVE, "https://spreadsheets.google.com/feeds"]
    return result
  }

  static CredentialFactory createServiceAccountCredentialFactory(OptionAccessor options) {
    GoogleServiceAccountCredentialFactory result = new GoogleServiceAccountCredentialFactory()
    if (options.accountId) {
      result.accountId = options.accountId
    }
    if (options.user) {
      result.user = options.user
    }
    if (options.p12File) {
      result.p12File = new File(options.p12File as String)
    }
    result.scopes = [DriveScopes.DRIVE, "https://spreadsheets.google.com/feeds"]
    return result
  }
}
