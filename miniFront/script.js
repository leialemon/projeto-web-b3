document.addEventListener('DOMContentLoaded', function () {

  let pagina = window.location.pathname;
  console.log('Página atual:', pagina);

  switch(pagina){
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/buscarUsuario.html":
      buscarUser();
      console.log('Executando buscarUser()');
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/cadastrarUsuario.html":
      cadastrarUsuario();
      console.log('Executando cadastrarUsuario()');
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/verUsuarios.html":
      mostrarUsuarios();
      console.log('Executando mostrarUsuarios()');
      break;  
  }


  function cadastrarUsuario(){
    document.getElementById('cadastrarUsuario').addEventListener('submit', function (event) {
      event.preventDefault();
  
      const formData = {
        name: document.getElementById('fname').value,
        password: document.getElementById('fsenha').value,
        birthDate: document.getElementById('fnascimento').value,
        cpf: document.getElementById('fcpf').value,
        email: document.getElementById('femail').value,
      };
  
      fetch('http://localhost:8080/api/v1/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erro HTTP: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        console.log('Sucesso:', data);
        document.getElementById('responseDiv').innerText = 'Usuário cadastrado com sucesso! Resposta: ' + JSON.stringify(data);
      })
      .catch((error) => {
        console.error('Erro:', error);
        document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
      });
    });
  }

  function mostrarUsuarios(){
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
      document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data);
    })
    .catch(error => {
      document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
    });
  });
  }

  function buscarUser() {
    console.log('entrou no método');
    const btn = document.getElementById('buscarUser');
    if (btn) {
      btn.addEventListener('click', function() {
        const cpf = document.getElementById('bcpf').value;
        console.log('CPF a ser buscado:', cpf);
        if (!cpf) {
          document.getElementById('responseDiv').innerText = 'Por favor, insira um CPF.';
          return;
        }
  
        let url = 'http://localhost:8080/api/v1/users/cpf/' + cpf;
        console.log('Fazendo requisição para URL:', url);
        
        fetch(url, {
          method: 'GET'
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.status);
          }
          return response.json();
        })
        .then(data => {
          console.log('Dados recebidos:', data);
          document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data);
        })
        .catch(error => {
          console.error('Erro ao buscar usuário:', error);
          document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
        });
      });
    } else {
      console.log('Botão de buscar não encontrado.');
    }
  }
  

  
  


  
});
