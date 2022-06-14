

# Concerts API

API written in Java using SpringBoot.
It has a single endpoint, that fetches the most listened bands from your lastFM account and checks if they are on tour in the requested location(s).
Used in my Magic Mirror and available in [this module](https://github.com/muilpp/MM-concert-calendar)

## Running the API

- Edit the config.properties file and in the project root and add your [Ticketmaster API key](https://developer.ticketmaster.com/products-and-docs/apis/international-discovery/v2/).
- Package the project:
```bash
mvn package -Dapp.home={location of the config.properties file}
```
- Run it:
```bash
java -jar "-Dapp.home={location of the config.properties file}" concerts-api-0.0.1-SNAPSHOT.jar
```

## Usage

The API is a single point that accepts multiple parameters:

```bash
http://localhost:8092/concerts/lastfmUser/lastfmKey/latitude/longitude?bandsLimit=limit
```
- LastfmUser: Comma separated String array that might contain ore or multiple users to check the most listened bands of.
- LastfmKey: It's the key provided by [lastFM](https://www.last.fm/api), in the form of a String.
- Latitude^: Comma separated String array containing the latitudes of all the locations you want to find concerts in.
- Longitude^: Comma separated String array containing the longitudes of all the locations you want to find concerts in.
- BandsLimit: Max number of bands to check, the valid range is from 1 to 1000.

^Latitude and longitude are both an array of strings, and they have to have the same length.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
