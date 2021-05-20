# blog-api-jwt
Blog de posts com autenticação JWT

API desenvolvida em Java 11.

SUBIR A APLICAÇÃO SPRINGBOOT E UTILIZAR PELO POSTMAN.
NECESSÁRIO TER O POSTGRE INSTALADO NA MÁQUINA.

1-Criar usuário.
2-Logar com o usuário e anotar o token gerado. Ir na aba "Authorization e selecionar o type para
  Bearer Token. Copiar e colar o token gerado no campo ao lado.
3-Configurar a aba "Headers" com Key = Content-Type, Value = application/json.

Obs: Para trocar de usuário, deve se reiniciar a aplicação e logar com o usuário diferente.

Criar um novo usuario:
POST - http://localhost:8080/api/auth/signup
JSON:
{
    "usuario":"(String)",
    "senha":"(String)"
}


Logar no sistema:
POST - http://localhost:8080/api/auth/signin
JSON:
{
    "usuario":"(String)",
    "senha":"(String)"
}

Criar um novo post:
POST - http://localhost:8080/api/newpost
JSON:
    {
        "titulo": "(String)",
        "link": "(String)",
        "data": "dd-mm-yyyy",
        "texto": "(String)",
        "imagemNome": "(String)",
        "imagemTipo": "(String)",
        "imagemTamanho": "(String)"
     }

Adicionar um comentário:
PUT - http://localhost:8080/api/post/(id do post)
JSON:
     {
        "comentario":"(String)"
      }

Excluir um post 
DELETE - http://localhost:8080/api/post/
JSON:
    {
    	"id":"(String)"
    }

Excluir um comentário:
DELETE - http://localhost:8080/api/deletarComentario/
JSON:
    {
    	"id"(id do comentario):"(String)"
    }