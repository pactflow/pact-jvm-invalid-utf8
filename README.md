# pact-jvm-invalid-utf8

To run `./gradlew check`

It will create a Pact file in `build/pacts`:

```json
{
  "consumer": {
    "name": "InvalidUTF8Provider"
  },
  "interactions": [
    {
      "description": "some request",
      "request": {
        "method": "GET",
        "path": "/"
      },
      "response": {
        "body": {
          "values": {
            "value1": "\uD95F\uD6AE\u74A1\u2AE4\u062C\uF859\u83ED\u8B66\uF5DD\u3D2E",
            "value2": "\uE61E\uD219\uA8E5\u5974\u23DB\u69DA\u5681",
            "value3": "\u45E7\uE019\u22FA"
          }
        },
        "generators": {
          "body": {
            "$.values.value1": {
              "regex": ".*",
              "type": "Regex"
            },
            "$.values.value2": {
              "regex": ".*",
              "type": "Regex"
            },
            "$.values.value3": {
              "regex": ".*",
              "type": "Regex"
            }
          }
        },
        "headers": {
          "Content-Type": "application/json; charset=UTF-8"
        },
        "matchingRules": {
          "body": {
            "$.values.value1": {
              "combine": "AND",
              "matchers": [
                {
                  "match": "regex",
                  "regex": ".*"
                }
              ]
            },
            "$.values.value2": {
              "combine": "AND",
              "matchers": [
                {
                  "match": "regex",
                  "regex": ".*"
                }
              ]
            },
            "$.values.value3": {
              "combine": "AND",
              "matchers": [
                {
                  "match": "regex",
                  "regex": ".*"
                }
              ]
            }
          },
          "header": {
            "Content-Type": {
              "combine": "AND",
              "matchers": [
                {
                  "match": "regex",
                  "regex": "application/json(;\\s?charset=[\\w\\-]+)?"
                }
              ]
            }
          }
        },
        "status": 200
      }
    }
  ],
  "metadata": {
    "pact-jvm": {
      "version": "4.5.4"
    },
    "pactSpecification": {
      "version": "3.0.0"
    }
  },
  "provider": {
    "name": "InvalidUTF8Provider"
  }
}
```
