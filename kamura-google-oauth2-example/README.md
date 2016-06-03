
Two methods of authenticating are illustrated.

To use an OAuth2 client ID, create one in Google API Manager Credentials.
Download the JSON and extract the client ID and client secret.
Then run the sample application like this:

build/install/kamura-google-oauth2-example/bin/kamura-google-oauth2-example \
  --authorizationCodeFlow \
  --clientId=$CLIENTID \
  --clientSecret=$CLIENTSECRET

You will be prompted for an authorization code like this:

Please open the following URL in your browser, then paste the authorization code:
  https://accounts.google.com/o/oauth2/auth?access_type=online&approval_prompt=auto&client_id=67827228933-815r1f3vesr6m40uribs8jc7va7lp4kg.apps.googleusercontent.com&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=https://www.googleapis.com/auth/drive%20https://spreadsheets.google.com/feeds

Follow the instructions, and paste the resulting code back into the console.

To use a Service Account Key, create one in Google API Manager Credentials.
Use key type P12.
Download the P12 key.
Delegate domain-wide authority as documented here: https://developers.google.com/identity/protocols/OAuth2ServiceAccount
Then run the application like this:

build/install/kamura-google-oauth2-example/bin/kamura-google-oauth2-example \
  --serviceAccount
  --accountId=$ACCOUNTEMAIL
  --p12File=keyfile.p12
  --user=yourauthorizedgoogleuser@domain.com

The application should be able to access Google Docs without further interaction with the user.

