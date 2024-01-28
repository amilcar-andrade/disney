# disney

## Build tools & versions used
- Gradle 8.2.0
- RxJava 3.0
- ViewModel 2.6
- LiveData 2.6
- Kotlin 1.9.10
- Picasso 2.8
- Retrofit 2.9.0
- Dagger 2.50

## Steps to run the app
- Open application and wait for the list to show up
- Click on item to see a different presentation of the comic

## What areas of the app did you focus on?
- Dependency injection pattern
- Clean architecture
- Material design recommendations

## What was the reason for your focus? What problems were you trying to solve?
- Avoid multiple network requests on rotation
- Provide a clear state model of what the feature is displaying
- Promote immutability
- Easy to test using dependency injection pattern

## How long did you spend on this project?
- 2/3 hours

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Improve the Dagger graph by breaking it down for features

## What do you think is the weakest part of your project?
- Not using Compose
- Personally prefer RxJava over Coroutines + Flow

## How to use the private key
- Run the md5 hashing on a website using the ts+pk+pbk
- The constants are inside of MarvelComicRepository
