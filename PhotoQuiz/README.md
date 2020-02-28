# PhotoApp

Android app for photo based quiz.

## Dev Requirements

- Android Studio 3.6 (Canary)

### Steps to build

- Clone the repo
- Build from Android Studio

### Running UI Tests

- Ensure emulator is running before executing this command

```
$ ./gradlew connectedAndroidTest
```

### Running Unit Tests

```
$ ./gradlew test
```

### Running tests with coverage

- Ensure emulator is running before executing this command

```
$ ./gradlew createDebugCoverageReport
```

Report can be found at `/app/build/reports/coverage/debug/index.html`

### Features

- Shows home screen
- Displays past scores
- Play photo quiz
- Timer of 10 sec for each question
- Correct/Wrong answer feedback
- Final Result - Time taken, correct, wrong and skipped question stats.
- MVC model (Model|View|Controller)

### Notes

- UI and Unit tests are added.(Coverage is 100% for Model)
- Tried to cover UI Test, but not all functionality is tested.
- Overall Coverage is 79% :P :-(

# Authors

- [Mohit Khare](mohit.khare@go-jek.com)
