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
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory

import java.security.KeyStore
import java.security.PrivateKey

/**
 * Sample class to illustrate using Service Account to obtain OAuth2 credential.
 */
class GoogleServiceAccountCredentialFactory implements CredentialFactory {

  String accountId
  String user
  Collection<String> scopes
  String privateKeyId
  File p12File

  @Override
  Credential createCredential() {
    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    JsonFactory jsonFactory = JacksonFactory.defaultInstance

    GoogleCredential.Builder builder = new GoogleCredential.Builder()
      .setTransport(httpTransport)
      .setJsonFactory(jsonFactory)
      .setServiceAccountId(accountId)
      .setServiceAccountScopes(scopes)
      .setServiceAccountUser(user)

    if (p12File) {
      builder.setServiceAccountPrivateKeyFromP12File(p12File)
    }

    return builder.build()
  }
}
