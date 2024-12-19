const produtosDisponiveis = [
    { id: 1, nome: 'Martelo', preco: 50.0 },
    { id: 2, nome: 'Chave de Fenda', preco: 25.0 },
    { id: 3, nome: 'Alicate', preco: 30.0 }
];

let carrinho = [];

function listarProdutos() {
    let output = 'Produtos disponíveis:<br>';
    produtosDisponiveis.forEach(produto => {
        output += `ID: ${produto.id}, Nome: ${produto.nome}, Preço: R$${produto.preco}<br>`;
    });
    document.getElementById('output').innerHTML = output;
}

function adicionarProduto() {
    let idAdicionar = parseInt(prompt('Digite o ID do produto que deseja adicionar:'));
    let produto = produtosDisponiveis.find(p => p.id === idAdicionar);
    if (produto) {
        carrinho.push(produto);
        alert(`${produto.nome} foi adicionado ao carrinho.`);
    } else {
        alert('Produto não encontrado.');
    }
}

function removerProduto() {
    let idRemover = parseInt(prompt('Digite o ID do produto que deseja remover:'));
    let index = carrinho.findIndex(p => p.id === idRemover);
    if (index !== -1) {
        carrinho.splice(index, 1);
        alert(`Produto com ID ${idRemover} foi removido do carrinho.`);
    } else {
        alert('Produto não encontrado no carrinho.');
    }
}

function verCarrinho() {
    if (carrinho.length === 0) {
        document.getElementById('output').innerHTML = 'O carrinho está vazio.';
    } else {
        let output = 'Produtos no carrinho:<br>';
        carrinho.forEach(produto => {
            output += `ID: ${produto.id}, Nome: ${produto.nome}, Preço: R$${produto.preco}<br>`;
        });
        document.getElementById('output').innerHTML = output;
    }
}

function finalizarCompra() {
    let total = carrinho.reduce((sum, produto) => sum + produto.preco, 0);
    let frete = parseFloat(prompt('Digite o valor do frete:'));
    if (!isNaN(frete)) {
        alert(`Valor final (com frete): R$${(total + frete).toFixed(2)}`);
    } else {
        alert('Entrada inválida. Valor do frete não informado corretamente.');
    }
}
