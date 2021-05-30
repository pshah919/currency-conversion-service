# Currency conversion api
This API is designed to convert currency from source currency of the country to targt currecy 
foreign exchange rates are retrived form https://exchangeratesapi.io/ and convert the currency.

# App hosting:
App is hosted on  AWS infra at http://35.154.7.59:8080/swagger-ui.html#  
##### login creds superadmin:{password}
loin in to app with above credentials and convert the currencies 

* Success Response: 200 ok
```json
{
"source": "INR",
"target": "USD",
"money":40000.00
}
```
* error currencyConvResponse 400 bad currencyConvRequest
```json
{
"errorInfo": "Please enter valid Source and Target"
}
```
