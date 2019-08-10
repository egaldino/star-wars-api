# Star Wars API

Possibilita o gerenciamento de planetas, tendo como foco os que apareceram nos filmes de Star Wars. 

**Adicionar planeta**
----

* **URI**

  /api/starwars/planeta

* **Method:**

  `POST`
   
* **Body**
```json
  {
    "nome": "string",
    "clima": "string",
    "terreno": "string"
  }
```

* **Success Response:**
  * **Code:** 201 - CREATED<br />
 
* **Error Response:**
  * **Code:** 500 - INTERNAL SERVER ERROR <br />

**Listar planetas**
----

* **URI**

  /api/starwars/planetas

* **Method:**

  `GET`
   
* **Success Response:**
  * **Code:** 200 - OK<br />
  * **Content:**
```json
  [{
    "id": "string",
    "nome": "string",
    "clima": "string",
    "terreno": "string",
    "aparicoesEmFilmes": "integer",
  },]
```
* **Error Response:**
  * **Code:** 500 - INTERNAL SERVER ERROR <br />

**Recuperar planeta por id**
----

* **URL**

  /api/starwars/planetas/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[string]`- id do planeta a ser retornado
   

* **Success Response:**
  * **Code:** 200 - OK<br />
  * **Content:**
```json
  {
    "nome": "string",
    "clima": "string",
    "terreno": "string",
    "aparicoesEmFilmes": "integer"
  }
```
* **Error Response:**
  * **Code:** 404 - NOT FOUND - Quando planeta não é encontrado <br />
  
  OU <br />

  * **Code:** 500 - INTERNAL SERVER ERROR <br />
  
**Listar planetas por nome**
----

* **URI**

  /api/starwars/planetasPorNome/{nome}

* **Method:**

  `GET`
   
* **Success Response:**
  * **Code:** 200 - OK<br />
  * **Content:**
```json
  [{
    "id": "integer",
    "nome": "string",
    "clima": "string",
    "terreno": "string",
    "aparicoesEmFilmes": "integer"
  },]
```
* **Error Response:**
  * **Code:** 500 - INTERNAL SERVER ERROR <br />
 

**Remover planeta**
----

* **URL**

  /api/starwars/planeta/{id}

* **Method:**

  `DELETE`
  
*  **URL Params**

   `id=[string]`- id do planeta a ser removido

* **Success Response:**
  * **Code:** 200 - OK<br />
 
* **Error Response:**
  * **Code:** 500 - INTERNAL SERVER ERROR <br />
----
**Observações**

As configurações de acesso ao banco de dados (MongoDB) estão no arquivo src/main/resources/application.properties. <br />
Os valores default são: <br />
`spring.data.mongodb.database=star-wars-db`<br />
`spring.data.mongodb.host=localhost`<br />
`spring.data.mongodb.port=27017` <br />
`#spring.data.mongodb.username=` <br />
`#spring.data.mongodb.password=`<br />
