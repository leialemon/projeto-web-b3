
    // Requisição GET para listar usuários ao carregar a página
  document.getElementById('enviarBtn').addEventListener('click', function (){
    fetch('http://localhost:8080/api/v1/users', {
      method: 'GET'
    })
    .then(response => {
      if (!response.ok) {
          throw new Error('Erro na requisição: ' + response.status);
      }
      return response.json();  
    })
    .then(data => {
      document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data, null, 2);
    })
    .catch(error => {
      document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
    });
  });
  