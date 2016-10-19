// Copyright 2016 John Hurst
// John Hurst (john.hurst@gmail.com)
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
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory

/**
 * Sample class to illustrate using GoogleAuthorizationCodeFlow to obtain OAuth2 credential.
 */
class GoogleAuthorizationCodeFlowCredentialFactory implements CredentialFactory {

  static final String REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob"

  String clientId
  String clientSecret
  Collection<String> scopes
  String prompt = "Please open the following URL in your browser, then paste the authorization code:\n  %s\n"

  @Override
  Credential createCredential() {
    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    JsonFactory jsonFactory = JacksonFactory.defaultInstance
    GoogleAuthorizationCodeFlow flow =
      new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientId, clientSecret, scopes)
        .setAccessType("online")
        .setApprovalPrompt("auto")
        .build()
    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URL).build()
    String code = System.console().readLine(prompt, url)
    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URL).execute()
    return new GoogleCredential().setFromTokenResponse(response)
  }

}
