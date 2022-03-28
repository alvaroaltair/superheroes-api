<div align="center">

<h1>Superheroes API</h1>

<h3>Languages and Tools</h3>
<img height="50" width="60" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg"/>
<img height="50" width="60" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg"/>
</div>

--------------------------------------------

This is my solution to the **Nstech Jr Backend Challenge**. I'm using the API at https://akabab.github.io/superhero-api/

### Getting all data
Access http://localhost:8080/ to get all heroes `id` and `name`.

Demo:
```
[
   {
      "id":1,
      "name":"A-Bomb"
   },
   {
      "id":2,
      "name":"Abe Sapien"
   },
   {
      "id":3,
      "name":"Abin Sur"
   },
   ....
]
```

### Getting top 5 powerstat data
Access http://localhost:8080/powerstat/{powerstat} to get top 5 data based on the powerstat entered at `{powerstat}`.

Top 5 superheroes intelligence powerstat:

```
[
   {
      "id":30,
      "intelligence":100,
      "name":"Ant-Man"
   },
   {
      "id":35,
      "intelligence":100,
      "name":"Apocalypse"
   },
   {
      "id":70,
      "intelligence":100,
      "name":"Batman"
   },
   {
      "id":80,
      "intelligence":100,
      "name":"Beyonder"
   },
   {
      "id":136,
      "intelligence":100,
      "name":"Brainiac"
   }
]