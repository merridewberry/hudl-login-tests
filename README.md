# Tests Hudl login

### Test execution

#### Components

- Open JDK 11 (for example, Coretto: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- Maven (https://maven.apache.org/install.html)

#### Preparation

In order to run tests, you have to rename the `example.properties` file in the project's root directory to `config.properties` and fill it in with your credentials.

#### Execution

To execute all the tests, run the following command in the project's root directory:

```
    mvn clean test
```

To run tests marked with certain tags, specify it by using the following prompt:

```
    mvn clean test -Dcucumber.filter.tags=@test
```

where:

`-Dcucumber.filter.tags=@positive` runs only the tests with `@positive` tag;

`-Dcucumber.filter.tags='@positive or @smoke'` runs all tests that have either `@positive` or `@smoke` tag;

`-Dcucumber.filter.tags='@positive and @smoke'` runs only the tests that have `@positive` and `@smoke` tags at the same time.

Please note that you may experience some issues with running this prompt. Possible fix is surrounding `cucumber` with quotation marks: `mvn clean test -D"cucumber".filter.tags=@test`

#### Tags

All tests have at least one tag that allow you to run specific groups of tests.

- `@test` - all tests
- `@smoke` - smoke tests
- `@positive` - positive tests
- `@negative` - negative tests

#### Reports

You can access test reports by executing the following prompt in the project's root directory:

```
    mvn allure:serve
```

The report will be opened automatically in your default browser.

Please note that you have to manually terminate report server afterwards by following the instructions in terminal.