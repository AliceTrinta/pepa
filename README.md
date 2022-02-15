# pepa
> The Electronic Health Record (Or Prontuário Eletrônico do Paciente - PEPA) is an API made to store all your medical history.

![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
    * [Built With](#built-with)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Running](#Running)
* [Contact](#contact)
* [Contributing](#contributing)



<!-- ABOUT THE PROJECT -->
## About The Project
Pepa is a simple API that stores all your medical history, developed in order to study Kotlin and Spring Framework.

### Built With
This work uses:
* [MongoDB](https://www.mongodb.com/golang)
* [Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Gradle](https://gradle.org/)


<!-- GETTING STARTED -->
## Getting Started

Follow pass-by-pass to run the project in your machine.

### Prerequisites

1. Have Installed Gradle on your machine
2. Have Installed Java on your machine
3. Have Installed MongoDB on your machine

### Installation

Clone repository
```sh
git clone https://github.com/AliceTrinta/petit
```

### Running

Firste step: Certify that your mongoDB configurations are well. If you need to change the database configurations, just go to *appication.properties*.


Second step: run the PepaApplication.

```sh

pepa

     |

     ---src

           |

           ---main

                  |

                  ---kotlin

                         |

                         ---com.example.pepa

                                             |

                                             ---PepaApplication

```

Third step: I recommend to use Postman, in order to access the endpoint.


The endpoints are listed above:

```sh

POST - http://localhost:8080/clinical_records OR http://localhost:8080/{personId}/clinical_records


Example of expected request:
{
    "clinical_backgrounds": [
        {
            "type":"DISEASE",
            "value":"Diabetes",
            "createdAt":"2021-03-03T09:55:00Z"
        },
        {
            "type":"DISEASE",
            "value":"Diabetes",
            "createdAt":"2021-03-03T09:55:00Z"
        }
    ]
}

Example of expected response:
{
    "clinical_backgrounds": [
        {
            "id": "c45c767a-d1db-4904-adda-6454ff70bcaf",
            "personId": "e8a23bbc-c84b-4d91-aae0-2e30e49fde7e",
            "value": "Diabetes",
            "type": "DISEASE",
            "createdAt": "2021-03-03T09:55:00Z"
        },
        {
            "id": "915b3465-c98f-495c-af03-4ab151cfa678",
            "personId": "e8a23bbc-c84b-4d91-aae0-2e30e49fde7e",
            "value": "Diabetes",
            "type": "DISEASE",
            "createdAt": "2021-03-03T09:55:00Z"
        }
    ]
}


```

```sh

GET - http://localhost:8080/{personId}/clinical_backgrounds

Example of expected response
{
    "clinical_backgrounds": [
        {
            "id": "c45c767a-d1db-4904-adda-6454ff70bcaf",
            "personId": "e8a23bbc-c84b-4d91-aae0-2e30e49fde7e",
            "value": "Diabetes",
            "type": "DISEASE",
            "createdAt": "2021-03-03T09:55:00Z"
        },
        {
            "id": "915b3465-c98f-495c-af03-4ab151cfa678",
            "personId": "e8a23bbc-c84b-4d91-aae0-2e30e49fde7e",
            "value": "Diabetes",
            "type": "DISEASE",
            "createdAt": "2021-03-03T09:55:00Z"
        }
    ]
}

```

<!-- CONTACT -->
## Contact

Alice Trinta – [@malicetrinta](https://www.instagram.com/malicetrinta/) – maria.trinta@aluno.cefet-rj.br

[https://www.researchgate.net/profile/Maria_Alice_Lima2/publications](https://www.researchgate.net/profile/Maria_Alice_Lima2/publications)
Project Link: [https://github.com/AliceTrinta/pepa](https://github.com/AliceTrinta/pepa)



<!-- CONTRIBUTING -->
## Contributing

1. Fork it (<https://github.com/AliceTrinta/pepa>)
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
