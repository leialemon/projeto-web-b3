document.addEventListener('DOMContentLoaded', function () {

  let pagina = window.location.pathname;
  console.log('Página atual:', pagina);

  switch(pagina){
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/buscarUsuario.html":
      console.log('Executando buscarUser()');
      buscarUser();
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/cadastrarUsuario.html":
      console.log('Executando cadastrarUsuario()');
      cadastrarUsuario();
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/verUsuarios.html":
      console.log('Executando mostrarUsuarios()');
      mostrarUsuarios();
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/cadastrarAcao.html":
      console.log('Cadastrando Ação');  
      cadastrarAcao();
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/verAcoes.html":
      console.log('Buscando ações');
      verAcoes();
      break;
    case "/home/juliana/repositorios/Ada-Homebroker/miniFront/fazerTransacao.html":
      console.log('Fazendo Transação');
      fazerTransacao();
      break;
    case  "/home/juliana/repositorios/Ada-Homebroker/miniFront/criarOrdem.html":
      console.log('Criando ordem');
      criarOrdem();
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
        document.getElementById('responseDiv').innerText = 'Usuário cadastrado com sucesso! \nResposta do servidor: ' + JSON.stringify(data, null, 2);
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
      document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data, null, 2);
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
      btn.addEventListener('click', function(event) {
        event.preventDefault();
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
          document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data, null, 2);
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
  
  function cadastrarAcao(){
    document.getElementById('cadastrarAcao').addEventListener('submit', function (event) {
      event.preventDefault();
  
      const formData = {
        price: document.getElementById('fprice').value,
        name: document.getElementById('fname').value,
        ticker: document.getElementById('fticker').value
      };
  
      fetch('http://localhost:8080/api/v1/stocks', {
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
        document.getElementById('responseDiv').innerText = 'Ação cadastrada com sucesso! \nResposta do servidor ' + JSON.stringify(data, null, 2);
      })
      .catch((error) => {
        console.error('Erro:', error);
        document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
      });
    });
  }
  
  function verAcoes(){
    document.getElementById('enviarBtn').addEventListener('click', function (){
      fetch('http://localhost:8080/api/v1/stocks', {
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
  }

  function fazerTransacao(){
    document.getElementById('fazerTransacao').addEventListener('submit', function (event) {
      event.preventDefault();
      let tipo;
        if (document.getElementById('deposito').checked){
          tipo = document.getElementById('deposito').value;
        } else if (document.getElementById('saque').checked) {
          tipo = document.getElementById('saque').value;
        } 
      
      const formData = {
        userCpf : document.getElementById('fcpf').value,
        amount : document.getElementById('fprice').value,
        type: tipo
      };
      fetch('http://localhost:8080/api/v1/transactions', {
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
        document.getElementById('responseDiv').innerText = 'Transação concluída! \nResposta do servidor ' + JSON.stringify(data, null, 2);
      })
      .catch((error) => {
        console.error('Erro:', error);
        document.getElementById('responseDiv').innerText = 'Erro: ' + error.message;
      });  
    
    })
    
            
  }

  function criarOrdem(){}

});
